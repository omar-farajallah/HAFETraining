(function () {
    var DATA_EAEM_NESTED = "data-custom-multifield",
        CFFW = ".coral-Form-fieldwrapper",
        THUMBNAIL_IMG_CLASS = "cq-FileUpload-thumbnail-img",
        SEP_SUFFIX = "-",
        SEL_FILE_UPLOAD = ".coral-FileUpload",
        SEL_FILE_REFERENCE = ".cq-FileUpload-filereference",
        SEL_FILE_NAME = ".cq-FileUpload-filename",
        SEL_FILE_MOVEFROM = ".cq-FileUpload-filemovefrom";

    function getStringBeforeAtSign(str){
        if(_.isEmpty(str)){
            return str;
        }

        if(str.indexOf("@") != -1){
            str = str.substring(0, str.indexOf("@"));
        }

        return str;
    }

    function getStringAfterAtSign(str){
        if(_.isEmpty(str)){
            return str;
        }

        return (str.indexOf("@") != -1) ? str.substring(str.indexOf("@")) : "";
    }

    function getStringAfterLastSlash(str){
        if(!str || (str.indexOf("/") == -1)){
            return "";
        }

        return str.substr(str.lastIndexOf("/") + 1);
    }

    function getStringBeforeLastSlash(str){
        if(!str || (str.indexOf("/") == -1)){
            return "";
        }

        return str.substr(0, str.lastIndexOf("/"));
    }

    function removeFirstDot(str){
        if(str.indexOf(".") != 0){
            return str;
        }

        return str.substr(1);
    }

    function modifyJcrContent(url){
        return url.replace(new RegExp("^" + Granite.HTTP.getContextPath()), "")
                .replace("_jcr_content", "jcr:content");
    }

    function isSelectOne($field) {
        return !_.isEmpty($field) && ($field.prop("type") === "select-one");
    }

    function setSelectOne($field, value) {
        var select = $field.closest(".coral-Select").data("select");

        if (select) {
            select.setValue(value);
        }
    }

    function isRTE($field){
                                return ($field.next().attr('name') == './textIsRich');
    }

    function isCheckbox($field) {
        return !_.isEmpty($field) && ($field.prop("type") === "checkbox");
    }

    function setCheckBox($field, value) {
        if(value==='true'){
         $field.attr('checked','');
       }else{
         $field.removeAttr('checked');
      }
      $field.val(value);
    }
	
	var fillNestedFields = function($multifield, valueArr , fKey){
                _.each(valueArr, function(record, index){
                    $multifield.find(".js-coral-Multifield-add").click();
                     var $field = $($multifield.find("[name='./" + fKey + "']")[index]);
                     $field.val(record);
                    });
                };
   

    function setWidgetValue($multifield , $field, value) {
        if (_.isEmpty($field)) {
            return;
        }if (isSelectOne($field)) {
            setSelectOne($field, value);
            attachListenersForFieldsInsideMultiField($multifield);
        } else if (isCheckbox($field)) {
            setCheckBox($field, value);
        } else if(isRTE($field)){
            $field.val(value);
            $field.next().next().html(value);
        }else if($field.prev().hasClass('js-coral-DatePicker-input')){
			$field.prev().val(value);
			$field.val(value);
        }else {
            $field.val(value);
        }
    }

    /**
     * Removes multifield number suffix and returns just the fileRefName
     * Input: paintingRef-1, Output: paintingRef
     *
     * @param fileRefName
     * @returns {*}
     */
    function getJustName(fileRefName){
        if(!fileRefName || (fileRefName.indexOf(SEP_SUFFIX) == -1)){
            return fileRefName;
        }

        var value = fileRefName.substring(0, fileRefName.lastIndexOf(SEP_SUFFIX));

        if(fileRefName.lastIndexOf(SEP_SUFFIX) + SEP_SUFFIX.length + 1 == fileRefName.length){
            return value;
        }

        return value + fileRefName.substring(fileRefName.lastIndexOf(SEP_SUFFIX) + SEP_SUFFIX.length + 1);
    }

    function getMultiFieldNames($multifields){
        var mNames = {}, mName;

        $multifields.each(function (i, multifield) {
            mName = $(multifield).children("[name$='@Delete']").attr("name");
            mName = mName.substring(0, mName.indexOf("@"));
            mName = mName.substring(2);
            mNames[mName] = $(multifield);
        });

        return mNames;
    }

    function buildMultiField(data, $multifield, mName){
        if(typeof data == 'string'){
         data = [data];
        }
        if(_.isEmpty(mName) || _.isEmpty(data)){
            return;
        }

        _.each(data, function(value, key){
            if(key == "jcr:primaryType"){
                return;
            }

            $multifield.find(".js-coral-Multifield-add:last").click();
              //value = JSON.parse(value);
            _.each(value, function(fValue, fKey){
                if(fKey == "jcr:primaryType"|| _.isObject(fValue)){
                    return;
                }else{	
               	 var $field = $multifield.find("[name='./" + fKey + "']").last();

                	if(_.isEmpty($field)){
                	    return;
                	}
                setWidgetValue($multifield , $field, fValue);
                }
            });
			
			_.each(value, function(fValue, fKey){
                if(fKey == "jcr:primaryType"){
                    return;
                }else if(_.isObject(fValue)){
					fillNestedFields($multifield.find('[data-init="multifield"]:last'), fValue ,fKey);
				}
            });
			
        });
    }

    function buildMultiFieldForInclude(data, $multifield, mName){
        if(typeof data == 'string'){
          data = [data];
        }
        if(_.isEmpty(mName) || _.isEmpty(data)){
            return;
        }

        _.each(data, function(value, key){
            if(key == "jcr:primaryType"){
                return;
            }

            $multifield.find(".js-coral-Multifield-add:last").click();
            _.each(value, function(fValue, fKey){
                if(fKey == "jcr:primaryType"){
                    return;
                }
                if(_.isObject(fValue)){
                    fillNestedFields($multifield.find('[data-init="multifield"]:last'), fValue , fKey);
                }else{
					var fieldName = $multifield.find('.coral-Form-fieldset').data('name');
                	var $field = $multifield.find("[name='" +fieldName + "/" + fKey + "']").last();

                	if(_.isEmpty($field)){
                    	return;
                	}
					setWidgetValue($multifield , $field, fValue);
                }

            });
        });
    }

    function buildImageField($multifield, mName){

        $multifield.find(".coral-FileUpload:last").each(function () {

            var $element = $(this), widget = $element.data("fileUpload"),
                resourceURL = $element.parents("form.cq-dialog").attr("action"),
                counter = $multifield.find(SEL_FILE_UPLOAD).length;

            if (!widget) {
                return;
            }

            var fuf = new Granite.FileUploadField(widget, resourceURL);

            addThumbnail(fuf, mName, counter-1);
        });
    }

    function addThumbnail(imageField, mName, counter){
        var $element = imageField.widget.$element,
            $thumbnail = $element.find("." + THUMBNAIL_IMG_CLASS),
            thumbnailDom;

        $thumbnail.empty();

        $.ajax({
            url: imageField.resourceURL + ".2.json",
            cache: false
        }).done(handler);

        function handler(data){
            var fName =  getJustName(getStringAfterLastSlash(imageField.fieldNames.fileName)),
                fRef = getJustName(getStringAfterLastSlash(imageField.fieldNames.fileReference));

            if(isFileNotFilled(data, counter, fRef)){
                return;
            }
                                                if(typeof data[mName[0]][mName[1]] == 'string'){
                                                                data[mName[0]][mName[1]] = [data[mName[0]][mName[1]]];
                                                }
                                                var x = JSON.parse(data[mName[0]][mName[1]][counter]);
            var fileName = x[fName],
                fileRef = x[fRef];

            if (!fileRef) {
                return;
            }

            if (imageField._hasImageMimeType()) {
                imageField._appendThumbnail(fileRef, $thumbnail);
            }

            var $fileName = $element.find("[name=\"" + imageField.fieldNames.fileName + "\"]"),
                $fileRef = $element.find("[name=\"" + imageField.fieldNames.fileReference + "\"]");

            $fileRef.val(fileRef);
            $fileName.val(fileName);
        }

        function isFileNotFilled(data, counter, fRef){
            if(data[mName[0]] == undefined){
				return true;
            }
            return _.isEmpty(data[mName[0]][mName[1]])
                    || _.isEmpty(data[mName[0]][mName[1]][counter]);

        }
    }

    //reads multifield data from server, creates the nested composite multifields and fills them
    function addDataInFields() {
        $(document).on("dialog-ready", function() {
            var $multifields = $("[" + DATA_EAEM_NESTED + "]");
            
            if(_.isEmpty($multifields)){
                return;
            }
            $('.js-coral-Multifield-remove').click();

            workaroundFileInputPositioning($multifields);

            var mNames = getMultiFieldNames($multifields),
                $form = $(".cq-dialog"),
                actionUrl = $form.attr("action") + ".infinity.json";

            $.ajax(actionUrl).done(postProcess).fail(errorFunction);

            function errorFunction(){
					_.each(mNames, function($multifield, mName){
                    $multifield.on("click", ".js-coral-Multifield-add", function () {
                      attachListenersForFieldsInsideMultiField($multifield);
                    });
                    $multifield.on("click", ".js-coral-Multifield-remove", function () {
                      attachListenersForMultiFieldRemove($multifield);
                    });

            });
            }

            function postProcess(data){
                _.each(mNames, function($multifield, mName){
                    $multifield.on("click", ".js-coral-Multifield-add", function () {
                      attachListenersForFieldsInsideMultiField($multifield);
                    });
                    $multifield.on("click", ".js-coral-Multifield-remove", function () {
                      attachListenersForMultiFieldRemove($multifield);
                    });
                    mName = mName.split('/');
                    if(data[mName[0]] == undefined){
						buildMultiField(null, $multifield, mName);
                    }else{
                        if(mName[1] == undefined){
                            buildMultiFieldForInclude(data[mName[0]], $multifield, mName)
                          }else{
                    		buildMultiField(data[mName[0]][mName[1]], $multifield, mName);
                    }


                    }
                });
            }
        });
    }

    function workaroundFileInputPositioning($multifields){
        //to workaround the .coral-FileUpload-input positioning issue
        $multifields.find(".js-coral-Multifield-add")
                    .css("position" ,"relative");
    }

    function collectImageFields($form, $fieldSet, record){
        var $fields = $fieldSet.children().children(CFFW).not(function(index, ele){
            return $(ele).find(".coral-FileUpload").length == 0;
        });


        $fields.each(function (j, field) {
            var $field = $(field),
                $widget = $field.find(".coral-FileUpload").data("fileUpload");

            if(!$widget){
                return;
            }
            var $fileName = $widget.$element.find(".coral-FileUpload-input");
            var $fileRef = $widget.$element.find(".cq-FileUpload-filereference");
            var name = getJustName($fileRef.attr("name"));
            var namePath = $fieldSet.data("name") ;
            name = name.substring(2);
            record[name] = $fileRef.val();
            $field.remove();
        });
    }
	
	function writeToCRX(name , data , $form){
		
		$('<input />').attr('type', 'hidden')
                                .attr('name', name)
                                .attr('value', data)
                                .appendTo($form);
		
	}
	
	function collectDataForNonNestedMultiField($field,$fieldSet,extensionName,fieldSetName , counter,$form){
		
						var name = $field.find("[name]").attr('name');
                        if(!name){
                            return;
                        }
						name = name.substring(2);
						record[name] = fillValue($form, $fieldSet.data("name"), $field.find("[name]"));
						
                        var dataAttributeName = fieldSetName + "/" + (extensionName + counter) + "/" + name.substring(name.lastIndexOf('/')+1);
						writeToCRX(dataAttributeName,record[name],$form);
		
	}
	
	function collectDataForNestedMultiField($nestedMultiField,$fieldSet,extensionName,fieldSetName , counter,$form){
						getRecordFromMultiField($nestedMultiField,extensionName,fieldSetName , counter,$form);
						
	}
	
	function collectDataForNormalMultifields($fieldSet,extensionName,fieldSetName , counter,$form){
		
				$fields = $fieldSet.children().children(CFFW).not(function(index, ele){
					return $(ele).find(SEL_FILE_UPLOAD).length > 0;
				});

				$fields.each(function (j, field) {
                    $nestedMultiField = $(field).find("[data-init='multifield']");
					
					if($nestedMultiField.length == 0){
						collectDataForNonNestedMultiField($(field),$fieldSet,extensionName,fieldSetName , counter,$form);
					}else{
						collectDataForNestedMultiField($nestedMultiField,$fieldSet,extensionName,fieldSetName , counter,$form);
					}

				});
		
	}
	
	function writeFieldsToCRX($fields,$fieldSet,extensionName,fieldSetName , counter,$form){
		
		$fields.each(function (j, field) {
				collectDataForNonNestedMultiField($(field),$fieldSet,extensionName,fieldSetName , counter,$form);
			});
		
	}
	
	function collectDataForIncludedMultiField($fieldSet,extensionName,fieldSetName , counter,$form){
		
		$fields = $fieldSet.children().children().children(CFFW).not(function(index, ele){
            	return $(ele).find(SEL_FILE_UPLOAD).length > 0;
        	});
		writeFieldsToCRX($fields,$fieldSet,extensionName,fieldSetName , counter,$form);
		
	}
	
    function collectNonImageFields($form, $fieldSet, record , fieldSetName , counter){
        var isInclude = $fieldSet.children().children(CFFW);
        var $fields = null;
		var extensionName = "item-";
        if(isInclude.length == 0){
			collectDataForIncludedMultiField($fieldSet,extensionName,fieldSetName , counter,$form);
		} else{
			collectDataForNormalMultifields($fieldSet,extensionName,fieldSetName , counter,$form);	
		}
}	
    var getRecordFromMultiField = function($multifield,extensionName,fieldSetName , counter,$form){
        var $fieldSets = $multifield.find("[class='coral-Form-fieldset']");

        var record=[], $fields, name;

        $fieldSets.each(function (i, fieldSet) {

            $fields = $(fieldSet).find("[name]");
            $fields.each(function (j, field) {
				var name = $(field).attr('name').substring(2);
				var dataAttributeName = fieldSetName + "/" + (extensionName + counter) + "/" + name.substring(name.lastIndexOf('/')+1);
				writeToCRX(dataAttributeName,fillValue(null , "" ,$(field)),$form);
                
            });

        });
    };

    function fillValue($form, fieldSetName, $field){
        var name = $field.attr("name");

        if (!name) {
            return;
        }
        if (name.indexOf("./") == 0) {
            name = name.substring(2);
        }
		if($field.attr('type') === 'checkbox'){
			var value = $field.eq(0).is(':checked');
            $field.remove();
            return value;
        }
        $field.remove();
		return $field.val();
    }

    //collect data from widgets in multifield and POST them to CRX
    function collectDataFromFields(){
        $(document).on("click", ".cq-dialog-submit", function (event) {
			
			var $multifields = $("[" + DATA_EAEM_NESTED + "]");
            if(_.isEmpty($multifields)){
                return;
            }
			if(!attachOnSubmitListener(event))
            {  
                event.preventDefault();
                return;
            }
            var $form = $(this).closest("form.foundation-form"),
                $fieldSets;

            $multifields.each(function(i, multifield){
                $fieldSets = $(multifield).find("[class='coral-Form-fieldset']");
                    $fieldSets.each(function (counter, fieldSet) {
                    var fieldSetName = $(this).data('name');
                    record = {};
                    collectNonImageFields($form, $(fieldSet), record,fieldSetName , counter+1);
					});
               });
        });
    }

    function overrideGranite_refreshThumbnail(){
        var prototype = Granite.FileUploadField.prototype,
            ootbFunc = prototype._refreshThumbnail;

        prototype._refreshThumbnail = function() {
            var $imageMulti = this.widget.$element.closest("[" + DATA_EAEM_NESTED + "]");

            if (!_.isEmpty($imageMulti)) {
                return;
            }

            return ootbFunc.call(this);
        }
    }

    function overrideGranite_computeFieldNames(){
        var prototype = Granite.FileUploadField.prototype,
            ootbFunc = prototype._computeFieldNames;

        prototype._computeFieldNames = function(){
            ootbFunc.call(this);

            var $imageMulti = this.widget.$element.closest("[" + DATA_EAEM_NESTED + "]");

            if(_.isEmpty($imageMulti)){
                return;
            }

            var fieldNames = {},
                fileFieldName = $imageMulti.find("input[type=file]").attr("name"),
                counter = $imageMulti.find(SEL_FILE_UPLOAD).length;

            _.each(this.fieldNames, function(value, key){
                if(value.indexOf("./jcr:") == 0){
                    fieldNames[key] = value;
                }else if(key == "tempFileName" || key == "tempFileDelete"){
                    value = value.substring(0, value.indexOf(".sftmp")) + getStringAfterAtSign(value);
                    fieldNames[key] = fileFieldName + removeFirstDot(getStringBeforeAtSign(value))
                                        + SEP_SUFFIX + counter + ".sftmp" + getStringAfterAtSign(value);
                }else{
                    fieldNames[key] = getStringBeforeAtSign(value) + SEP_SUFFIX
                                                    + counter + getStringAfterAtSign(value);
                }
            });

            this.fieldNames = fieldNames;

            this._tempFilePath = getStringBeforeLastSlash(this._tempFilePath);
            this._tempFilePath = getStringBeforeLastSlash(this._tempFilePath) + removeFirstDot(fieldNames.tempFileName);
        }
    }

    function performOverrides(){
        overrideGranite_computeFieldNames();
        overrideGranite_refreshThumbnail();
    }

    $(document).ready(function () {
        addDataInFields();
        collectDataFromFields();
    });

    performOverrides();
	 CUI.Multifield = new Class({
        toString: "Multifield",
        extend: CUI.Multifield,

        construct: function (options) {
            this.script = this.$element.find(".js-coral-Multifield-input-template:last");
        },

        _addListeners: function () {
            this.superClass._addListeners.call(this);

            //otb coral event handler is added on selector .js-coral-Multifield-add
            //any nested multifield add click events are propagated to the parent multifield
            //to prevent adding a new composite field in both nested multifield and parent multifield
            //when user clicks on add of nested multifield, stop the event propagation to parent multifield
            this.$element.on("click", ".js-coral-Multifield-add", function (e) {
                e.stopPropagation();
            });

            this.$element.on("drop", function (e) {
                e.stopPropagation();
            });
        }
    });
	CUI.Widget.registry.register("multifield", CUI.Multifield);
})();
