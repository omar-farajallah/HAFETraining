function attachSelectBoxListener(){

	$('.cq-dialog-content').find('select').each(function(){

			var selectBoxSelector = 'name';
         	var selectorVal = $(this).attr(selectBoxSelector);
			var selectorObj = $("[" + selectBoxSelector + "='" + selectorVal + "']").closest(".coral-Select");

         	if(selectorObj.attr('data-loadcontent')){

				var $loadFunction = new Function(selectorObj.attr('data-loadcontent'));
                $loadFunction();
            }

         	selectorObj.on('selected',function(){
                if($(this).attr('data-selectionchanged')){
					var $function = new Function($(this).attr('data-selectionchanged'));
                	$function();
                }
            });

     	});
}

function attachCheckBoxListener(){
    $('.cq-dialog-content').find(':checkbox').each(function(){

			var checkBoxSelector = 'name';
         	var checkBoxVal = $(this).attr(checkBoxSelector);
			var checkBoxObj = $("[" + checkBoxSelector + "='" + checkBoxVal + "']");

         	if(checkBoxObj.attr('data-loadcontent')){

				var $loadFunction = new Function(checkBoxObj.attr('data-loadcontent'));
                $loadFunction();
            }

         	checkBoxObj.on('change',function(){
                if($(this).attr('data-selectionchanged')){
					var $function = new Function($(this).attr('data-selectionchanged'));
                	$function();
                }
            });

     	});

}

function attachOnSubmitListener(event){

	var $form = $(".cq-dialog-content");
    var componentName = $form.attr('data-componentname');
    if(!validateForm($form,componentName)){
        return false;
    }
    return true;
}

function attachListenersForFieldsInsideMultiField($multiField){

    attachSelectBoxListenersInsideMultiField($multiField);
    attachAddFieldDisable($multiField);

}

function attachListenersForMultiFieldRemove($multiField){
$multiField.find('.js-coral-Multifield-add').removeAttr('disabled');
}

function attachAddFieldDisable($multiField){

   var maxcount = $multiField.attr('data-maxcount');
    var length = $multiField.find('ol:first').children().length;
    if(length >= maxcount){
        $multiField.find('.js-coral-Multifield-add:last').attr('disabled','disabled');
    }



}

function attachSelectBoxListenersInsideMultiField($multiField){
    var selectBoxInMultifield = $multiField.find('.js-coral-Multifield-input:last').find('.coral-Select');
    selectBoxInMultifield.each(function(){
        var selectorObj = $(this);
        if(selectorObj.attr('data-loadcontent')){
			var $loadFunction = new Function(selectorObj.attr('data-loadcontent'));
            $loadFunction();
        }

        selectorObj.on('selected',function(event){

                if($(this).attr('data-selectionchanged')){
					var $function = new Function('event' , $(this).attr('data-selectionchanged'));
                	$function(event);
                }
            });

    });

}

function attachDialogLoadListeners(){

	var $form = $('.cq-dialog-content');
    var loadContent = $form.attr('data-loadcontent');
    if(loadContent){
			var $loadFunction = new Function(loadContent);
            $loadFunction();
        }

}
