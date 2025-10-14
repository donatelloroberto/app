var container = document.getElementById("container");
var pushNotification;

document.addEventListener("deviceready", handleDeviceReady, true);

function handleDeviceReady(){

    var push = PushNotification.init({
		android: {
			senderID: "375634245893"
		},
		ios: {
			alert: "true",
			badge: "true",
			sound: "true"
		},
		windows: {}
	});

	push.on('registration', function(data) {
		// data.registrationId
	});

	push.on('notification', function(data) {
		// data.message,
		// data.title,
		// data.count,
		// data.sound,
		// data.image,
		// data.additionalData
		//console.log('push:'+data.message);
	});
}