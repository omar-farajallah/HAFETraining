use(function() {
	var parentPath = currentPage.getAbsoluteParent(2);
	var homePagePath = parentPath.getPath();
	var utilityNavPath = parentPath.getPath()
			+ "/jcr:content/utilityNav/utilityNav";
	var globalFooterPath = parentPath.getPath() + "/jcr:content/footer/footer";
    var globalFooterQuotePath = parentPath.getPath() + "/jcr:content/footer/footerQuote";
	var globalHeaderPath = parentPath.getPath()
			+ "/jcr:content/globalHeader/globalHeader";
	var brand = currentPage.getAbsoluteParent(1).getName();
	if (brand == 'hal') {
		brand = 'platform';
	}
	var currentPageTemplate = currentPage.getContentResource()
			.getResourceType();
	var nodeNameSplit = currentPageTemplate.split('/');
	currentPageTemplate = nodeNameSplit[nodeNameSplit.length - 1];

	return {
		// anything exposed here can be used inside your template
		homePage : homePagePath,
		globalFooterPath : globalFooterPath,
        globalFooterQuotePath : globalFooterQuotePath,
		globalHeaderPath : globalHeaderPath,
		utilityNavPath : utilityNavPath,
		brandName : brand,
		template : currentPageTemplate
	};
});
