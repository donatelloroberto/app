cordova.define("com.velocitywebworks.cordova.card.io.card.io", function(require, exports, module) {
/**
 * CardIOPGPlugin.js
 * card.io phonegap plugin
 * @Copyright 2013 Cubet Technologies http://www.cubettechnologies.com
 * @author Robin <robin@cubettech.com>
 * @Since 28 June, 2013
 */

//Your response array contain these fields
// redacted_card_number, card_number, expiry_month,expiry_year, cvv, zip

//set your configurations here
var cardIOConfig = {
	'apiKey': '42b38c0ea705443dba3fba2a37081b82',
	'expiry': true,
	'cvv': true,
	'zip': false,
    'confirm': false,
    'showLogo': true,
    'suppressManual': false
};


var CardIOPlugin = function() {};
  
CardIOPlugin.prototype.setKey = function(key) {
    cardIOConfig.apiKey = key;
    console.log(cardIOConfig);
};

CardIOPlugin.prototype.requireExpiry = function(b) {
    cardIOConfig.expiry = b;
    console.log(cardIOConfig);
};

CardIOPlugin.prototype.requireCVV = function(b) {
    cardIOConfig.cvv = b;
    console.log(cardIOConfig);
};

CardIOPlugin.prototype.requireZip = function(b) {
    cardIOConfig.zip = b;
    console.log(cardIOConfig);
};

CardIOPlugin.prototype.requireConfirmation = function(b) {
    cardIOConfig.confirm = b;
    console.log(cardIOConfig);
};

CardIOPlugin.prototype.showLogo = function(b) {
    cardIOConfig.showLogo = b;
    console.log(cardIOConfig);
};

CardIOPlugin.prototype.suppressManual = function(b) {
    cardIOConfig.showLogo = b;
    console.log(cardIOConfig);
};

CardIOPlugin.prototype.scan = function(success, fail) {
    return cordova.exec(function(args) {
    	console.log("card.io scanning completed", args[0]);
        success(args[0].card_number && args[0] || args[0]);
    }, function(args) {
    	console.log("card.io scanning Failed");
        fail(args);
    }, "CardIOPlugin", "execute", [cardIOConfig]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.CardIOPlugin) {
    window.plugins.CardIOPlugin = new CardIOPlugin();
}


//EOF

});
