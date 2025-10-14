//==========================================================================================
//========        Theese are a CallBack Functions, which are called                =========
//========                   when an action succedes.                              =========
//==========================================================================================



//======================================================================
//====== Is called on _chromeCastStartScan_                    =========
//======================================================================
startScan = function () {
    chromeCastAreThereDevicesAvailable();
    setInterval("chromeCastAreThereDevicesAvailable()", 5000);
}

//======================================================================
//====== Is called on _chromeCastShowActiveDevices_            =========
//====== Returns :                                             =========
//====== - data.devicesList - array of devices friendly names  =========
//======                                                       =========
//======      null - if no devices available                   =========
//======      array of strings - if there are some devices     =========
//======                                                       =========
//====== - data.deviceName - connected device name             =========
//======                                                       =========
//======      null - if device is not connected                =========
//======      string - if device is connected                  =========
//======                                                       =========
//======================================================================
showDeviceList = function (data) {
    var list = data.devicesList;
    var deviceName = data.deviceName;
    chrome_actionSheet.removeAll();
    if (list == null){
        chrome_actionSheet.add({
                               text: 'There are no devices available...',
                               ui: 'decline',
                               handler: function() {
                                    chrome_actionSheet.hide();
                               }
        });
    }
    else {
        for (i = 0; i < list.length; i += 1){
            chrome_actionSheet.add({
                                   text: list[i],
                                   handler: function(data) {
                                        chromeCastConnectToDevice(data.getText());
                                   }
            });
        }
        
        chrome_actionSheet.add({
                               text: 'Cancel',
                               ui: 'decline',
                               handler: function() {
                                    chrome_actionSheet.hide();
                               }
       });
    }
    
    Ext.Viewport.add(chrome_actionSheet);
    chrome_actionSheet.show();
}

//======================================================================
//====== Is called on _chromeCastConnectToDevice_              =========
//======================================================================
connectToDevice = function () {
    glb_ios_glb_session = true;
    chrome_actionSheet.disable();
    if(Ext.Viewport.getActiveItem().id == 'Video' || Ext.Viewport.getActiveItem().id == 'Video2') {
        setMask('Connecting...');
        clearInterval(glb_chromecast_status);
        glb_chromecast_status = setInterval(chromeCastIsDeviceConnected, 1000);
    }
    else {
        chrome_actionSheet.hide();
        chrome_actionSheet.enable();
    }
}

//======================================================================
//====== Is called on _chromeCastDisconnectFromDevice_         =========
//======================================================================
disconnectFromDevice = function() {
    
}

//======================================================================
//====== Is called on _chromeCastChangeVolume_                 =========
//======================================================================
changeVolume = function() {
    
}

//======================================================================
//====== Is called on _chromeCastCastVideo_                    =========
//======================================================================
castVideo = function () {
    clearInterval(glb_chromecast_update);
    glb_chromecast_update = setInterval(chromeCastGetStatusStreamVolume, 1000);
}

//======================================================================
//====== Is called on _chromeCastPlayPause_                    =========
//======================================================================
playPause = function () {
    
}

stop = function () {
    
}

//======================================================================
//====== Is called on _chromeCastMuteUnmute_                   =========
//======================================================================
muteUnmute = function () {
    
}

//======================================================================
//====== Is called on _chromeCastIsDeviceConnected_            =========
//====== Returns :                                             =========
//====== - data.isDeviceConnected - bool, that shows if there  =========
//======   are any devices connected                           =========
//======                                                       =========
//======      true - there is device connected                 =========
//======      false - there are no devices                     =========
//======                                                       =========
//======================================================================
isDeviceConnected = function (data) {
    var isDeviceConnected = data.isDeviceConnected;

    if(data.isDeviceConnected=='true') {
        getVideo(glb_current_video,glb_video_type,true,true);
        chrome_actionSheet.hide();
        chrome_actionSheet.enable();
        clearInterval(glb_chromecast_status);
    }
    else {
        //alert(data.isDeviceConnected);
    }
}

//======================================================================
//====== Is called on _chromeCastAreThereDevicesAvailable_     =========
//====== Returns :                                             =========
//====== - data.areThereDevicesAvailable - bool, that shows if =========
//======   there are any devices available                     =========
//======                                                       =========
//======      true - there are devices available               =========
//======      false - there are no devices                     =========
//======                                                       =========
//======================================================================
areThereDevicesAvailable = function (data) {
    var areThereDevicesAvailable = data.areThereDevicesAvailable;
    
    if (areThereDevicesAvailable == 'true'){
        Ext.getCmp('chomecast_btn1').show('fade');
        Ext.getCmp('chomecast_btn2').show('fade');
        Ext.getCmp('chomecast_btn3').show('fade');
    }
    else {
        Ext.getCmp('chomecast_btn1').setHidden(true);
        Ext.getCmp('chomecast_btn2').setHidden(true);
        Ext.getCmp('chomecast_btn3').setHidden(true);
    }
}

//======================================================================
//====== Is called on _chromeCastGetStatusStreamVolume_        =========
//====== Returns :                                             =========
//====== - data.isConnected - bool is device connected         =========
//======                                                       =========
//======      true - if device is connected                    =========
//======      false - if no devices connected                  =========
//======                                                       =========
//====== - data.isCasting - if currently casting media         =========
//======                                                       =========
//======      true - is casting media                          =========
//======      false - is not casting media                     =========
//======                                                       =========
//====== - data.streamPositionInSeconds - get stream position  =========
//======   in seconds                                          =========
//======                                                       =========
//======      string - string of seconds                       =========
//======                                                       =========
//====== - data.streamDurationInSeconds - get video length in  =========
//======   seconds                                             =========
//======                                                       =========
//======      string - string of seconds                       =========
//======                                                       =========
//====== - data.streamPosition - get stream position in next   =========
//======   format: 1:56:20                                     =========
//======                                                       =========
//======      string - string of hours/minutes/seconds         =========
//======                                                       =========
//====== - data.streamDuration - get video length in next      =========
//======   format: 1:56:20                                     =========
//======                                                       =========
//======      string - string of hours/minutes/seconds         =========
//======                                                       =========
//====== - data.playerState - get player state (has 3 possible =========
//======   values)                                             =========
//======                                                       =========
//======      Playing - video is currently playing             =========
//======      Buffering - video is currently buffering         =========
//======      Paused - video is paused                         =========
//======                                                       =========
//====== - data.deviceVolume - get device volume value         =========
//======                                                       =========
//======      string - string of volume value                  =========
//======                                                       =========
//======================================================================
getStatusStreamVolume = function (data) {
    var isConnected = data.isConnected;
    var isCasting = data.isCasting;
    var streamPositionInSeconds = data.streamPositionInSeconds;
    var streamDurationInSeconds = data.streamDurationInSeconds;
    var streamPosition = data.streamPosition;
    var streamDuration = data.streamDuration;
    var playerState = data.playerState;
    var deviceVolume = data.deviceVolume;
    
    glb_ios_playerState = playerState;
    
    if(isConnected == 'true')
        glb_ios_glb_session = true;
    else {
        glb_ios_glb_session = null;
    }
    
    if (isCasting == 'true')
        glb_ios_currentMedia = true;
    else {
        glb_ios_currentMedia = null;
    }
    
    if (isConnected == 'true'){
        if (isCasting == 'true'){
            glb_ios_currentMedia = true;
            var streamPositionFloat = parseFloat(streamPositionInSeconds.replace(",", "."));
            var streamDurationFloat = parseFloat(streamDurationInSeconds.replace(",", "."));
            
            glb_chromecast_listeners = false;
            var pos = parseInt(100 * streamPositionFloat / streamDurationFloat);
            
            var chrome_time = moment().startOf('day')
            .seconds(parseInt(streamPositionFloat))
            .format('HH:mm:ss');
            
            var chrome_duration = moment().startOf('day')
            .seconds(parseInt(streamDurationFloat))
            .format('HH:mm:ss');
            
            if(pos >= 0) {
                Ext.getCmp('chrome_cast_seeker').setValue(pos);
                Ext.get('chrome_time').setHtml(chrome_time);
                Ext.get('chrome_duration').setHtml(chrome_duration);
            }
            glb_chromecast_listeners =  true;
        }
    }
}

//======================================================================
//====== Is called on _chromeCastChangeStreamPosition_         =========
//======================================================================

changeStreamPosition = function () {

    
}





//==========================================================================================
//========           Theese are a Plugin Functions, which you should call          =========
//========           to interact with Objective C classes that work with           =========
//========                             ChromeCast SDK                              =========
//==========================================================================================




chromeCastStartScan = function() {
    var dateStr = new Date().toJSON();
    cordova.exec(
                 function callback(data) {
                 startScan();
                 //contentsDiv.innerHTML = 'Scan Started';
                 //console.log('Start Scan Method ' + dateStr);
                 },
                 function errorHandler(err) {
                 alert('Error');
                 },
                 'ChromeCastPlugin',
                 'cordovaStartScan',
                 [ dateStr ]
                 );
}


chromeCastShowActiveDevices = function() {

    cordova.exec(
                 function callback(data) {
                 showDeviceList(data.dataDic);
                 },
                 function errorHandler(err) {
                 glb_ios_glb_session = null;
                 },
                 'ChromeCastPlugin',
                 'cordovaShowActiveDevices',
                 [  ]
                 );
}

//======================================================================
//====== This method should reciev device friendly name you want  ======
//====== to connect to.                                           ======
//======================================================================
chromeCastConnectToDevice = function(value) {
    cordova.exec(
                 function callback(data) {
                 connectToDevice();
                 },
                 function errorHandler(err) {
                 alert('Error');
                 },
                 'ChromeCastPlugin',
                 'cordovaConnectToDeviceByFriendlyName',
                 [ value ]
                 );
}

chromeCastDisconnectFromDevice = function() {
    cordova.exec(
                 function callback(data) {
                 disconnectFromDevice();
                 },
                 function errorHandler(err) {
                 alert('Error');
                 },
                 'ChromeCastPlugin',
                 'cordovaDisconnectFromDevice',
                 [  ]
                 );
}

//======================================================================
//====== This function should recive a value in [0.0 .. 1.0]      ======
//====== range.                                                   ======
//======================================================================
chromeCastChangeVolume = function(value) {
    cordova.exec(
                 function callback(data) {
                 changeVolume();
                 },
                 function errorHandler(err) {
                 alert('Error');
                 },
                 'ChromeCastPlugin',
                 'cordovaChangeVolumeLevel',
                 [ value ]
                 );
}
//======================================================================
//====== This method should recieve 4 ESCAPED strings:            ======
//====== 1. video url                                             ======
//====== 2. thumbnail image url                                   ======
//====== 3. video title                                           ======
//====== 4. video subtitle                                        ======
//======================================================================
function chromeCastCastVideo(url, thumbnailUrl, title, subtitle) {

    var urlUn = unescape(url);
    var thumbnailUrlUn = unescape(thumbnailUrl);
    var titleUn = unescape(title);
    var subtitleUn = unescape(subtitle);

    cordova.exec(
                 function callback(data) {
                 castVideo();
                 },
                 function errorHandler(err) {
                    glb_ios_glb_session = null;
                    glb_ios_currentMedia = null;
                 },
                 'ChromeCastPlugin',
                 'cordovaCastVideoByUrlThumbnailurlTitleSubtitle',
                 [ urlUn, thumbnailUrlUn, titleUn, subtitleUn ]
                 );
}

chromeCastPlayPause = function() {
    cordova.exec(
                 function callback(data) {
                 playPause();
                 },
                 function errorHandler(err) {
                    glb_ios_glb_session = null;
                    glb_ios_currentMedia = null;
                 },
                 'ChromeCastPlugin',
                 'cordovaPlayPause',
                 [ ]
                 );
}

chromeCastStop = function() {
    cordova.exec(
                 function callback(data) {
                 stop();
                 },
                 function errorHandler(err) {
                 glb_ios_glb_session = null;
                 glb_ios_currentMedia = null;
                 },
                 'ChromeCastPlugin',
                 'cordovaStop',
                 [ ]
                 );
}

chromeCastMuteUnmute = function() {
    cordova.exec(
                 function callback(data) {
                 muteUnmute();
                 },
                 function errorHandler(err) {
                 glb_ios_glb_session = null;
                 glb_ios_currentMedia = null;
                 },
                 'ChromeCastPlugin',
                 'cordovaMuteUnmute',
                 [ ]
                 );
}


chromeCastIsDeviceConnected = function () {
    cordova.exec(
                 function callback(data) {
                 isDeviceConnected(data);
                 },
                 function errorHandler(err) {
                 alert('Error');
                 },
                 'ChromeCastPlugin',
                 'cordovaIsDeviceConnected',
                 [ ]
                 );

}

chromeCastAreThereDevicesAvailable = function () {
    cordova.exec(
                 function callback(data) {
                 areThereDevicesAvailable(data);
                 },
                 function errorHandler(err) {
                 alert('Error');
                 },
                 'ChromeCastPlugin',
                 'cordovaAreThereDevicesAvailable',
                 [ ]
                 );
    
}



chromeCastGetStatusStreamVolume = function () {
    cordova.exec(
                 function callback(data) {
                 getStatusStreamVolume(data.status);
                 },
                 function errorHandler(err) {
                 },
                 'ChromeCastPlugin',
                 'cordovaGetStatusStreamVolume',
                 [ ]
                 );
}
//======================================================================
//====== This function should recive a value in [0.0 .. 1.0]      ======
//====== range. It is a percentage of the stream.                 ======
//======================================================================
chromeCastChangeStreamPosition = function (value) {
    cordova.exec(
                 function callback(data) {
                 changeStreamPosition();

                 },
                 function errorHandler(err) {
                 },
                 'ChromeCastPlugin',
                 'cordovaChangeStreamPosition',
                 [ value ]
                 );
}

