function titleDecoratorOnChange() {
	var $form = $('.cq-dialog');
	var titleDecorator = $form.find('[name="./titleH1/dividerType"]').val();
	var titleDecoratorText = $form.find('[name="./titleH1/dividerText"]')
			.closest('div');
	if (titleDecorator == 'icon') {
		titleDecoratorText.hide();
	} else if (titleDecorator == 'text') {
		titleDecoratorText.show();
	}
}


// show/hide our target depending on toggle state of isCampaignHeaderRequired
function isCampaignHeaderRequired() {
	var $form = $('.cq-dialog');
    var value =$(".showhide").prop("checked") ? $(".showhide").val() : "";
    var headerText = $form.find('[name="./titleH1/campaignHeaderText"]').closest('div');
    var campaignHeaderValidation = $("#campaign-header-text");

	if (value == 'true') {
		headerText.show();
        campaignHeaderValidation.attr('aria-invalid','true');
        campaignHeaderValidation.attr('aria-required','true');

	} else{
		headerText.hide();
        campaignHeaderValidation.attr('aria-invalid','false');
        campaignHeaderValidation.attr('aria-required','false');

	}
}
