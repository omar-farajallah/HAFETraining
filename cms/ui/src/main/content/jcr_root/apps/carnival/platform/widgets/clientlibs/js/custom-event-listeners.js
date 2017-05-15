//Attaching events to select of touch ui dialogs also dynamically populates the checkbox and select
(function($, $document) {
	"use strict";

 $document.on("dialog-ready", function() {    

     attachSelectBoxListener();	
     attachCheckBoxListener();
	 attachDialogLoadListeners();

});


})($, $(document));