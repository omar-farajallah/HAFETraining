use(function() {
	var brand = 'platform';

	var currentPageTemplate = currentPage.getContentResource()
			.getResourceType();
	var nodeNameSplit = currentPageTemplate.split('/');
	currentPageTemplate = nodeNameSplit[nodeNameSplit.length - 1];

	return {
		// anything exposed here can be used inside your template
		brandName : brand,
		template : currentPageTemplate
	};
});
