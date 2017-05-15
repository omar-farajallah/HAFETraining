/**
 * File for custom field validation overriding the OOTB validation.
 * 
 */
/**
 * Validate form for the multifield
 * @param $form
 * @param componentName
 * @returns {Boolean}
 */

function validateForm($form,componentName){
    var listOfMultifields = $form.find('.coral-Multifield');
	var isValid = true;

	$form.find("input, select, textarea").each(function(index,field){
		var isRequired = $(field).attr('aria-required') === 'true';
		var display = ($(field).parents('.coral-Form-fieldwrapper').css('display') != 'none');
		var isCustomMandatoryField = $(field).attr('data-requiredfield') === 'true' || 
										$(field).parents('.coral-PathBrowser').attr('data-requiredfield') === 'true' ||
										$(field).parents('.coral-Select').attr('data-requiredfield') === 'true';
		if(display){
			if(isRequired){
				if($(field).hasClass('coral-Select-select')){
					var value = $(field).eq(0).val();
					if(value == undefined || value == null || value == ''){
						$(field).closest(".coral-Select").adaptTo("foundation-field").setInvalid(true);
						isValid = false;
					}
				}else{
					var api = $(field).adaptTo("foundation-validation");
			         if (api != undefined) { 
			            if(!api.checkValidity()){
			            	isValid = false;
			            }
			            api.updateUI();
			        }
				}
			} else if(isCustomMandatoryField && $(field).parents('.coral-TabPanel-pane').attr('data-hidden') != 'true'){
				var value = $(field).eq(0).val();
				if(value == null || value == undefined || value == ''){
					if($(field).hasClass('js-coral-pathbrowser-input')){
						$(field).adaptTo("foundation-field").setRequired(true);
						
					} else if($(field).hasClass('coral-Select-select')){
						$(field).adaptTo("foundation-field").setRequired(true);
					} else {
						show($(field), 'Please fill out this required field');					
					}
					isValid = false;
				}else {
					clear($(field));
				}
            }else {
				clear($(field));
            }
		}
    });
	if(isValid && listOfMultifields != undefined && listOfMultifields.length > 0){
		isValid = isValid && countValidation(listOfMultifields);
	}
    return isValid;
}

function countValidation(listOfMultifields){
	var isValidCount = false;
	listOfMultifields.each(function(){
		
		if($(this).parents('.coral-TabPanel-pane').attr('data-hidden') != 'true'){
			var maxCount = $(this).attr('data-maxcount');
		    var minCount = $(this).attr('data-mincount');
			var actualCount = $(this).children('ol').children().length;
			var isVisible = $(this).parent().css('display') != 'none' ;			
		
		    if(isVisible && maxCount!=undefined && parseInt(maxCount) < actualCount){
		    	show($(this), "Maximum allowed is: " + maxCount);
		    	isValidCount = false;
		    	return false;
		    }else if(isVisible && minCount!=undefined && parseInt(minCount) > actualCount){
		    	show($(this), "Minimum required fields is: " + minCount);
		    	isValidCount = false;
		    	return false;
		    }else {
		    	clear($(this));
		    	isValidCount = true;
		    }
		}		
	});	
    return isValidCount;
}

function show(multifield, message) {
var field = multifield.closest(".coral-Form-field");
    
    field.attr("aria-invalid", "true")
      .toggleClass("is-invalid", true);

    field.nextAll(".coral-Form-fieldinfo")
      .addClass("u-coral-screenReaderOnly");
    
    field.setCustomValidity(message);
    field.checkValidity();
    field.updateErrorUI();
  }

function clear(multifield) {
	var field = multifield.closest(".coral-Form-field");

    field.removeAttr("aria-invalid").removeClass("is-invalid");
    field.setCustomValidity(null);

    field.validationMessage();
    field.updateErrorUI();

	checkValidity(field);

  }

function checkValidity(field){

    var panel = field.parents('.coral-TabPanel-pane');
    var isInvalid = panel.find('.is-invalid').length != 0;
    if(!isInvalid){
		var index = panel.index();
        $('.cq-dialog-content').find('.coral-TabPanel-navigation').children().eq(index).removeClass('is-invalid');
    }
}

/**
 * Get the validation error HTML as a jQuery object
 *
 * @return {jQuery} The validation error HTML as a jQuery object
 * @function
 */
function getFieldErrorEl() {
  return $("<span class='coral-Form-fielderror coral-Icon coral-Icon--alert coral-Icon--sizeS' data-init='quicktip' data-quicktip-type='error' />").clone();
}

/**
 * Mandatory validation & custom error message display 
 * @param fieldSet
 * @returns {Boolean}
 */
function validateMandatory(fieldSet){
	var isBreak = true;
    fieldSet.find('[data-isrequired]').each(function(){
		var isRequired = $(this).attr('data-isrequired') == 'true';
        var value = getValue($(this)).length == 0;
        if(isRequired && value){
            var errorLabel = $(this).attr('data-errorlabel');
			isBreak = false;
            displayMessage(errorLabel + " is mandatory");
            return false;
        }
    });
    return isBreak;
}

/**
 * Max length validation for multifield attributes
 * @param fieldSet
 * @returns {Boolean}
 */
function validateMaximumLength(fieldSet){
    var isBreak = true;
    fieldSet.find('[data-maxlength]').each(function(){
		var maxLength = $(this).attr('data-maxlength');
        var currentLength = getValue($(this)).length;
        if(currentLength > maxLength){
            var errorLabel = $(this).attr('data-errorlabel');
			isBreak = false;
            displayMessage(errorLabel + " cannot exceed " + maxLength + " characters");
            return false;
        }
    });    
        
    return isBreak;
}

/**
 * Min length validation for multifield attribute
 * @param fieldSet
 * @returns {Boolean}
 */
function validateMinimumLength(fieldSet){
    var isBreak = true;
    fieldSet.find('[data-minlength]').each(function(){
		var minLength = $(this).attr('data-minlength');
        var currentLength = getValue($(this)).length;
        if(currentLength < minLength){
            var errorLabel = $(this).attr('data-errorlabel');
			isBreak = false;
            displayMessage(errorLabel + " should have minimum of " + minLength + " characters");
            return false;
        }
    });
    return isBreak;
}

/**
 * Get the multifield value
 * 
 * @param $this
 * @returns {String}
 */
function getValue($this){
    var value = "";
    if($this.hasClass('coral-Select')){
        var id = $this.attr('id');
        var idSelector = "#" + id +" :selected";
		value = $(idSelector).val();
    }else if($this.hasClass('coral-PathBrowser')){
        value = $this.find(':input').val();
    }else if($this.next().attr('name') == './textIsRich'){
        value = $this.val();
        value = $(value).text().length;
    }else if($this.data('usehtml5')){
		value = $this.parents('.cq-FileUpload-thumbnail').children('.cq-FileUpload-thumbnail-img').children();
    }else{
        value = $this.val();
    }
    return value;
}

/**
 * Showing the custom error label on the failure
 * @param errorLabel
 */
function displayMessage(errorLabel){
	$(window).adaptTo("foundation-ui").alert("Validation Failure", errorLabel);
}

/**
 * Validation for input field
 * 
 * @param $form
 * @returns {Boolean}
 */
function inputFieldValidation($form){
    var flag = true;
	$form.find('input, textarea').each(
        function(){

            if(!currentInputFieldValidation($(this))){
                flag = false;
				return false;	
            }
        });
    return flag;
}

/**
 * Input validation for the non-multifield variables
 * 
 * @param currentField
 * @returns {Boolean}
 */
function currentInputFieldValidation(currentField){

	var isRequired = currentField.attr('data-isRequired') == 'true';

    //validation for images outside multifield
    if(isRequired && currentField.attr('type') == 'file'){
		var isValid = validateImageField(currentField);
        var fieldName = currentField.attr('data-errorlabel');
        if(!isValid){
			$(window).adaptTo("foundation-ui").alert("Validation Failure",
				fieldName + " is mandatory");
		return false;
        }
        return true;
    }

    var maxLength = currentField.attr('data-maxlength');
	var minLength = currentField.attr('data-minlength');
	var fieldName = currentField.attr('data-errorlabel');

    //validation for rte
	var isRTE = currentField.next().attr('name') == './textIsRich';
    if(isRTE){
		var rteContent = currentField.val();
        var rteText = $(rteContent).text().trim();
        var length = rteText.length;
        if(isRequired && (length == 0)){
			displayMessage(fieldName + " is mandatory");
            return false;
        }
        if(length > maxLength){
            displayMessage(fieldName + " cannot exceed " + maxLength + " characters");
			return false;
        }
        if(length < minLength){
            displayMessage(fieldName + " should have more than " + minLength+" characters")
			return false;
        }
        return true;
    }

	//for textfield , textarea , pathfield , selection xtype
	var actualLength = currentField.val().length;
	if (currentField.hasClass("js-coral-pathbrowser-input")) {

		var parentEle = currentField.parent().parent()[0];
		if (parentEle.getAttribute('data-isrequired')) {
			isRequired = true;
            fieldName = parentEle.getAttribute('data-errorlabel');
		}
	}
	var display = (currentField.parents('.coral-Form-fieldwrapper').css('display') != 'none');
	var isNotPartOfMultiField = currentField.parents('.coral-Multifield-input').length == 0;

	if (isNotPartOfMultiField && display && isRequired != undefined
			&& isRequired && actualLength == 0) {
		displayMessage(fieldName + " is mandatory");
		return false;
	}
	if (isNotPartOfMultiField && display && maxLength != undefined
			&& actualLength > maxLength) {
		displayMessage(fieldName + " cannot exceed " + maxLength + " characters");
		return false;
	}
	if (isNotPartOfMultiField && display && minLength != undefined
			&& actualLength < minLength) {
		displayMessage(fieldName + " should have more than " + minLength
						+ " characters");
		return false;
	}
	return true;
}

function validateImageField(currentField){

	var parentElement = currentField.parents('.cq-FileUpload-thumbnail-dropHere');
    var src = parentElement.siblings('.cq-FileUpload-thumbnail-img').find('[src]');
    var display = parentElement.siblings('.cq-FileUpload-thumbnail-img').parents('span').parent().css('display') != 'none';
    if(!currentField.parents().hasClass('coral-Multifield-list') && display && src.length == 0){
		return false;
    }else{
		return true;
    }
}
