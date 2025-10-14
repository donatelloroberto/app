function getParameterByName(name,url) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(url);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
	
	
function handleOpenURL(url) {
  	setTimeout(function() {
  		
	  	var type = getParameterByName('type',url);
		var prodId = getParameterByName('id',url);
	    
	   	if(prodId) {
	   			getVideo(prodId, type);
    	}
    
  }, 3000);
}
