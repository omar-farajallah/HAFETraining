function toggleAriaRequired(inputField, isRequired){
	if(isRequired){
		inputField.attr('aria-required', 'true');
	}else{
		inputField.removeAttr('aria-required');
	}
	/*var api = inputField.adaptTo("foundation-validation");
    api.checkValidity();
    api.updateUI();*/
}

/*function toggleAriaRequiredonTab(tabField, isRequired){
    	tabField.find("input, select, textarea").each(function(index,inputField){
        if(isRequired){
            inputField.setAttribute('aria-required', 'true');
        }else{
            inputField.removeAttribute('aria-required');
        }
    });
	/*var api = inputField.adaptTo("foundation-validation");
    api.checkValidity();
    api.updateUI();
}*/