var glb_youtube_api;
var glb_youtube_video;
var glb_youtube_video_width;
var glb_youtube_video_height;
var glb_youtube_video_id;
var glb_done_playing = false;
var glb_autoplayIndex = 0;
var glb_videojs_id;
var glb_youtubeEmbedId;
var pusher;
var glb_vid_err_handle = true;

// soft update to notify users of app maintenance. comment or uncomment live below to activate
function maintenanceAlertFunc(){
    var maintenanceAlert = confirm("App is currently under maintenance, please check back later. Try again now or close app?");
    if (maintenanceAlert == true) {
        location = location;
    } else {
        alert('Please close the app and retry again later');
        maintenanceAlertFunc();
    }
    return;
 }

function create_pusher() {

    var user_id = '';
    // try/catch needed in pusher. plugin is occasionlly bugger
    try{
        user_id = account.get('user_id');
    }
    catch(err){
        user_id = '0';
        return;
    }

     pusher = new Pusher('99d751f02388a7370cc3', {
        authEndpoint: 'https://api.myvidster.com/austin/seb3.php?my='+user_id+'&to=1&msg=1&token='+glb_msg_token,
        cluster: 'mt1',
      });

                        var my_id1 = user_id;
                        var my_id = "presence-" + String(my_id1);

                        //  try/catch added due to known pusher plugin bugs
                        try{
                                  
                        var presenceChannel = pusher.subscribe(my_id);
                        var count_p = presenceChannel.members.count;
                    
                        presenceChannel.bind('my_event', function(data) {

                            if(data.video_chat){

                                if(document.getElementById("videochat_container_table").style.zIndex == "100" || Ext.os.is('iOS') || glb_cancel_videocall == String(data.from_username) || glb_videoCall == 1){
                                    return;
                                }
                              
                                var from_user = "Accept incoming video call from " + String(data.from_username) +"?";
                                var r = confirm(from_user);
                                if (r == true) {
                                    glb_cancel_videocall = String(data.from_username);
                                    setTimeout(function() {
                                        glb_cancel_videocall = "";
                                    }, 25000);
                                    initializeSession('', data.video_apikey, data.video_session, data.video_token);
                        
                                }else{
                                    glb_cancel_videocall = String(data.from_username);
                                    setTimeout(function() {
                                        glb_cancel_videocall = "";
                                    }, 25000);
                            
                                    }
                               
                            } else{

        
                            if (data.from_user_id == glb_current_messaging && Ext.Viewport.getActiveItem().id == "MsgUser") { 
                                // try/catch for message encoding in special chars
                                try {
                                    var msg1 = escape(data.message);
                                    msg1 = decodeURIComponent(msg1);
                                    msg1 = encodeHTML(msg1);
                                } catch (e) {
                                    var msg1 = encodeURI(data.message);
                                    msg1 = decodeURIComponent(msg1);
                                    msg1 = encodeHTML(msg1);
                                }
                                msg1 = myv_url_share(msg1);
        
                                var days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
                                var d = new Date();
                                var day = days[d.getDay()];
                                var hr = d.getHours();
                                var min = d.getMinutes();
                                min = String(min);
        
                                if (min.length < 2) {
                                    min = '0' + min;
                                }
                                var ampm = hr < 12 ? " AM" : " PM";
        
                                if (hr > 12) {
                                    hr -= 12;
                                } else if (hr === 0) {
                                    hr = 12;
                                }
        
                                var date = d.getDate();
        
                                var time_f = day + " " + hr + ":" + min + ampm;

        
                                list_detailed_msgs_obj.add({
                                    from_u_profile_photo: data.profile_photo,
                                    message: msg1,
                                    to_u_profile_photo: data.profile_photo_to,
                                    from_u_id: data.from_user_id,
                                    to_u: data.to_user_id,
                                    chat_id: data.chat_id,
                                    video_id: data.video_id,
                                    video_thumbnail: data.video_thumbnail,
                                    time: time_f
                                });
        
                                list_detailed_msgs_obj.each(function(item, i) {
                                    if (list_detailed_msgs_obj.getAt(i).get('from_u_id') == user_id) {
                                        // me
                                        item.set('float_u', 'right');
                                    } else {
                                        item.set('float_u', 'left');
                                    }
                                });
                                clearMask();
                                setTimeout(function() {
                                    var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();
                                    scroller.scrollToEnd(true);
                                }, 500);
        
                                read_msg(user_id, account.get('pw'), data.from_user_id, 1);
        
                            } else {
        
                                if (glb_alert == 1 && glb_alert_active == 0 && typeof device == 'undefined' && data.from_user_id != user_id) {

                                    Ext.util.JSONP.request({
                                        url: 'https://api.myvidster.com/mobile_json2.php',
                                        callbackKey: 'callback',
                                        params: {
                                            type: 'check_proMember',
                                            user_id: account.get('user_id'),
                                            token: glb_msg_token,
                                            pkey: account.get('pw')
                                        },
                                        callback: function(success, result) {
                                            if (result) {
                                                if(result.items[0]['pro_mem'] > 0){
                                                    glb_is_pro_ckeck = 1;
                                                }else{
                                                    glb_is_pro_ckeck = 0;
                                                }
                                            }
                                        }
                                    });
        
                                    Ext.util.JSONP.request({
                                        url: 'https://api.myvidster.com/mobile_json2.php',
                                        callbackKey: 'callback',
                                        params: {
                                            type: 'read_message',
                                            user_id: user_id,
                                            token: glb_msg_token,
                                            pkey: account.get('pw'),
                                            from_user_id: 1
                                        },
                                        callback: function(success, result) {
                                            if (result) {
                                                glb_msg_token = result.items[0]['token'];
                                                update_tab_badge(result.items[0]['unread_msgs']);
                                            }
                                        }
                                    });
                                    navigator.vibrate(500);
                                }
                                clearMask();
                            }

                        }

                        });

                    }catch(err){}
}

function load_remote_css(cssId,href) {
	var head  = document.getElementsByTagName('head')[0];
	var link  = document.createElement('link');
	link.id   = cssId;
	link.rel  = 'stylesheet';
	link.type = 'text/css';
	link.href = href;
	link.media = 'all';
	head.appendChild(link);
}

String.prototype.stripSlashes = function() {
    return this.replace(/\\(.)/mg, "$1");
}

function checkConnectionType() {
    var conn = '';
    // try/catch from sterling dev
    try {
        var networkState = navigator.network.connection.type;
        var states = {};
        states[Connection.UNKNOWN] = 'unknown';
        states[Connection.ETHERNET] = 'ethernet';
        states[Connection.WIFI] = 'wiFi';
        states[Connection.CELL_2G] = '2g';
        states[Connection.CELL_3G] = '3g';
        states[Connection.CELL_4G] = '4g';
        states[Connection.CELL] = 'cell';
        states[Connection.NONE] = 'none';
        conn = states[networkState];
    } catch (err) {
        conn = 'unknown';
    }
    return conn;
}

String.prototype.trunc =
    function(n, useWordBoundary) {
        var toLong = this.length > n,
            s_ = toLong ? this.substr(0, n - 1) : this;
        s_ = useWordBoundary && toLong ? s_.substr(0, s_.lastIndexOf(' ')) : s_;
        return toLong ? s_ + '&hellip;' : s_;
    };

Ext.Component.prototype.animateFn = // or Ext.override( Ext.Component, { animateFn:
    function(animation, component, newState, oldState, options, controller) {
        var me = this;
        if (animation && (!newState || (newState && this.isPainted()))) {
            this.activeAnimation = new Ext.fx.Animation(animation);
            this.activeAnimation.setElement(component.element);
            if (!Ext.isEmpty(newState)) {
                var onEndInvoked = false;
                var onEnd = function() {
                    if (!onEndInvoked) {
                        onEndInvoked = true;
                        me.activeAnimation = null;
                        controller.resume();
                    }
                };
                this.activeAnimation.setOnEnd(onEnd);
                window.setTimeout(onEnd, 50 + (this.activeAnimation.getDuration() || 500));
                controller.pause();
            }
            Ext.Animator.run(me.activeAnimation);
        }
    };

function chat_handler() {
    if (glb_current_list['type']) {
        glb_back_data['type'] = glb_current_list['type'];
        glb_back_data['id'] = glb_current_list['id'];
    }
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    msg_list();
    push_alert_bug_fix();
    clearCountdown();
}

function update_tab_badge(count) {
    if (count > 0) {
        count = count;
    } else {
        count = null;
    }
    // updates badge count for all different tabber bars?
    Ext.getCmp('home_tabbar').items.getAt(6).setBadgeText(count);
    Ext.getCmp('icon_menu_tabbar').items.getAt(6).setBadgeText(count);
    //Ext.getCmp('user_tabbar').items.getAt(6).setBadgeText(count);
    Ext.getCmp('user_tabbar_recommendations').items.getAt(6).setBadgeText(count);
    Ext.getCmp('user_tabbar_discover').items.getAt(6).setBadgeText(count);
    Ext.getCmp('user_tabbar_contacts').items.getAt(6).setBadgeText(count);
    Ext.getCmp('user_tabbar_empty_contacts').items.getAt(6).setBadgeText(count);
    Ext.getCmp('user_tabbar_empty_recommendations').items.getAt(6).setBadgeText(count);
    Ext.getCmp('search_tabbar').items.getAt(6).setBadgeText(count);

    // Video viewer
    Ext.getCmp('home_tabbar_video1').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_video2').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_settings').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_chromecast').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_feedback').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_help').items.getAt(6).setBadgeText(count);
    Ext.getCmp('homeBottomBar_options').items.getAt(6).setBadgeText(count);

    // Chat window with individual user
    Ext.getCmp('home_tabbar_MsgUser').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_ProMember').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_Resubscribe').items.getAt(6).setBadgeText(count);
    // Following, Queue, Collections, History, Cloud, Subscriptions
    Ext.getCmp('home_tabbar_UserList').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_General').items.getAt(6).setBadgeText(count);

    // Chat list
    Ext.getCmp('home_tabbar_MsgList').items.getAt(6).setBadgeText(count);

    // not needed
    Ext.getCmp('home_tabbar_Payment').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_Collect').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_Comment').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_EditVideo').items.getAt(6).setBadgeText(count);
    Ext.getCmp('home_tabbar_InviteF').items.getAt(6).setBadgeText(count);
}

// Android legacy player
function exitHandlerLegacy() {
	if (v_orientation_chk == 3) {
        v_orientation_chk = 1;
        screen.lockOrientation('portrait');
    }
}
function AndroidLegacyPlay(video_file) {
	cordova.plugins.videoPlayer.play(video_file);
}

function playVideo_handler(video_file) {
    if (v_orientation_chk == 1) {
            screen.unlockOrientation();
            v_orientation_chk = 3;
    }
    if (typeof cordova.plugins.videoPlayer != 'undefined') {
      playingLegacyPlayer = true;
      if (glb_last_panel != 'Video' && glb_last_panel != 'Video2') { //if first video
		  AndroidLegacyPlay(video_file);
          //clearAndroidPlaySprite();
      } else {
        AndroidLegacyPlay(video_file);
      }
    } 
	else if (typeof window.plugins.streamingMedia != 'undefined') {
		var options = {
			successCallback: function() {
				//clearMask();
			},
			errorCallback: function(errMsg) {
				myvAlert("Error! " + errMsg);
			},
			shouldAutoClose: true,  
			controls: true 
		};

		window.plugins.streamingMedia.playVideo(video_file,options);
	}
	else {
      window.plugins.videoPlayer.play(video_file); 
    }   
}

function search_handler(str_type) {
    clear_search(true);
    video_search(String(str_type), 1, 'profiles', glb_back_data['search_id'], glb_back_data['list_name']);
    Ext.Viewport.animateActiveItem("Search", {
        type: 'fade'
    });
}

function delete_download_handler(master_id, video_file, rowIndex) {
    var check_type = video_file.charAt(0);
    var check_type2 = video_file.charAt(1);
    var check_type3 = video_file.charAt(2);

    if (typeof device == 'undefined') {
        if (account.get('user_id')) {
            if (check_type != 'h' && check_type2 != 't' && check_type3 != 't') {
                myvAlert("Please install our native app for this feature.");
            } else {
                var msg = new Ext.MessageBox({
                    width: 320,
                    floating: 1,
                    margin: '80 0 0 0'
                });
                msg.show({
                    title: 'Are you sure you want delete this video?',
                    msg: 'Select an option.',
                    buttons: [{
                        text: 'Yes',
                        itemId: 'Yes'
                    }, {
                        text: 'No',
                        itemId: 'No'
                    }],
                    fn: function(response) {
                        if (response == 'Yes') {
                            delete_video(master_id, video_file, glb_current_video, rowIndex);
                        } else {}
                    }
                });
            }
        } else {
            Ext.Viewport.animateActiveItem("UserLogin", {
                type: 'fade'
            });
            searchviewbackhack();
            custom_views_arr.push("UserLogin");
            glb_custom_views_arr_data.push([0]);
        }
        return true;
    }

    video_file = decodeURIComponent(video_file);
    if (account.get('user_id')) {
        navigator.notification.confirm('Are you sure you want delete this video?',
            function(buttonIndex) {
                if (buttonIndex == 1) {
                    delete_video(master_id, video_file, glb_current_video, rowIndex);
                }
            }, 'myVidster', 'Yes,No');
    } else {
        Ext.Viewport.animateActiveItem("UserLogin", {
            type: 'fade'
        });
        searchviewbackhack();
        custom_views_arr.push("UserLogin");
        glb_custom_views_arr_data.push([0]);
    }
}

function delete_channelcollection_handler(type, id) {
    if (typeof device == 'undefined') {
        if (account.get('user_id')) {
                var msg = new Ext.MessageBox({
                    width: 320,
                    floating: 1,
                    margin: '80 0 0 0'
                });
                var typevar = 'delete_'+type;
                msg.show({
                    title: 'Delete this '+type+'?',
                    msg: 'Select an option.',
                    buttons: [{
                        text: 'Yes',
                        itemId: 'Yes'
                    }, {
                        text: 'No',
                        itemId: 'No'
                    }],
                    fn: function(response) {
                        if (response == 'Yes') {
                            setMask('Loading...');
                            Ext.util.JSONP.request({
                                url: 'https://api.myvidster.com/mobile_json2.php',
                                callbackKey: 'callback',
                                params: {
                                    type: typevar,
                                    id: id,
                                    user_id: account.get('user_id'),
                                    token: glb_msg_token,
                                    pro_status: glb_is_pro_ckeck,
                                    pkey: account.get('pw'),
                                    uniquify: Math.random()
                                },
                                callback: function(success, result) {
                                    if (result.items) {
                                        if (result.items[0] == "") {
                                            clearMask();
                                            if(type == "collection"){
                                                glb_seb_back = 1;
                                                list_videos('User', account.get('user_id'), 0);
                                                list_videos_obj.currentPage = 1;
                                                glb_seb_back = 1;
                                                list_videos_obj.load();
                                            }
                                            else{
                                                glb_seb_back = 1;
                                                list_videos(glb_current_list['type'], glb_current_list['id'], 0, 1);
                                                list_videos_obj.currentPage = 1;
                                                glb_seb_back = 1;
                                                list_videos_obj.load();
                                            }
                                        } else {
                                            clearMask();
                                            myvAlert(result.items[0].errors);
                                        }
                                    }
                                }
                            });
                        } else {}
                    }
                });
        } 
        return true;
    }

    if (account.get('user_id')) {
        var typevar = 'delete_'+type;
        navigator.notification.confirm('Delete this '+type+'?',
            function(buttonIndex) {
                if (buttonIndex == 1) {
                    setMask('Loading...');
                    Ext.util.JSONP.request({
                        url: 'https://api.myvidster.com/mobile_json2.php',
                        callbackKey: 'callback',
                        params: {
                            type: typevar,
                            id: id,
                            user_id: account.get('user_id'),
                            token: glb_msg_token,
                            pro_status: glb_is_pro_ckeck,
                            pkey: account.get('pw'),
                            uniquify: Math.random()
                        },
                        callback: function(success, result) {
                            if (result.items) {
                                if (result.items[0] == "") {
                                    clearMask();
                                    if(type == "collection"){
                                        glb_seb_back = 1;
                                        list_videos('User', account.get('user_id'), 0);
                                        list_videos_obj.currentPage = 1;
                                        glb_seb_back = 1;
                                        list_videos_obj.load();
                                    }
                                    else{
                                        glb_seb_back = 1;
                                        list_videos(glb_current_list['type'], glb_current_list['id'], 0, 1);
                                        list_videos_obj.currentPage = 1;
                                        glb_seb_back = 1;
                                        list_videos_obj.load();
                                    }
                                } else {
                                    clearMask();
                                    myvAlert(result.items[0].errors);
                                }
                            }
                        }
                    });
                }
            }, 'myVidster', 'Yes,No');
    } 
}

function download_video_handler(master_id, video_id, type_url, video_file, video_thumbnail, video_title, video_type_id, video_type) {
    if (typeof device == 'undefined') {
        var msg = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msg.show({
            title: 'Save video in the cloud?',
            msg: 'Select an option.',
            buttons: [{
                text: 'yes',
                itemId: 'cloud'
            }, {
                text: 'cancel',
                itemId: 'cancel'
            }],
            fn: function(response) {
                if (response == 'cloud') {
                    if (glb_downloading)
                        myvAlert("A download in progress: " + glb_loadingStatus + ", please wait for the download to complete.");
                    else {
                        if (response == 'cloud') {
                            if (video_type == 0) {
                                check_download_cnt(master_id, video_id, type_url, type_url, video_thumbnail, video_title, 'cloud');
                            } else 
                                check_download_cnt(master_id, video_id, type_url, video_file, video_thumbnail, video_title, 'cloud');
                        }
                    }
                }
            }
        });
        return true;
    }

    if (Ext.os.is('iOS')) {
        /*
        // might be needed
            user_options.each(function(record){
            if(record.get('user_options') == 'Cloud') { user_options.remove(record); }
            });
            user_options.add({
                user_options: 'Cloud',
                value: 'Downloads'
            });
        */
    }

    if (!video_file && video_type != 0) {
        if (glb_source_dl) {
            video_file = glb_source_dl;
        } else {
            myvAlert("Video is still loading please wait.");
            return true;
        }
    }

    type_url = decodeURIComponent(type_url);
    video_file = decodeURIComponent(video_file);
    video_thumbnail = decodeURIComponent(video_thumbnail);

    // download video or download to cloud here
    navigator.notification.confirm('Where would you like to save your video?',
        function(buttonIndex) {
            if (buttonIndex == 1 || buttonIndex == 2) {
                if (glb_downloading)
                    myvAlert("A download in progress: " + glb_loadingStatus + ", please wait for the download to complete.");
                else {
                    if (buttonIndex == 1) {
                        if (video_type == 0) {
                            fetch_youtube_dl(master_id, video_id, type_url, video_thumbnail, video_title, 'phone', video_type_id);
                         } else {
                            check_download_cnt(master_id, video_id, type_url, video_file, video_thumbnail, video_title, 'phone');
                         }
                    } else {
                        if (video_type == 0) {
                            check_download_cnt(master_id, video_id, type_url, type_url, video_thumbnail, video_title, 'cloud');
                        } else {
                            check_download_cnt(master_id, video_id, type_url, video_file, video_thumbnail, video_title, 'cloud');
                        }
                    }
                }
            }
        }, 'myVidster', 'Phone,Cloud,Cancel');
}

function editVid(action, video_id) {
    var access_opt = ''; // Ext.ComponentQuery.query('radiofield[name=access_opt1]')[0]['_checked'] == true
    if (Ext.ComponentQuery.query('radiofield[name=access_opt]')[0]['_checked'] == true) {
        access_opt = 0;
        // public 0
    } else if (Ext.ComponentQuery.query('radiofield[name=access_opt]')[1]['_checked'] == true) {
        access_opt = 1;
        // private 1
    } else if (Ext.ComponentQuery.query('radiofield[name=access_opt]')[2]['_checked'] == true) {
        access_opt = 2;
        // adult 2
    }
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'change_video_bookmark',
            id: video_id,
            action: action,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            uniquify: Math.random(),
            video_title: Ext.getCmp('edit_video_title').getValue(),
            video_tags: Ext.getCmp('edit_video_tags').getValue(),
            video_desc: Ext.getCmp('edit_desc_text').getValue(),
            video_access: access_opt,
            video_channel: Ext.getCmp('myv_item_editv').getValue()
        },
        callback: function(success, result) {
            if (result.items) {
                if (result.items[0].response == "success") {
                    var vid = "'" + video_id + "'";
                    setTimeout(function() {
                        try{
                            var last_view = custom_views_arr[custom_views_arr.length - 1];
                            if(last_view == 'EditVideo'){
                                custom_views_arr.pop();
                                glb_custom_views_arr_data.pop();
                            }
                        }catch(err){}
                        getVideo(video_id, 'videobyid');
                        clearMask();
                    }, 2100);
                } else {
                    clearMask();
                    myvAlert(result.items[0].response);
                }
            }
        }
    });
}

function edit_video(video_id, num) {
    if (num == 0) {
        glb_back_data_channel_bug_2 = 0;
    } else {
        glb_back_data_channel_bug_2 = 1;
    }
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'get_edit_video_data',
            id: video_id,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            uniquify: Math.random()
        },
        callback: function(success, result) {
            if (result.items) {
                var editv_opt = new Array();
                var user_channels = result.items[0].user_channels.length;
                var video_title = '';
                video_title = result.items[0].video_title;
                var video_tags = '';
                video_tags = result.items[0].video_tags;
                var desc_text = '';
                desc_text = result.items[0].desc_text;
                var default_channel = '';
                default_channel = result.items[0].default_channel;
                var vid_access = '';
                vid_access = result.items[0].vid_access;
                var access_lock = '';
                access_lock = result.items[0].access_lock;
                if (access_lock == 1) {
                    Ext.getCmp('access_check').setHidden(true);
                } else {
                    Ext.getCmp('access_check').setHidden(false);
                }
                for (var i = 0; i < user_channels; i++) {
                    editv_opt[i] = {
                        text: result.items[0].user_channels[i].gallery_name + ': ' + result.items[0].user_channels[i].name,
                        value: result.items[0].user_channels[i].id
                    };
                }
                Ext.getCmp('myv_item_editv').setOptions(editv_opt);
                Ext.getCmp('myv_item_editv').setValue(result.items[0].channel_id);
                Ext.getCmp('edit_video_title').setValue(video_title);
                Ext.getCmp('edit_video_tags').setValue(video_tags);
                Ext.getCmp('edit_desc_text').setValue(desc_text);
                Ext.getCmp('edit_video_id').setValue(video_id);
                if (vid_access == 0) {
                    Ext.getCmp('access_opt1').setChecked(true);
                } else if (vid_access == 1) {
                    Ext.getCmp('access_opt2').setChecked(true);
                } else if (vid_access == 2) {
                    Ext.getCmp('access_opt3').setChecked(true);
                }
                Ext.Viewport.animateActiveItem("EditVideo", {
                    type: 'fade'
                });
                searchviewbackhack();
                custom_views_arr.push("EditVideo");
                glb_custom_views_arr_data.push([0]);
                glb_last_panel = Ext.Viewport.getActiveItem().id;
                clearMask();
            }
        }
    });
}

function video_show_more(video_id, master_id, ip, type_url, video_title_encoded, access, isOwner) {
    type_url = decodeURIComponent(type_url);
    var more_btn_sz2 = '';
    if (glb_device_type == 'tablet') {
        more_btn_sz2 = 'font-size: 1.22em;';
    }
    var owner_check = '';
    if (isOwner == 1) {
        owner_check = false;
    } else {
        owner_check = true;
    }
    var video_actionSheet = Ext.create('Ext.ActionSheet', {
        items: [{
            text: 'edit video',
            style: more_btn_sz2,
            hidden: owner_check,
            handler: function(btn, evt) {
                edit_video(video_id, 0);
                video_actionSheet.hide();
            }
        }, /*{
            text: 'share',
            style: more_btn_sz2,
            handler: function(btn, evt) {
                share_video(video_title_encoded, video_id);
                video_actionSheet.hide();
            }
        },*/ { // chat func
            text: 'share video',
            style: more_btn_sz2,
            handler: function(btn, evt) {
                selectVideoSendMethod(video_title_encoded, video_id);
                video_actionSheet.hide();
            }
        },{
            text: 'report',
            style: more_btn_sz2,
            handler: function(btn, evt) {
                report_video_handle(video_id, master_id, ip, access);
                video_actionSheet.hide();
            }
        }, {
            text: 'source',
            style: more_btn_sz2,
            handler: function(btn, evt) {
                load_webpage(type_url, false);
                video_actionSheet.hide();
            }
        }, {
            text: 'Cancel',
            style: more_btn_sz2,
            ui: 'decline',
            handler: function(btn, evt) {
                video_actionSheet.hide();
            }
        }]
    });
    Ext.Viewport.add(video_actionSheet);
    video_actionSheet.show();
}

function setMask(msg) {
    var loadingMask = {
        xtype: 'loadmask',
        message: msg
    };
    Ext.Viewport.setMasked(loadingMask);
}

function clearMask() {
    Ext.Viewport.setMasked(false);
}

function log_preroll(user_id, ip) {
    var uniquify = Math.random();
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'log_preroll',
            id: user_id,
            ip: ip,
            token: glb_msg_token,
            uniquify: uniquify
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                var response = items[0].response;
                if (response == "Success") {
                } else {
                    myvAlert(response);
                }
            } else {}
        }
    });
}

function encodeHTML(s) {
    return s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/"/g, '&quot;');
}

// sends chat messages to other users chat func
function pusher_test(from_id, to_id, msg, video_id) {
    // try/catch needed for known pusher plugin bugs
    try {
        var xmlhttp;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                    var str = xmlhttp.responseText;
                    var n = str.includes("error2");
                    if(n){
                        myvAlert("Please restart your app to send a message!");
                    }
                }
            }
        }
        xmlhttp.open("GET", 'https://api.myvidster.com/austin/send_msg_pusher.php?my=' + String(from_id) + '&msg=' + encodeURIComponent(msg) + '&to=' + String(to_id) + '&token=' + glb_msg_token + '&video_id=' + video_id, true);
        xmlhttp.send();
        sendingMessage = true;
        if (video_id) {
          myvAlert("Video sent");
        }
    } catch (err) {
        myvAlert('Message not sent!');
    }
}

var msg_user = function(user_id, my_id, name) {
    if (glb_current_list['type']) {
        glb_back_data['type'] = glb_current_list['type'];
        glb_back_data['id'] = glb_current_list['id'];
    }
    Ext.getCmp('to_c').setValue(user_id);
    Ext.getCmp('from_c').setValue(my_id);
    document.getElementById('msg_c').value = ""; 
    Ext.Viewport.animateActiveItem("MsgUser", {
        type: 'fade',
    });
    var array_data = [user_id, name, 1];
    searchviewbackhack();
    custom_views_arr.push('MsgUser');
    glb_custom_views_arr_data.push(array_data);
    setTimeout(function() {
        var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();
        scroller.scrollToEnd(false);
    }, 99);
    // sebmsg 2020
    Ext.getCmp('home_tabbar_MsgUser').hide();
}


var chat_scroll_btm = function() {
    setTimeout(function() {
        var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();
        scroller.scrollToEnd(false);
    }, 100);
}

//loads chat message list
var msg_list = function() {
    if (account.get('user_id')) {
        var dt = new Date();
        var offset_time = dt.getTimezoneOffset();
        var limit = 12;
        if (glb_device_type == 'tablet') {
            limit = 14;
        }
        list_msgs_obj.getProxy().setExtraParams({
            type: "messages_list",
            user_id: account.get('user_id'),
            pkey: account.get('pw'),
            device_type: glb_device_type,
            token: glb_msg_token,
            limit: limit,
            start: 0,
            id: Math.random(),
            offset: offset_time
        });
        Ext.Viewport.animateActiveItem("MsgList", {
            type: 'fade'
        });
        searchviewbackhack();
        custom_views_arr.push("MsgList");
        var array_data = [0];
        glb_custom_views_arr_data.push(array_data);

        // scroll to top when we come to the list msgs from another panel
        var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();
        scroller.scrollTo({
            x: 0,
            y: 0
        });

        list_msgs_obj.currentPage = 1;
        list_msgs_obj.load();
    } else {
        Ext.Viewport.animateActiveItem("UserLogin", {
            type: 'fade'
        });
        searchviewbackhack();
        custom_views_arr.push("UserLogin");
        glb_custom_views_arr_data.push([0]);
    }
    //setMask('Loading Messages');
}


function start_chat(from_id, to_id) {
    var uniquify = Math.random();
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_chat.php',
        callbackKey: 'callback',
        params: {
            type: 'start_chat',
            from_id: from_id,
            to_id: to_id,
            token: glb_msg_token,
            uniquify: uniquify
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                var response = items[0].response;
                if (response == "Success") {
                    myvAlert(response);
                } else {
                    myvAlert(response);
                }
            } else {
                myvAlert("Download failed, source video not found");
            }
        }
    });
}

function send_msg(from_id, to_id, msg) {
    var uniquify = Math.random();
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_chat.php',
        callbackKey: 'callback',
        params: {
            type: 'send_msg',
            from_id: from_id,
            to_id: to_id,
            msg: msg,
            token: glb_msg_token,
            uniquify: uniquify
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                var response = items[0].response;
                if (response == "Success") {
                    myvAlert(response);
                } else {
                    myvAlert(response);
                }
            } else {
                myvAlert("Download failed, source video not found");
            }
        }
    });
}

function show_lockscreen(isStartup) {
    glb_back_button = false;
    Ext.getCmp('lockBTN').setHidden(false);
    Ext.getCmp('lockSpace').setHidden(true);
    if (!application.get('pin_code1')) {
        Ext.get('lock_dialog').setHtml("Please set your pin");
    } else if (!application.get('pin_code2')) {
        Ext.get('lock_dialog').setHtml("Please confirm your pin");
    } else {
        Ext.getCmp('lockBTN').setHidden(true);
        Ext.getCmp('lockSpace').setHidden(false);
        Ext.get('lock_dialog').setHtml("Enter Pin");
    }

    if(glb_lockscreen_shown == 0){
        Ext.Viewport.animateActiveItem('LockScreen', {
            type: 'fade',
        });
    }else{
        Ext.getCmp('lockBTN').setHidden(false);
        Ext.getCmp('lockSpace').setHidden(true);
    }

    if (typeof device != 'undefined' && isStartup) navigator.splashscreen.hide();
}

function update_app() {
    if (checkConnection()) {
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                type: 'check_version'
            },
            callback: function(success, result) {
                if (result) {
                    var items = result.items;
                    var version = items[0].version;
                    if (glb_version < version) {
                        ask_to_update();
                    }
                }
            }
        });
    }
}

function update_app2() {
    if (checkConnection()) {
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                type: 'check_version'
            },
            callback: function(success, result) {
                if (result) {

                    if (Ext.os.is.Android) {
                        var url_open = 'https://www.myvidster.com/app/android_update';
                    } else {
                        var url_open = 'https://itunes.apple.com/us/app/myvidster/id611470289?mt=8';
                    }

                    var items = result.items;
                    var version = items[0].version;
                    if (glb_version < version) {
                        var new_update_btn = {
                            layout: {
                                type: 'hbox',
                                pack: 'center'
                            },
                            items: [{
                                xtype: 'button',
                                text: 'Update to version ' + String(version),
                                height: '34px',
                                margin: '10',
                                handler: function() {
                                    load_webpage(url_open, true)
                                }
                            }]
                        };
                        myGeneral.add(new_update_btn);
                    }
                }
            }
        });
    }
}

function ask_to_update() {
    navigator.notification.confirm('A new version of the MyVidster App is now available.',
        function(buttonIndex) {
            if (buttonIndex == 1) {
                if (Ext.os.is.Android) {
                    load_webpage('https://www.myvidster.com/app/android_update', true);
                } else
                    load_webpage('https://itunes.apple.com/us/app/myvidster/id611470289?mt=8', true);
            }
        }, 'MyVidster', 'update,no thanks');
}


function lockOverride() {
    glb_lock_screen_override = true;
}

function backLock() {

    Ext.Viewport.animateActiveItem('Home', {
        type: 'fade',
    });

    searchviewbackhack();
    custom_views_arr.push("Home");
    glb_custom_views_arr_data.push([0]);

    glb_lock_screen = 0;
    application.set("lock_screen", glb_lock_screen);
    application.set("pin_code1", "");
    application.set("pin_code2", "");
    application.setDirty();
    app_store.sync();
    application = app_store.first();

    Ext.getCmp('settingsForm').setValues({
        lock_screen: 0
    });

    glb_back_button = true;
    //glb_lock_screen_override = true;

    Ext.getCmp('lock_holder').setValues({
        lock_pw1: '',
        lock_pw2: '',
        lock_pw3: '',
        lock_pw4: ''
    });

}

function clearLock() {
    Ext.getCmp('lock_holder').setValues({
        lock_pw1: '',
        lock_pw2: '',
        lock_pw3: '',
        lock_pw4: ''
    });

}

function updateLock(value) {
    var fields = Ext.getCmp('lock_holder').getValues();
    var lock_pw1 = fields['lock_pw1'];
    var lock_pw2 = fields['lock_pw2'];
    var lock_pw3 = fields['lock_pw3'];
    var lock_pw4 = fields['lock_pw4'];

    if (lock_pw1 == '') {
        Ext.getCmp('lock_holder').setValues({
            lock_pw1: value
        });
    } else if (lock_pw2 == '') {
        Ext.getCmp('lock_holder').setValues({
            lock_pw2: value
        });
    } else if (lock_pw3 == '') {
        Ext.getCmp('lock_holder').setValues({
            lock_pw3: value
        });
    } else if (lock_pw4 == '') {
        Ext.getCmp('lock_holder').setValues({
            lock_pw4: value
        });

        window.setTimeout(function() {
            fields = Ext.getCmp('lock_holder').getValues();
            var results = fields['lock_pw1'] + fields['lock_pw2'] + fields['lock_pw3'] + fields['lock_pw4'];
            Ext.getCmp('lock_holder').setValues({
                lock_pw1: '',
                lock_pw2: '',
                lock_pw3: '',
                lock_pw4: ''
            });

            if (!application.get('pin_code1')) {
                application.set("pin_code1", results);
                show_lockscreen(false);
            } else if (!application.get('pin_code2')) {
                if (results == application.get('pin_code1')) {
                    glb_lock_screen = 1;
                    application.set("lock_screen", glb_lock_screen);
                    application.set("pin_code2", results);
                    application.setDirty();
                    app_store.sync();
                    application = app_store.first();

                    Ext.getCmp('settingsForm').setValues({
                        lock_screen: glb_lock_screen
                    });

                    promptForTouchIDEnable();

                    glb_back_button = true;
                    if(glb_last_panel == 'Startup'){
                        custom_views_arr = [];
                        custom_views_arr.push('Home');
                        var array_data = [0];
                        glb_custom_views_arr_data = [];
                        glb_custom_views_arr_data.push(array_data);
                        Ext.Viewport.setActiveItem('Home');
                        load_homepage(0, 0);
                    }else{
                        custom_views_arr.push("LockScreen");
                        glb_custom_views_arr_data.push([0]);
                        onBackKeyDown();
                    } 

                } else {
                    myvAlert("Pin codes do not match please try again");
                    application.set("pin_code1", "");
                    application.set("pin_code2", "");
                    application.setDirty();
                    app_store.sync();
                    application = app_store.first();
                    show_lockscreen(false);
                }
            } else {
                // unlocks phone with correct pin code
                if (results == application.get('pin_code1')) {
                    glb_back_button = true;
                    //alert('pinned',glb_last_panel );

                   // if(glb_last_panel == 'Startup'){
                        custom_views_arr = [];
                        custom_views_arr.push('Home');
                        var array_data = [0];
                        glb_custom_views_arr_data = [];
                        glb_custom_views_arr_data.push(array_data);
                        Ext.Viewport.setActiveItem('Home');
                        glb_lockscreen_shown = 1;
                        load_homepage(0, 0);
                   // }else{
                    //    custom_views_arr.push("LockScreen");
                    //    glb_custom_views_arr_data.push([0]);
                    //    onBackKeyDown();
                   // }
                } else {
                    myvAlert("Incorrect pin please try again");
                    show_lockscreen(false);
                }
            }
        }, 250);
    }
}

function loadAd(ad_id) {
    if (ad_id.search("ca-app-pub") >= 0) {
        if (AdMob) {
            AdMob.prepareInterstitial({
                adId: ad_id,
                autoShow: false,
                isTesting: false,
                license: "marques@myvidster.com/81f5454112e7bb1a61e94d98ad6f8ae1"
            });
        }
    } else {
		//if (typeof AppTracker != 'undefined') {	//leadbolt
			//AppTracker.loadModuleToCache("inapp");
        //}
        if (typeof window.AmazonMobileAds != 'undefined') {
            window.AmazonMobileAds.loadInterstitialAd(
                function(operationResponse) {
                    var loadingStarted = operationResponse.booleanValue;
                },
                function(errorResponse) {
                    // Handle error
                }, []
            );
        }

    }
}

function registerAdEvents() {
    glb_ad_resume = 1;
    if (Ext.os.is.iOS && typeof device != 'undefined') {
        document.addEventListener('onAdDismiss', function() {
            // try/catch added by sterling dev
            try {
                var video = document.getElementsByTagName("video")[0];
                video.play();
                if (typeof device != 'undefined') {
                    doPlayList();
                }
            } catch (err) {
            }
        });
    }
}

function playAd(ad_id) {
    lockOverride();
    if (ad_id.search("ca-app-pub") >= 0) {
        if (AdMob) {
            AdMob.showInterstitial();
        }
    }
	//else if (typeof AppTracker != 'undefined') {	//leadbolt
		//AppTracker.loadModule("inapp");
    //}
	else if (typeof window.AmazonMobileAds != 'undefined') {
		window.AmazonMobileAds.showInterstitialAd(
			function(operationResponse) {
				// Handle success
				var adShown = operationResponse.booleanValue;
				//if (adShown == false) {
					//fallback ad here
					//if (typeof AppTracker != 'undefined') {	//leadbolt
                        //AppTracker.loadModule("inapp");
                    //}
				//}
			},
			function(errorResponse) {
				// Handle error
				}, [savedAdObject]
		);
	}          
}

initializeCastApi = function() {
    if (Ext.os.is('Android')) {
        var applicationID = '978358C0';
        var sessionRequest = new chrome.cast.SessionRequest(applicationID);
        var apiConfig = new chrome.cast.ApiConfig(sessionRequest, sessionListener, receiverListener);

        chrome.cast.initialize(apiConfig, onCastSuccess, onCastFail);

        setTimeout(function() {
            if (glb_chromecast_init_check) {
                chrome_cast_check_availability(apiConfig);
            }
        }, 20000);

    } else {
        chromeCastStartScan();
    }
};

function chomecast_requestSession() {
    chrome.cast.requestSession(onRequestSessionSuccess, onRequestSessionCancel);
    setMask('Connecting...');
}

function onRequestSessionCancel() {
    clearMask();
}

function yt_chomecast_load(yt_id, video_thumbnail, title, w, h) {
    setMask('Please wait...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'fetch_youtube_dl',
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            conn: checkConnectionType(),
            id: yt_id
        },
        callback: function(success, result) {
            clearMask();
            if (result) {
                var items = result.items;
                var response = items[0].response;
                if (response == "Success") {
                    var url = items[0].video_dl;
                    chomecast_load(url, video_thumbnail, title, w, h);
                } else {
                    myvAlert(response);
                }
            } else {
                myvAlert("Download failed, source video not found");
            }
        }
    });
}

function pip_load(w,h) {
	var techEl = glb_myPlayer.tech(true).el();
	
	if (typeof PictureInPicture != 'undefined') {
		PictureInPicture.enter(w, h, pip_success,pip_error);
	}
	else if (techEl.webkitSupportsPresentationMode && typeof techEl.webkitSetPresentationMode === "function") {
		if(glb_myPlayer.paused()) {
			glb_myPlayer.one('playing', function() {
				techEl.webkitSetPresentationMode(techEl.webkitPresentationMode === "picture-in-picture" ? "inline" : "picture-in-picture");
			});
			glb_myPlayer.play();
		}
		else {
			techEl.webkitSetPresentationMode(techEl.webkitPresentationMode === "picture-in-picture" ? "inline" : "picture-in-picture");
		}
	}
	else if(typeof techEl.requestPictureInPicture === "function") {
		if(glb_myPlayer.paused()) {
			glb_myPlayer.one('playing', function() {
				techEl.requestPictureInPicture();
			});
			glb_myPlayer.play();
		}
		else {
			techEl.requestPictureInPicture();
		}
	}
	else {
		myvAlert('Picture in Picture is not supported by this device');
	}
}

function pip_success() {
	if(glb_myPlayer.paused())
		glb_myPlayer.play();
	glb_myPlayer.requestFullscreen();
}

function pip_error(err) {
	myvAlert('Picture in Picture is not supported by this device');
}

function chomecast_load(url, video_thumbnail, title, w, h) {
    try {
        var video = document.getElementsByTagName("video")[0];
        video.pause();
    } catch (err) {}

    if (Ext.os.is('iOS')) {
        if (!glb_ios_glb_session) {
            chromeCastShowActiveDevices();
            return true;
        }
        if (glb_ios_currentMedia) {
            switch (glb_ios_playerState) {
                case 'Buffering':
                    load_chromecast_controler(video_thumbnail, title, w, h);
                    chromeCastCastVideo(url, video_thumbnail, title, "");
                    break;
                case 'Playing':
                case 'Paused':
                    navigator.notification.confirm('Existing media loaded',
                        function(buttonIndex) {
                            if (buttonIndex == 1) {
                                glb_last_panel = Ext.Viewport.getActiveItem().id;
                                Ext.Viewport.animateActiveItem('ChromeCast', {
                                    type: 'fade',
                                });

                                searchviewbackhack();
                                custom_views_arr.push("ChromeCast");
                                glb_custom_views_arr_data.push([0]);
                            } else if (buttonIndex == 2) {
                                //chromeCastStop();
                                load_chromecast_controler(video_thumbnail, title, w, h);
                                chromeCastCastVideo(url, video_thumbnail, title, "");
                            }
                        }, 'chromecast', 'go to media,load new media');
                    break;
                default:
                    break;
            }
        } else {
            load_chromecast_controler(video_thumbnail, title, w, h);
            chromeCastCastVideo(url, video_thumbnail, title, "");
        }
    } else {
        if (!glb_session) {
            chomecast_requestSession();
            return true;
        }
        var mediaInfo = new chrome.cast.media.MediaInfo(url, 'video/mp4');
        var request = new chrome.cast.media.LoadRequest(mediaInfo);

        if (glb_currentMedia) {
            switch (glb_currentMedia.playerState) {
                case chrome.cast.media.PlayerState.IDLE:
                case chrome.cast.media.PlayerState.STOPPED:
                case chrome.cast.media.PlayerState.LOADING:
                    load_chromecast_controler(video_thumbnail, title, w, h);
                    glb_session.loadMedia(request, onMediaDiscovered.bind(this, 'loadMedia'), onChomeCast_loadFail);
                    break;
                case chrome.cast.media.PlayerState.PLAYING:
                case chrome.cast.media.PlayerState.LOADED:
                case chrome.cast.media.PlayerState.PAUSED:
                    navigator.notification.confirm('Existing media loaded',
                        function(buttonIndex) {
                            if (buttonIndex == 1) {
                                glb_last_panel = Ext.Viewport.getActiveItem().id;
                                Ext.Viewport.animateActiveItem('ChromeCast', {
                                    type: 'fade',
                                });

                                searchviewbackhack();
                                custom_views_arr.push("ChromeCast");
                                glb_custom_views_arr_data.push([0]);
                            } else if (buttonIndex == 2) {
                                load_chromecast_controler(video_thumbnail, title, w, h);
                                glb_session.loadMedia(request, onMediaDiscovered.bind(this, 'loadMedia'), onChomeCast_loadFail);
                            }
                        }, 'chromecast', 'go to media,load new media');
                    break;
                default:
                    break;
            }
        } else {
            load_chromecast_controler(video_thumbnail, title, w, h);
            glb_session.loadMedia(request, onMediaDiscovered.bind(this, 'loadMedia'), onChomeCast_loadFail);
        }
    }
}

function onChomeCast_loadFail() {
    chrome.cast.requestSession(onRequestSessionSuccess, onFail);
}

function onMediaDiscovered(how, media) {
    glb_currentMedia = media;
    glb_currentMedia.addUpdateListener(onMediaStatusUpdate);
    glb_chromecast_update = setInterval(updateCurrentTime, 1000);
    clearMask();
}

function chrome_thumb_seeker_func(pos) {
    if (Ext.os.is('iOS')) {
        if (glb_ios_currentMedia && glb_chromecast_listeners) {
            chromeCastChangeStreamPosition( (pos * 10) / glb_seeker_duration ); 
        }
    } else {
        if (glb_currentMedia && glb_chromecast_listeners) {
            var request = new chrome.cast.media.SeekRequest();
            var pos2 = parseInt(pos * 10) - 5;
            request.currentTime = pos2; 
            glb_currentMedia.seek(request, onSuccess, onFail);
      }
    }
};

function load_chromecast_controler(video_thumbnail, title, w, h) {
    var output;
    var video_thumb_left = Math.round(w / 2 - 50);
    var video_thumb_top = Math.round(h / 2 - 50);

    glb_last_panel = Ext.Viewport.getActiveItem().id;

    output = '<div style="background-color:#000;" align="center">';
    output += '<div class="video-dev" style="background:url(' + video_thumbnail + ') no-repeat center;background-size:100% 100%;width:' + w + 'px;height:' + h + 'px;">';
    output += '<div id="chomecast_play_pause"><a href="javascript:void(0);" onclick="chomecast_play_pause(\'pause\',' + video_thumb_left + ',' + video_thumb_top + ')"><span class="pause-video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a></div>';
    output += '</div>';
    output += '</div>';
    output += '<div class="video_title">' + title + '</div>',

    document.getElementById("chrome_preview").innerHTML = output;
    Ext.getCmp('chrome_cast_volume').setValue(glb_currentVolume * 10);
    Ext.getCmp('chrome_cast_seeker').setValue(0);
    Ext.get('chrome_time').setHtml("00:00:00");
    Ext.get('chrome_duration').setHtml("00:00:00");
    Ext.getCmp('chromecastseeker').removeAll();
    var thumb_h = glb_seek_carousel_h;
    if(glb_html_chrome_seeker == ''){
        thumb_h = 0;
    }
    var glb_seek_carousel_3 = {
        xtype: 'panel',
        scrollable: {
            direction: 'horizontal',
            directionLock: true,
            indicators: false
        },
        config: {
            inline: {
                wrap: false
            }
        },
        indicator: false,
        height: thumb_h,
        width: '100%',
        html: '<div id="seek_carousel_3" style="margin:0px auto 0px;text-align:left;width:100%;overflow:hidden; white-space:nowrap;' + glb_bottomPaddingThumbnails +'background-color:black;' + glb_topPaddingThumbnails +'">' + glb_html_chrome_seeker + '</div>'
    };
        Ext.getCmp('chromecastseeker').add(glb_seek_carousel_3);
   
    Ext.Viewport.animateActiveItem('ChromeCast', {
        type: 'fade',
    });
    searchviewbackhack();
    custom_views_arr.push("ChromeCast");
    glb_custom_views_arr_data.push([0]);
	
    if (!Ext.os.is('iOS')) {
        setMask("Playing on " + glb_session.displayName);
    }
    clearMask();
}
function chomecast_mute(mute) {
    if (mute == "mute") {
        if (Ext.os.is('iOS')) chromeCastMuteUnmute();
        else glb_session.setReceiverMuted(true, onSuccess, onFail);
        output = '<a href="javascript:void(0);" onclick="chomecast_mute(\'unmute\')"><span class="casticon-mute"></span></a>';
    } else {
        if (Ext.os.is('iOS')) chromeCastMuteUnmute();
        else glb_session.setReceiverMuted(false, onSuccess, onFail);
        output = '<a href="javascript:void(0);" onclick="chomecast_mute(\'mute\')"><span class="casticon-unmute"></span></a>';
    }
    Ext.get('chomecast_mute').setHtml(output);
}
function chomecast_play_pause(playpause, video_thumb_left, video_thumb_top) {
    var output;
    if (Ext.os.is('iOS')) {
        if (glb_ios_currentMedia) {
            chromeCastPlayPause();
            if (playpause == "pause") {
                output = '<a href="javascript:void(0);" onclick="chomecast_play_pause(\'play\',' + video_thumb_left + ',' + video_thumb_top + ')"><span class="video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';
            } else {
                output = '<a href="javascript:void(0);" onclick="chomecast_play_pause(\'pause\',' + video_thumb_left + ',' + video_thumb_top + ')"><span class="pause-video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';
            }
            Ext.get('chomecast_play_pause').setHtml(output);
        }
    } else {
        if (glb_currentMedia) {
            if (playpause == "pause") {
                glb_currentMedia.pause(null, onSuccess, onFail);
                output = '<a href="javascript:void(0);" onclick="chomecast_play_pause(\'play\',' + video_thumb_left + ',' + video_thumb_top + ')"><span class="video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';
            } else {
                glb_currentMedia.play(null, onSuccess, onFail);
                output = '<a href="javascript:void(0);" onclick="chomecast_play_pause(\'pause\',' + video_thumb_left + ',' + video_thumb_top + ')"><span class="pause-video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';
            }

            Ext.get('chomecast_play_pause').setHtml(output);
        } else {
            onBackKeyDown();
        }
    }
}

function updateCurrentTime() {
    if (glb_currentMedia) {
        glb_chromecast_listeners = false;
        var cTime = glb_currentMedia.getEstimatedTime();
        var pos = parseInt(100 * cTime / glb_currentMedia.media.duration);

        var chrome_time = moment().startOf('day')
            .seconds(parseInt(cTime))
            .format('HH:mm:ss');

        var chrome_duration = moment().startOf('day')
            .seconds(parseInt(glb_currentMedia.media.duration))
            .format('HH:mm:ss');

        if (pos >= 0) {
            Ext.getCmp('chrome_cast_seeker').setValue(pos);
            Ext.get('chrome_time').setHtml(chrome_time);
            Ext.get('chrome_duration').setHtml(chrome_duration);
        }

        glb_chromecast_listeners = true;
    } else {
        clearInterval(glb_chromecast_update);
    }
}

function onMediaStatusUpdate(isAlive) {
    if (glb_currentMedia) {
     switch (glb_currentMedia.playerState) {
        case chrome.cast.media.PlayerState.IDLE:
        case chrome.cast.media.PlayerState.STOPPED:
            glb_currentMedia = null;
            break;
        default:
            break;
     }
    }
}
function chomecast_stop() {
    if (Ext.os.is('Android')) {
        glb_currentMedia.stop(null, onSuccess, onFail);
        glb_currentMedia = null;
        clearInterval(glb_chromecast_update);
        onBackKeyDown();
    } else {
        clearInterval(glb_chromecast_update);
        glb_ios_currentMedia = null;
        onBackKeyDown();
        chromeCastStop();
    }
}
function onCastSuccess() {}

function onCastFail(e) {
    // retry on fail , email this data possibly
}

function chrome_cast_check_availability(apiConfig) {
    var Routers = chrome.cast.getRouteListElement();
    var cast_devices = Routers['innerText'];

    if (cast_devices != "" && glb_chrome_cast_setting != 1 && glb_chrome_cast_setting != 2 && glb_chromecast_init_check == true) {
        var msg_cc = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0',
            items: [{
                html: '<br/><div style="color:white;padding:10px;">Your device failed to detect your Chromecast. <br/>Retry?<br/><br/><input type="checkbox" id="chromecast_save" /> Save Option</div><br/>'
            }]
        });
        msg_cc.show({
            title: 'Chromecast',
            msg: '',
            buttons: [{
                text: 'Yes',
                itemId: 1
            }, {
                text: 'No',
                itemId: 2
            }],
            fn: function(response) {
                if (document.getElementById('chromecast_save').checked) {
                    // save yes to userdata
                    application.set("chrome_cast_setting", response);
                    application.setDirty();
                    app_store.sync();
                    application = app_store.first();
                    glb_chrome_cast_setting = response;

                } else {
                    // save no to userdata
                    application.set("chrome_cast_setting", 0);
                    application.setDirty();
                    app_store.sync();
                    application = app_store.first();
                    glb_chrome_cast_setting = 0;
                }

                if (response == 1) {
                    if (glb_session) {
                        glb_session.removeUpdateListener(sessionUpdateListener);
                        glb_session = null;
                        glb_currentMedia = null;
                        clearInterval(glb_chromecast_update);
                    }
                    chrome.cast.initialize(apiConfig, onCastSuccess, onCastFail);
                }
            }
        });
    } else if (glb_chrome_cast_setting == 1) {
        // retry
        if (glb_session) {
            glb_session.removeUpdateListener(sessionUpdateListener);
            glb_session = null;
            glb_currentMedia = null;
            clearInterval(glb_chromecast_update);
        }
        chrome.cast.initialize(apiConfig, onCastSuccess, onCastFail);
    }
}
function receiverListener(e) {
    if (e === chrome.cast.ReceiverAvailability.AVAILABLE) {
        Ext.getCmp('chomecast_btn1').show('fade');
        Ext.getCmp('chomecast_btn2').show('fade');
        Ext.getCmp('chomecast_btn3').show('fade');
        glb_chromecast_init_check = false;
    } else if (e === chrome.cast.ReceiverAvailability.UNAVAILABLE) {
        Ext.getCmp('chomecast_btn1').setHidden(true);
        Ext.getCmp('chomecast_btn2').setHidden(true);
        Ext.getCmp('chomecast_btn3').setHidden(true);
        glb_chromecast_init_check = true;
    }
}
function onRequestSessionSuccess(e) {
    glb_session = e;
    glb_session.setReceiverVolumeLevel(glb_currentVolume, onSuccess, onFail);
    clearMask();
    if (Ext.Viewport.getActiveItem().id == 'Video' || Ext.Viewport.getActiveItem().id == 'Video2') getVideo(glb_current_video, glb_video_type, true, true);
}
function sessionListener(e) {
    glb_session = e;
    glb_session.addUpdateListener(sessionUpdateListener);
}
function sessionUpdateListener(isAlive) {
    if (!isAlive) {
        glb_session = null;
        glb_currentMedia = null;
        clearInterval(glb_chromecast_update);
    }
}
function load_youtube_legacy(video_id, video_type_id, w, h) {
    if (Ext.os.is('Android')) {
        if (typeof cordova.plugins.videoPlayer != 'undefined')
            AndroidLegacyPlay("https://www.youtube.com/watch?v=" + video_type_id);
        else
            window.plugins.videoPlayer.play("https://www.youtube.com/watch?v=" + video_type_id);
    } else
        load_webpage("https://www.youtube.com/watch?v=" + video_type_id, false);
}
function preload() {
    for (i = 0; i < preload.arguments.length; i++) {
        images[i] = new Image();
        images[i].src = preload.arguments[i];
    }
}
function pad(n, width, z) {
    z = z || '0';
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
}
function youtube_playVideo(id) {
    if (!is_yt_playing) {
        if (Ext.os.is('Android')) {
            youtube.playVideo(id, onSuccess, onFail);
            is_yt_playing = true;
        } else
            YoutubeVideoPlayer.openVideo(id);
    }
}

var onCardIOComplete = function(response) {
    var expiry_year = response.expiry_year.toString();
    var expiry_month = response.expiry_month;
    var exp_date = pad(expiry_month, 2, 0) + expiry_year.substring(2);
    Ext.getCmp('PaymentForm').setValues({
        card_num: response.card_number,
        card_code: response.cvv,
        exp_date: exp_date,
    });
};

var onCardIOCancel = function() {};
function enable_back_button() {
    glb_back_button = true;
}
function check_serv() {
    var u = '';
    var p = '';
    // try/catch added by sterling dev
    try {
        u = account.get('pw');
        p = account.get('user_id');
    } catch (e) {
        u = '';
        p = '';
    }

    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'check_version',
            pkey: u,
            user_id: p
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                // number of individual user that have messaged this user
                var count1 = items[0].count;
                if (count1 != 'NA') {
                    update_tab_badge(count1);
                    if(count1){
                    }else{
                        count1 = 0;
                    }
                    if (typeof PushNotification != 'undefined' && typeof device != 'undefined') {
                        // sets badge count for icon on phone home screen outside app
                        push.setApplicationIconBadgeNumber(function() {
                        }, function() {}, count1);
                    }
                }
            } else {
                myvAlert("Sorry, we are experiencing technical difficulties");
            }
        }
    });
}

function read_msg(user_id, pw, from_id, hack_one) { 
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'read_message',
            user_id: user_id,
            pkey: pw,
            token: glb_msg_token,
            from_user_id: from_id
        },
        callback: function(success, result) {
          // hack_one will be '1' when opening a chat history with user
          // it will be 0 or undefined all other times
            if (result) {
                if (!hack_one) { // 0 or undefined
                  // if messages are not null or 0 to avoid erasing badge
                  if (result.items[0]['unread_msgs']) {
                    glb_msg_token = result.items[0]['token'];
                    
                    update_tab_badge(result.items[0]['unread_msgs']);

                    if (typeof PushNotification != 'undefined' && typeof device != 'undefined') {
                        // try catch added due to known push notif plugin bugs on ios
                        try {
                            if(result.items[0]['unread_msgs']){
                            }else{
                                result.items[0]['unread_msgs'] = 0; // ios 13.4 doesn't like 'nill/empty' badge value
                            }
                            push.setApplicationIconBadgeNumber(function() {
                            }, function() {
                            }, result.items[0]['unread_msgs']);
                        } catch (err) {}
                    }
                  }
                } else {
                  
                  if (result.items[1] && result.items[1]['unread_msgs']) {
                    update_tab_badge(result.items[1]['unread_msgs']);
                    if (typeof PushNotification != 'undefined' && typeof device != 'undefined') {
                        try {
                            if(result.items[1]['unread_msgs']){
                            }else{
                                result.items[1]['unread_msgs'] = 0;
                            }
                            push.setApplicationIconBadgeNumber(function() {
                            }, function() {
                            }, result.items[1]['unread_msgs']);
                        } catch (err) {
                            // do nothing
                        }
                    }
                  } else {
                    update_tab_badge(null);
                    if (typeof PushNotification != 'undefined' && typeof device != 'undefined') {
                        try {
                            // clears out icon badge notifications outside app for IOS
                            push.clearAllNotifications(function() {
                            }, function() {
                            });
                        } catch (err) {
                            // do nothing
                        }
                    }
                  }
                }
           }

            if (hack_one == 1) {
                sendingMessage = false;
            }
        }
    });
}

function uuid_success(uuid) {
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'user_data',
            email: account.get('email'),
            user_id: account.get('user_id'),
            pkey: account.get('pw'),
            token: glb_msg_token,
            uuid: uuid
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                var version = items[0].version;
                if (glb_version < version) {
                    ask_to_update();
                }
            }
        }
    });
};

function onPause() {
    canPrompt = false;
    glb_lockscreen_shown = 0;
    if (!glb_lock_screen_override) {
        if (typeof device != 'undefined') {
            if (typeof cordova.plugins.idfa != 'undefined') {
				cordova.plugins.idfa.getInfo().then(function(info) {
					if (!info.limitAdTracking) {
						uuid_success(info.idfa || info.aaid);
					}
				});
            }
			else if(typeof analytics.getAdId != 'undefined') {
                var uuid_func = window.setTimeout(function() {
                    analytics.getAdId(uuid_success, onFail);
                }, 500);
			}
			else {
                update_app();
            }
        }
    } else {
        glb_lock_screen_override = false;
    }
}

function onResume() {
    if (glb_lock_screen) {
        show_lockscreen(true);
        setTimeout(startFingerprintPrompt, 1000);
    } 
    // if autoplay is on, legacy player is on, and user has tapped on legacy video
    if (typeof device != 'undefined') {
      if (glb_play_playlist && default_html5_playback == 0 && playingLegacyPlayer) {
        playingLegacyPlayer = false;
        resumePlayListLegacyPlayer();
      }
    }

    if (glb_play_playlist == 1 && glb_ad_resume == 1 && typeof device != 'undefined' && Ext.os.is('Android')) {
        // try/catch added by sterling devs
        try {
            var video = document.getElementsByTagName("video")[0];
            video.play();
            if (typeof device != 'undefined') {
                doPlayList();
            }
        } catch (err) {}
        glb_ad_resume = '';
    } else {
        clearMask();
		//setTimeout(function(){ check_serv(); }, 5000);

        if (is_yt_playing) {
            is_yt_playing = false;
        }
        //disable back button for 500 ms
        if (glb_back_button) {
            glb_back_button = false;
            setTimeout(enable_back_button, 500);
        }
        // if user is on chat list view and minimizes, upon resuming app,
        // code below will refresh list, in case, new messages were sent
        // while app was minimized. new messages appearing in bold
        if (Ext.Viewport.getActiveItem().getId() === 'MsgList') {
          list_msgs_obj.load();
        }

        if (glb_last_panel == "MsgList") {
            try {
                read_msg(account.get('user_id'), account.get('pw'), list_detailed_msgs_obj.getAt(0).get('to_u'), 1);
            } catch (err) {

            }
            list_detailed_msgs_obj.removeAll();
            list_detailed_msgs_obj.load();
        }
    }
}

function fetch_youtube_dl(master_id, video_id, type_url, video_thumbnail, video_title, storage, yt_id) {
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'fetch_youtube_dl',
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            conn: checkConnectionType(),
            id: yt_id
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                var response = items[0].response;
                if (response == "Success") {
                    var video_file = items[0].video_dl;
                    check_download_cnt(master_id, video_id, type_url, video_file, video_thumbnail, video_title, storage);
                } else {
                    myvAlert(response);
                }
            } else {
                myvAlert("Download failed, source video not found");
            }
        }
    });
}
function scrape_webpage(url) {
}
function fetch_mp4(url, items, cur_video_holder, video_thumb_top, video_thumb_left, source_playback_cnt, load_chromecast, native_video, videojs_id) {
    setMask('Loading...');
	var play_html;

    if (url == 'myvidster') {
        play_html = '<a href="javascript:void(0);" onclick="load_webpage(\'' + items[0].type_url + '\',false)"><span class="video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';
        if (Ext.get(videojs_id)) Ext.get(videojs_id).setHtml(play_html);
		clearMask();
	} else if (url == 'get_pro2') {
        var pro_spacing = '4px';
        var pro_spacing2 = '4px';
        
		if (glb_device_type == 'phone') {
            pro_spacing = '2px';
            pro_spacing2 = '1px';
        }

        if (items[0].type == 'videobydownload') {
            if (Ext.os.is('Android')) {
                play_html = '<div  id="dl_pro" onclick="pro_upgrade(\'Please renew your Pro membership to watch this video.\');" style="top:0px;width:' + Ext.get(videojs_id).getWidth() + 'px;height:' + Ext.get(videojs_id).getHeight() + 'px;z-index:999;"><a href="javascript:void(0);"><span class="video-link-span2" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a><br/><div>      <span style="position:absolute;background-color:#f5f5f5;width:100%;height:' + Ext.get(videojs_id).getHeight() + 'px;opacity:0;left:0px;top:0px;padding:' + pro_spacing + ';z-index:102;">&nbsp;</span>  <div style="position:absolute;width:100%;opacity:0.89;left:0px;background-color:#f5f5f5;font-weight:bold;padding:' + pro_spacing + ';z-index:100;font-size:1.01em;"> <span style="width:100%;left:0px;color:#478e23;font-weight:bold;padding:' + pro_spacing + ';">Please renew your PRO membership to access your cloud videos</span></div>     </div>      </div>';
            } else {
                play_html = '<div  id="dl_pro" onclick="pro_upgrade(\'Please renew your Pro membership to watch this video.\');" style="top:0px;width:' + Ext.get(videojs_id).getWidth() + 'px;height:' + Ext.get(videojs_id).getHeight() + 'px;z-index:999;"><a href="javascript:void(0);"><span class="video-link-span2" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a><br/><div>      <span style="position:absolute;background-color:#f5f5f5;width:100%;height:' + Ext.get(videojs_id).getHeight() + 'px;opacity:0;left:0px;top:0px;padding:' + pro_spacing + ';z-index:102;">&nbsp;</span>  <div style="position:absolute;width:100%;opacity:0.89;left:0px;background-color:#f5f5f5;font-weight:bold;padding:' + pro_spacing + ';z-index:100;font-size:1.65em;"> <span style="width:100%;left:0px;color:#478e23;font-weight:bold;padding:' + pro_spacing + ';">Please renew your PRO membership to access your cloud videos</span></div>     </div>      </div>';
            }

            var console = Ext.get(videojs_id);
            var html = console.getHtml();

            html = play_html + html;

            if (Ext.get(videojs_id)) Ext.get(videojs_id).setHtml(html);

        } else {
            play_html = '<a href="javascript:void(0);" onclick="pro_upgrade(\'To view this premium video please upgrade to PRO\')"><span class="video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a><br/><div> <span style="position:absolute;background-color:#f5f5f5;width:100%;opacity:0.89;left:0px;padding:' + pro_spacing2 + ';">&nbsp;</span><span style="position:absolute;width:100%;left:0px;color:#478e23;font-weight:bold;padding:' + pro_spacing2 + ';">Premium Video</span>  </div>';
            if (Ext.get(videojs_id)) Ext.get(videojs_id).setHtml(play_html);
        }

        premium_slideshow_index = 0;
        premium_slideshow();
		clearMask();
    } 
	else {
		var yt_data;
		glb_source_dl = false;
		
		if (Ext.os.is('iOS')) {
			var options = { method: 'post' };
			cordova.plugin.http.sendRequest(url, options, 
				function(response) {
					yt_data = response.data;
					mp4_scrape(yt_data, url, items, cur_video_holder, video_thumb_top, video_thumb_left, source_playback_cnt, load_chromecast, native_video, videojs_id);
				}, 
				function(response) {
					glb_ver = 1;
					myvAlert("failure please try again");
					report_video(items[0].video_id, items[0].master_id, items[0].ip, "mobile_auto_broken", true);
					clearMask();
			});
		}
		else {
			var requestId = Ext.Ajax.request({
			url: url,
			method: 'POST',
			timeout: 10000,
			success: function(response) {
				yt_data = response.responseText;
				mp4_scrape(yt_data, url, items, cur_video_holder, video_thumb_top, video_thumb_left, source_playback_cnt, load_chromecast, native_video, videojs_id);
			},
			failure: function(response) {
				glb_ver = 1;
                myvAlert("failure please try again");
                report_video(items[0].video_id, items[0].master_id, items[0].ip, "mobile_auto_broken", true);
				clearMask();
              },
            });
		}
	}
}

function mp4_scrape(yt_data, url, items, cur_video_holder, video_thumb_top, video_thumb_left, source_playback_cnt, load_chromecast, native_video, videojs_id) {
	var mp4s = new Array();
	var play_html;
	var z = 0;
	var string1 = "http";
    var string2 = "https";
    var use_http = '';
    
    if (url.indexOf(string2) > -1) {
        use_http = string2;
    } else {
        use_http = string1;
    }

	var tmp = yt_data.replace(/\n\r|\r\n|\n|\r|\t/g, '').stripSlashes();
	
	if (url.search("bitporno.sx") >= 0) {
		tmp = tmp.replace(/,/g, " ");
	}
		
	var uri_pattern = /[-a-zA-Z0-9@:%_\+.~#?&\/\/=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&\/\/=,]*)?/gi;
	var urls = tmp.match(uri_pattern);

	if (urls) {
		var arrayLength = urls.length;
		for (var i = 0; i < arrayLength; i++) {
			if (urls[i].search(/\.mp4/i) > 0 || urls[i].search(/oloadcdn\.net/i) > 0 || urls[i].search(/\.m3u8/i) > 0 && urls[i].search(/videos\/tmb/i) < 0) {
				mp4s[z] = urls[i];
				z++;
			}
		}
		
		if (!mp4s[0]) {
			var uri_pattern = /\b((?:[a-z][\w-]+:(?:\/{1,3}|[a-z0-9%])|www\d{0,3}[.]|[a-z0-9.\-]+[.][a-z]{2,4}\/)(?:[^\s()<>]+|\(([^\s()<>]+|(\([^\s()<>]+\)))*\))+(?:\(([^\s()<>]+|(\([^\s()<>]+\)))*\)|[^\s`!()\[\]{};:'".,<>?«»“”‘’]))/ig;
			var urls = tmp.match(uri_pattern);
			var arrayLength = urls.length;
			for (var i = 0; i < arrayLength; i++) {
				if (urls[i].search(/\.mp4/i) > 0 || urls[i].search(/oloadcdn\.net/i) > 0 || urls[i].search(/\.m3u8/i) > 0) {
					mp4s[z] = urls[i];
					z++;
				}
			}
		}

		if (mp4s[source_playback_cnt])  { 
			var mp4 = mp4s[source_playback_cnt];
		}
		else  { 
			var mp4 = mp4s[0];
		}
					
		if (mp4) {
			if (mp4.search('.mp4"/') >= 0) {
				mp4 = mp4.replace('.mp4"/', ".mp4");
			}

			if (mp4.charAt(0) == '/' && mp4.charAt(1) == '/') {
				mp4 = use_http + ':' + mp4;
			}
			glb_source_dl = mp4;
		}
	}

	if (mp4) {
		if (mp4.search(/oloadcdn\.net/i) > 0 && mp4.search(/\.mp4/i) <= 0) {
			mp4 += "&xfile=1.mp4";
		}
	
		if (native_video || default_html5_playback == 0) {
			error_obj = {video_id: items[0].video_id, master_id: items[0].master_id, ip: items[0].ip};
			glb_video_file = mp4;
			play_html = '<a href="javascript:void(0);" onclick="playVideo_handler(\'' + mp4 + '\')"><span class="video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';
			
			if (glb_session) play_html += '<a href="javascript:void(0);" onclick="chomecast_load(\'' + mp4 + '\',\'' + items[0].video_thumbnail + '\',\'' + items[0].video_title_encoded + '\',\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="casticon-span" style="right:' + 5 + 'px;top:' + 5 + 'px;"></span></a>';

			if (load_chromecast) {
				chomecast_load(mp4, items[0].video_thumbnail, items[0].video_title_encoded, items[0].width, items[0].height);
			}

			if (Ext.get(videojs_id)) Ext.get(videojs_id).setHtml(play_html);
			clearMask();
		}
		else {
			if (load_chromecast) {
				chomecast_load(mp4, items[0].video_thumbnail, items[0].video_title_encoded, items[0].width, items[0].height);
			}
			glb_myPlayer.src(mp4);
			clearMask();
		}
	} 
	else {
		glb_ver = 1;
		report_video(items[0].video_id, items[0].master_id, items[0].ip, "mobile_auto_broken", true);

		setTimeout(function() {
			clearMask();
			getVideo(glb_current_video, glb_video_type, true);
		}, 2000);
	}
}

function fetch_banner_ad(banner_api, video_id, w) {
    Ext.Ajax.request({
        url: banner_api,
        method: 'GET',
        success: function(response) {
            var obj = JSON.parse(response.responseText);
            var ad = '<a href="javascript:void(0);" onclick="load_webpage(\'' + obj.url + '\',false)">';
            ad += '<img width="300" height="50" src="' + obj.bannerurl + '"/>';
            ad += '</a>';

            Ext.get('top_banner_' + video_id).setHtml(ad);
        },
        failure: function(response) {
            myvAlert("failure " + response.responseText);
        }
    });
}

function upload_photo() {
    if (typeof device != 'undefined') {
        glb_lock_screen_override = 1;
        navigator.notification.confirm('Select an option below.',
            function(buttonIndex) {
                if (buttonIndex == 1) {
                    capturePhotoEdit();
                } else if (buttonIndex == 2) {
                    getPhoto(pictureSource.PHOTOLIBRARY);
                }
            }, 'Photo upload', 'take photo,library,cancel');
    } else {
        myvAlert('Photo upload not supported on the web app');
    }
}

function onPhotoDataSuccess(imageURI) {
    var options = new FileUploadOptions();
    options.fileKey = "file";
    options.fileName = imageURI.substr(imageURI.lastIndexOf('/') + 1);
    options.mimeType = "image/jpeg";

    var params = {};
    params.user_id = account.get('user_id');

    options.params = params;

    var ft = new FileTransfer();
    ft.upload(imageURI, encodeURI("https://api.myvidster.com/mobile_upload.php"), upload_win, upload_fail, options);
}

function upload_win(r) {
    //update user account photo
    loginUser(account.get('email'), account.get('pw'), false);
}

function upload_fail(error) {
    myvAlert("An error has occurred: Code = " + error.code);
    clearMask();
}

function capturePhotoEdit() {
    // Take picture using device camera, allow edit, and retrieve image as base64-encoded string
    navigator.camera.getPicture(onPhotoDataSuccess, onFail, {
        quality: 80,
        correctOrientation: true,
        targetHeight: 800,
        destinationType: destinationType.FILE_URI
    });
}

function getPhoto(source) {
    // Retrieve image file location from specified source
    navigator.camera.getPicture(onPhotoDataSuccess, onFail, {
        quality: 80,
        correctOrientation: true,
        destinationType: destinationType.FILE_URI,
        sourceType: source
    });
}

function onFail(message) {
    clearMask();
}

function onSuccess(message) {
}

var MyVPlugin = {
    callNativeFunction: function(success, fail, resultType) {
        return cordova.exec(success, fail, "MyVPlugin", "nativeAction", [resultType]);
    }
};

function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [, null])[1]
    );
}

function abort_download() {
    navigator.notification.confirm('Are you sure you want to abort the download?',
        function(buttonIndex) {
            if (buttonIndex == 1) glb_fileTransfer.abort();
        }, 'myVidster', 'Abort,Cancel');
}

function download_process(fileTransfer) {
    glb_fileTransfer = fileTransfer;
    fileTransfer.onprogress = function(progressEvent) {
        if (progressEvent.lengthComputable) {
            var tmp_loaded_fix = progressEvent.loaded;
            var tmp_percent = Math.floor(tmp_loaded_fix / progressEvent.total * 100);
            var tmp_loaded = roundNumber(tmp_loaded_fix / 1024 / 1024, 2);
            var tmp_total = roundNumber(progressEvent.total / 1024 / 1024, 2);
            glb_loadingStatus = tmp_loaded + ' MBs out of ' + tmp_total + ' MBs (' + tmp_percent + '%)';
        } else {
            glb_loadingStatus++;
        }
        if (tmp_percent == 100) glb_loadingStatus = 0;
    };
}

function callNativePlugin(returnSuccess) {
    if (returnSuccess == 'com.google.android.youtube')
        MyVPlugin.callNativeFunction(function(result) {
            youtubeInstalled = result
        }, nativePluginErrorHandler, returnSuccess);
    else
        MyVPlugin.callNativeFunction(function(result) {
            flashInstalled = result
        }, nativePluginErrorHandler, returnSuccess);
}

function nativePluginErrorHandler(error) {
    myvAlert("ERROR: \r\n" + error);
}

function video_not_found(master_id, video_file, download_id) {
    if (video_file !== '') {
        var uniquify = Math.random();
        navigator.notification.confirm('Video not found. Would you like to delete it?',
            function(buttonIndex) {
                if (buttonIndex == 2) {
                    delete_video(master_id, video_file, download_id);
                }
            }, 'myVidster', 'No Thanks,Yes!');
    }
}

function checkConnection() {
    var networkState = navigator.network.connection.type;
    var states = {};
    states[Connection.UNKNOWN] = 'Unknown connection';
    states[Connection.ETHERNET] = 'Ethernet connection';
    states[Connection.WIFI] = 'WiFi connection';
    states[Connection.CELL_2G] = 'Cell 2G connection';
    states[Connection.CELL_3G] = 'Cell 3G connection';
    states[Connection.CELL_4G] = 'Cell 4G connection';
    states[Connection.NONE] = 'No network connection';

    if (states[networkState] == 'No network connection')
        return false;
    else
        return true;
}
function onRequestFileSystemSuccess(fileSystem) {
    fileSystem.root.getDirectory("myv_videos", {
        create: true,
        exclusive: false
    }, onGetDirectorySuccess, onGetDirectoryFail);
}

function onGetDirectorySuccess(dir) {
    glb_dataDir = dir;
    glb_myv_videos_dir = dir.toURL();
}

function onGetDirectoryFail(error) {}

function delete_video(master_id, video_file, download_id, rowIndex) {
    glb_disable_ori_change = 1;
    setMask('Deleting...');
    var uniquify = Math.random();
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            id: master_id,
            video_file: video_file,
            download_id: download_id,
            type: 'remove_download',
            uniquify: uniquify
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var status = result.items[0].status;
                if (status == 'Success' && video_file && video_file.substring(0, 4) != 'http') {
                    if (video_file.substring(0, 4) != 'file') video_file = "file://" + video_file;
                    window.resolveLocalFileSystemURI(video_file,
                        function(fileEntry) {
                            fileEntry.remove(function(entry) {
                                myvAlert("Your video has been deleted");
                                // try/catch needed because back btn can 
                                if(!rowIndex){ deletion_backs(); }
                            }, function(error) {
                                myvAlert("Error removing video " + error.code);
                            });
                        },
                        function() {
                            myvAlert("Your video has been deleted");
                            if(!rowIndex){ deletion_backs(); }
                        }
                    );
                } else if (status != 'Success') {
                    myvAlert(status);
                } else {
                    myvAlert("Your video has been deleted");
                    if(!rowIndex){ deletion_backs(); }
                }
                glb_disable_ori_change = 0;
                list_videos_obj.currentPage = 1;
                if(rowIndex){
                    var scroller7h = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                    var x = scroller7h.position.x;
                    var y = scroller7h.position.y+0.01;
                    list_videos_obj.removeAt(rowIndex);
                    scroller7h.scrollTo(x, y);
                }else{
                    glb_seb_back = 1;
                    list_videos_obj.load();
                    list_videos('Downloads', account.get('user_id'), 0);
                }
            }
        }
    });
}


function mute_subscription(s_id, mute) {
    if (slider_pressed == '1') {
        slider_pressed = '0';
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                user_id: account.get('user_id'),
                token: glb_msg_token,
                pro_status: glb_is_pro_ckeck,
                pkey: account.get('pw'),
                id: s_id,
                type: 'mute_subscription',
                status: mute
            },
            callback: function(success, result) {
                clearMask();
                if (result.items) {
                    var status = result.items[0].response;
                    if (status == 'Success') {} else {
                        myvAlert("Subscription change error");
                    }
                    glb_seb_back = 1;
                    list_videos_obj.load();
                    slider_pressed = '1';
                } else {
                    slider_pressed = '1';
                }
            }
        });
    }
}

function delete_chat_hist(to, last_id, action, rowIndex) {
    if (slider_pressed == '1') {
        slider_pressed = '0';
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                user_id: account.get('user_id'),
                pkey: account.get('pw'),
                type: 'delete_chat_hist',
                token: glb_msg_token,
                friend_id: to,
                last_id: last_id,
                status: '0',
                block: action
            },
            callback: function(success, result) {
                clearMask();
                if (result.items) {
                    var status = result.items[0].response;
                    if (status == 'Success') {} else {
                        myvAlert("Deletion error");
                    }

                    var dt = new Date();
                    var offset_time = dt.getTimezoneOffset();

                    var limit = 12;

                    if (glb_device_type == 'tablet') {
                        limit = 14;
                    }

                    list_msgs_obj.getProxy().setExtraParams({
                        type: "messages_list",
                        user_id: account.get('user_id'),
                        pkey: account.get('pw'),
                        token: glb_msg_token,
                        start: 0,
                        limit: limit,
                        device_type: glb_device_type,
                        id: Math.random(),
                        offset: offset_time
                    });

                    list_msgs_obj.currentPage = 1;

                    if(rowIndex){
                        var scroller7h = Ext.getCmp('msglistscroll').getScrollable().getScroller();
                        var x = scroller7h.position.x;
                        var y = scroller7h.position.y+0.01;
                        list_msgs_obj.removeAt(rowIndex);
                        scroller7h.scrollTo(x, y);
                    }else{
                        list_msgs_obj.load();
                    }
                    slider_pressed = '1';
                    ////// for block user
                    if (last_id == '' || !last_id) {
                        try {
                            // try/catch added for fast clicks
                            var remove_oncl = document.getElementsByClassName('follows_2');
                            remove_oncl[0].innerHTML = "Unblock";

                            if(rowIndex){
                                var scroller7h = Ext.getCmp('msglistscroll').getScrollable().getScroller();
                                var x = scroller7h.position.x;
                                var y = scroller7h.position.y+0.01;
                                list_msgs_obj.removeAt(rowIndex);
                                scroller7h.scrollTo(x, y);
                            }
                            else{
                                list_msgs_obj.load();
                            }
                        } catch (err) {}
                    }
                } else {
                    slider_pressed = '1';
                }
            }
        });
    }
}

function delete_queue(v_id, status, rowIndex) {
    if (slider_pressed == '1') {
        slider_pressed = '0';
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                user_id: account.get('user_id'),
                token: glb_msg_token,
                pro_status: glb_is_pro_ckeck,
                pkey: account.get('pw'),
                type: 'update_queue',
                id: v_id,
                status: status
            },
            callback: function(success, result) {
                clearMask();
                var scroller7h = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                var x = scroller7h.position.x;
                var y = scroller7h.position.y+0.01;
                list_videos_obj.removeAt(rowIndex);
                scroller7h.scrollTo(x, y);

                if (result.items) {
                    var status = result.items[0].response;
                    if (status == 'Success') {} else {
                        myvAlert("Error");
                    }

                    slider_pressed = '1';

                } else {
                    slider_pressed = '1';
                }
            }
        });
    }
}

function edit_collection(c_id, select) {
    if (slider_pressed == '1') {
        slider_pressed = '0';
        var type_select = '';
        if (select == 'collection') {
            Ext.getCmp('EditCollectionToolbar').setTitle('Edit Collection');
            type_select = 'fetch_collection';
        } else {
            Ext.getCmp('EditCollectionToolbar').setTitle('Edit Channel');
            type_select = 'fetch_channel';
        }
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                user_id: account.get('user_id'),
                token: glb_msg_token,
                pro_status: glb_is_pro_ckeck,
                pkey: account.get('pw'),
                type: type_select,
                id: c_id
            },
            callback: function(success, result) {
                if (result.items) {
                    var status = result.items[0].response;
                    var title = result.items[0].title;
                    var description = result.items[0].description;
                    Ext.getCmp('edit_col_title').setValue(title);
                    Ext.getCmp('edit_desc_col_text').setValue(description);
                    Ext.getCmp('col_id').setValue(c_id);
                    Ext.getCmp('col_type_id').setValue(type_select);
                    clearMask();
                    Ext.Viewport.animateActiveItem("EditCollection", {
                        type: 'fade',
                    });
                    searchviewbackhack();
                    custom_views_arr.push("EditCollection");
                    glb_custom_views_arr_data.push([0]);
                    slider_pressed = '1';
                } else {
                    slider_pressed = '1';
                    myvAlert("Error");
                }
            }
        });
    }
}

function edit_col(col1, col2, col3, col4) {
    setMask('Updating...');
    var update_type = '';
    if (col4 == 'fetch_collection') {
        update_type = 'update_collection';
    } else {
        update_type = 'update_channel';
    }
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: update_type,
            id: col3,
            title: col1,
            description: col2,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            uniquify: uniquify
        },
        callback: function(success, result) {
            if (result) {

                Ext.getCmp('edit_col_title').setValue(col1);
                Ext.getCmp('edit_desc_col_text').setValue(col2);
                Ext.getCmp('col_id').setValue(col3);
                Ext.getCmp('col_type_id').setValue(col4);
                if (col4 == 'fetch_collection') {
                    list_videos_obj.getProxy().setExtraParams({
                        type: 'User',
                        id: account.get('user_id'), //col3,
                        user_id: account.get('user_id'),
                        pkey: account.get('pw'),
                        token: glb_msg_token,
                        start: '',
                        limit: '',
                        version: glb_version,
                        family_filter: family_filter,
                        html5: glb_HTML5_only,
                        q: '',
                        uniquify: Math.random()
                    });
                    // try/catch needed added in case of server lag
                    try{
                        custom_views_arr.pop();
                        glb_custom_views_arr_data.pop();
                    }catch(err){}

                    list_videos_obj.currentPage = 1;
                    glb_seb_back = 1;
                    list_videos_obj.load();
                } else {
                    list_videos_obj.getProxy().setExtraParams({
                        type: 'Collection',
                        id: glb_back_data_channel_bug,
                        user_id: account.get('user_id'),
                        pkey: account.get('pw'),
                        token: glb_msg_token,
                        start: '',
                        limit: '',
                        version: glb_version,
                        family_filter: family_filter,
                        html5: glb_HTML5_only,
                        q: '',
                        uniquify: Math.random()
                    });
                    try{
                        custom_views_arr.pop();
                        glb_custom_views_arr_data.pop();
                    }catch(err){}

                    list_videos_obj.currentPage = 1;
                    glb_seb_back = 1;
                    list_videos_obj.load();
                }
                clearMask();
            } else {
                myvAlert("Error");
                clearMask();
            }
        }
    });
}

function delete_video_collection(video_id) {
    if (typeof device != 'undefined') {
        navigator.notification.confirm('Click yes to confirm.',
            function(buttonIndex) {
                if (buttonIndex == 1) delete_video_collection2(video_id);
                if (buttonIndex == 2) {
                }
            }, 'Delete Video?', 'yes,cancel');
    } else {
        var msg = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msg.show({
            title: 'Delete Video?',
            msg: 'Select an option.',
            buttons: [{
                text: 'yes',
                itemId: 'yes'
            }, {
                text: 'cancel',
                itemId: 'cancel'
            }],
            fn: function(response) {
                if (response == 'yes') {
                    delete_video_collection2(video_id);
                } else if (response == 'cancel') {

                }
            }
        });
    }
}

function delete_video_book(video_id, rowIndex) {
    if (typeof device != 'undefined') {
        navigator.notification.confirm('Click yes to confirm.',
            function(buttonIndex) {
                if (buttonIndex == 1) delete_video_book2(video_id, rowIndex);
                if (buttonIndex == 2) {

                }
            }, 'Delete Video?', 'yes,cancel');

    } else {
        var msg = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msg.show({
            title: 'Delete Video?',
            msg: 'Select an option.',
            buttons: [{
                text: 'yes',
                itemId: 'yes'
            }, {
                text: 'cancel',
                itemId: 'cancel'
            }],
            fn: function(response) {
                if (response == 'yes') {
                    delete_video_book2(video_id, rowIndex);
                } else if (response == 'cancel') {

                }
            }
        });
    }
}

function delete_video_collection2(video_id) {
    setMask('Deleting...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            id: video_id,
            type: 'remove_video'
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var status = result.items[0].response;
                if (status == 'Success') {
                    myvAlert("Video has been removed");
                    deletion_backs();
                } else {
                    myvAlert("Video has not been removed");
                }
                list_videos('User', account.get('user_id'), 0);
            }
        }
    });
}

function delete_video_book2(video_id, rowIndex) {
    setMask('Deleting...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            id: video_id,
            type: 'remove_video'
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var status = result.items[0].response;
                if (status == 'Success') {
                    myvAlert("Video has been removed");
                } else {
                    myvAlert("Video has not been removed");
                }
   

                if(rowIndex){
                    var scroller7h = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                    var x = scroller7h.position.x;
                    var y = scroller7h.position.y+0.01;
                    list_videos_obj.removeAt(rowIndex);
                    scroller7h.scrollTo(x, y);
                }
                else{
                    glb_seb_back = 1;
                    list_videos_obj.currentPage = 1;
                    list_videos_obj.load();
                }
            }
        }
    });
}

function reply_comment(reply_name){
    document.getElementById("show_comments2").click();
    Ext.getCmp('comment_post_id').focus();
    document.getElementById("comment_post_id").focus();
    Ext.getCmp('videoCommentForm').setValues({
        comment_post: '@'+reply_name+' '
    });
    // notify the user !
}

function remove_comment(video_id, comment_id, owner) {
    if(account.get('user_id')){
    if (typeof device != 'undefined') {
        navigator.notification.confirm('Click yes to confirm.',
            function(buttonIndex) {
                if (buttonIndex == 1) remove_comment_func(video_id, comment_id, owner);
                if (buttonIndex == 2) {
                }
            }, 'Report or remove comment?', 'yes,cancel');

    } else {
        var msg = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msg.show({
            title: 'Report or remove comment?',
            msg: 'Select an option.',
            buttons: [{
                text: 'yes',
                itemId: 'yes'
            }, {
                text: 'cancel',
                itemId: 'cancel'
            }],
            fn: function(response) {
                if (response == 'yes') {
                    remove_comment_func(video_id, comment_id, owner);
                } else if (response == 'cancel') {

                }
            }
        });
    }
  }else{
    myvAlert("Please login/signup first");
  }
}

function remove_comment_func(video_id, comment_id, owner) {
    setMask('Loading...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            video_id: video_id,
            id: comment_id,
            type: 'remove_comment',
            owner: owner
        },
        callback: function(success, result) {
            clearMask();

            if (result.items) {
                var status = result.items[0].response;
                if (status == 'Success') {

                    if(owner == 1){
                        myvAlert("Comment has been removed");
                    }else{
                        myvAlert("Comment has been reported");
                    }
                            var cmt_id = "cmt_"+String(comment_id);
                            document.getElementById(cmt_id).style.display = "none";
                            var cmt_cnt = document.getElementById("cmt_cnt").innerText;
                            cmt_cnt = parseInt(cmt_cnt) - 1;
                            document.getElementById("comment_id_holder").innerText = String(cmt_cnt)+" Comments";
                            document.getElementById("cmt_cnt").innerText = String(cmt_cnt);
                } else {
                    myvAlert("An error has occurred");
                }
            }
        }
    });
}

function check_download_cnt(master_id, video_id, type_url, video_file, video_thumbnail, video_title, storage) {
    var uniquify = Math.random();	
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            type: 'check_download_cnt',
            conn: checkConnectionType(),
            storage: storage,
            uniquify: uniquify
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                if (storage == "cloud") {
                    var status = result.items[0].status;
                    if (status == 'Pro') {
                        pro_upgrade("PRO membership is needed to use private cloud storage.");
                        return true;
                    }
                }
                download_video(master_id, video_id, type_url, video_file, video_thumbnail, video_title, storage);
            }
        }
    });
}

function roundNumber(num, dec) {
    var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
    return result;
}

function download_video(master_id, video_id, type_url, video_file, thumbnail, video_title, storage) {	
    var uniquify = Math.random();
    if (storage != 'cloud') {
        var fileTransfer = new FileTransfer();
    }
    var filePath = glb_myv_videos_dir + master_id + ".mp4";
    var uri;
    if (video_file != 'null') {
      uri = video_file;
    } else {
      uri = glb_source_dl;
    }
    //local phone storage
    if (storage == "phone") {
		if(false) {
			native_vid_download(uri);
		}
		else {
			myvAlert("Download in progress, you will be alerted when finished.");
			glb_disable_ori_change = 1;
			glb_downloading = 1;
			glb_dl_title = video_title;
			acquire();
			download_process(fileTransfer);
			fileTransfer.download(
				uri,
				filePath,
				function(entry) {
					var uniquify = Math.random();
					Ext.util.JSONP.request({
						url: 'https://api.myvidster.com/mobile_json2.php',
						callbackKey: 'callback',
						params: {
                            user_id: account.get('user_id'),
                            token: glb_msg_token,
                            pro_status: glb_is_pro_ckeck,
							pkey: account.get('pw'),
							id: master_id,
							video_id: video_id,
							type_url: type_url,
							title: video_title,
							thumbnail: thumbnail,
							filepath: filePath,
							conn: checkConnectionType(),
							type: 'add_download',
							uniquify: uniquify
						},
						callback: function(success, result) {
							clearMask();
							if (result.items) {
								var status = result.items[0].status;
								var dl_id = result.items[0].dl_id;
								if (status == 'Success') {
									navigator.notification.beep(1);
									navigator.notification.confirm('Download completed',
										function(buttonIndex) {
											if (buttonIndex == 2) {
												getVideo(dl_id, 'videobydownload', true);
											}
										}, 'myVidster', 'Stay here,Go to video');
								}
								glb_downloading = 0;
								if (glb_current_list['type'] == 'Downloads') Ext.getCmp('myUserList').removeAll();
								glb_disable_ori_change = 0;
								release();
							}
						}
					});
				},
				function(error) {
					glb_downloading = 0;
					if (glb_current_list['type'] == 'Downloads') Ext.getCmp('myUserList').removeAll();
					glb_disable_ori_change = 0;
					release();
					navigator.notification.beep(1);
					if (error.code == FileTransferError.ABORT_ERR) {
						myvAlert("Download aborted.");
						list_videos('Downloads', account.get('user_id'), 0, 1);
						window.resolveLocalFileSystemURI(filePath,
							function(fileEntry) {
								fileEntry.remove(function(entry) {	
								}, function(error) {
								});
							},
							function() {}
						);
					} else if (error.code == FileTransferError.CONNECTION_ERR) {
						myvAlert("Download failed please check your internet connection and verify that you have sufficient space on your SD Card");
					} else
						myvAlert("download error code" + error.code);
				}
			);
		}
    } else {
        setMask('Downloading...');
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                user_id: account.get('user_id'),
                token: glb_msg_token,
                pro_status: glb_is_pro_ckeck,
                pkey: account.get('pw'),
                title: video_title,
                id: master_id,
                video_id: video_id,
                type_url: type_url,
                type: 'cloud_storage',
                thumbnail: thumbnail,
                uri: uri,
                uniquify: uniquify
            },
            callback: function(success, result) {
                clearMask();
                if (result.items) {
                    var status = result.items[0].status;
                    var dl_id = result.items[0].dl_id;
                    if (status == 'Success') {

                        if (typeof device != 'undefined') {

                            navigator.notification.beep(1);
                            navigator.notification.confirm('Video is uploading to the cloud.',
                                function(buttonIndex) {
                                    if (buttonIndex == 2) {
                                        list_videos('Downloads', account.get('user_id'), 0);
                                    }
                                }, 'myVidster', 'Stay here,Go to downloads');

                        } else {
                            var msg = new Ext.MessageBox({
                                width: 300,
                                floating: 1,
                                margin: '80 0 0 0'
                            });
                            msg.show({
                                title: 'Video is uploading to the cloud.',
                                msg: 'Select an option.',
                                buttons: [{
                                    text: 'Go to video',
                                    itemId: '1'
                                }, {
                                    text: 'Stay here',
                                    itemId: '2'
                                }],
                                fn: function(response) {
                                    if (response == '1') {
                                        list_videos('Downloads', account.get('user_id'), 0);
                                    } else if (response == '2') {
                                    } else {
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
    }
}

var notification_check = function() {
    var user_id = account.get('user_id');
    var uniquify = Math.random();
    if (user_id) {
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                user_id: user_id,
                type: 'notification',
                token: glb_msg_token,
                uniquify: uniquify
            },
            callback: function(success, result) {
                clearMask();
                if (result.items) {
                    var status = result.items[0].status;
                    if (status == 'Success') {
                        var message = result.items[0].message;
                        navigator.systemNotification.createStatusBarNotification('myVidster', message, 'Notification');
                        if (glb_nofity_cnt == 1) navigator.notification.beep(1);
                        Ext.getCmp('home_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        Ext.getCmp('collect_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        Ext.getCmp('comment_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        Ext.getCmp('search_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        Ext.getCmp('user_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        Ext.getCmp('settings_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        Ext.getCmp('userMenu_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        Ext.getCmp('general_tabbar').items.getAt(1).setBadge(glb_nofity_cnt);
                        glb_nofity_cnt++;
                    }
                }
            }
        });
    }
}

var record_video = function(video_title, channel, video_tags, access) {
    setMask('Uploading...');
    glb_file_name = "video_" + account.get('user_id') + "_" + Math.round(new Date().getTime() / 1000) + ".mp4";
    glb_upload_video_title = video_title;
    glb_upload_channel = channel;
    glb_upload_video_tags = video_tags;
    glb_upload_access = access;
    acquire();
    captureVideo();
}

function captureSuccess(mediaFiles) {
    var i, len;
    for (i = 0, len = mediaFiles.length; i < len; i += 1) {
        uploadFile(mediaFiles[i]);
    }
}

function captureError(error) {
    var msg = 'An error occurred during capture: ' + error.code;
    navigator.notification.alert(msg, null, 'Uh oh!');
}

function captureVideo() {
    // Launch device video recording application,
    // allowing user to capture up to 2 video clips
    navigator.device.capture.captureVideo(captureSuccess, captureError, {
        limit: 1
    });
}

function uploadFile(mediaFile) {
    var ft = new FileTransfer(),
        path = mediaFile.fullPath,
        name = mediaFile.name;

    var options = new FileUploadOptions();
    options.fileKey = "file";
    options.fileName = name;
    options.mimeType = "video/mpeg";
    var params = new Object();
    params.video_name = glb_file_name;
    options.params = params;
    ft.upload(path,
        "https://api.myvidster.com/mobile_upload.php",
        function(result) {
            add_video();
            clearMask();
        },
        function(error) {
            clearMask();
        },
        options);
}


var set_video_history = function(video_id) { 
    if (!glb_video_history_chk)
        return;
    var video_unique = true;
    if (glb_video_history.length == 55 && !localStorage.getItem('hasRated') && typeof device != 'undefined' && Ext.os.is('iOS')) {
        localStorage.setItem('hasRated', true);
        navigator.notification.confirm('Show MyVidster some love by rating our app!',
            function(buttonIndex) {
                if (buttonIndex == 2) {
                    if (Ext.os.is.iOS)
                        load_webpage('https://itunes.apple.com/us/app/myvidster/id611470289?mt=8', true);
                }
            }, 'myVidster', 'No Thanks,Rate');
    }
    for (var i = 0; i < glb_video_history.length; i++) {
        if (glb_video_history[i] == video_id) {
            video_unique = false;
            glb_video_history.move(i, glb_video_history.length - 1);
        }
    }
    if (video_unique) {
        if (glb_video_history.length == glb_video_history_limit)
            glb_video_history.shift();

        glb_video_history.push(video_id);
    }
   localStorage.setItem('video_history', JSON.stringify(glb_video_history));
    account.set("video_history", glb_video_history);//you do a store sync in the video func
};

Array.prototype.move = function(old_index, new_index) {
    if (new_index >= this.length) {
        var k = new_index - this.length;
        while ((k--) + 1) {
            this.push(undefined);
        }
    }
    this.splice(new_index, 0, this.splice(old_index, 1)[0]);
    return this; // for testing purposes
};

var acquire = function() {
    if (typeof device != 'undefined' && !glb_is_acquired) {
        window.plugins.insomnia.keepAwake();
        set_glb_is_acquired(1);
    }
};

var release = function() {
    if (typeof device != 'undefined' && glb_is_acquired) {
        glb_is_acquired = 0;
        window.plugins.insomnia.allowSleepAgain();
        set_glb_is_acquired(0)
    }
}

var set_glb_is_acquired = function(x) {
    glb_is_acquired = x;
};

var onSwipe = function(direction, loc) {
    var x;
    var reverse;

    if (direction == 'right') {
        x = loc - 1;
        reverse = true;
    } else if (direction == 'left') {
        x = loc + 1;
        reverse = false;
    }
    switch (x) {
        case 0: //sort

            break;
        case 1: //home
            glb_last_panel = Ext.Viewport.getActiveItem().id;
            Ext.Viewport.animateActiveItem("Home", {
                type: 'fade',
            });

            searchviewbackhack();
            custom_views_arr.push("Home");
            glb_custom_views_arr_data.push([0]);
            break;
        case 2: //UserMenu
            glb_last_panel = Ext.Viewport.getActiveItem().id;
            if (account.get("user_id")) {
                Ext.Viewport.animateActiveItem("UserMenu", {
                    type: 'fade',
                });

                searchviewbackhack();
                custom_views_arr.push("UserMenu");
                glb_custom_views_arr_data.push([0]);
            } else {
                Ext.Viewport.animateActiveItem("UserLogin", {
                    type: 'fade',
                });
                searchviewbackhack();
                custom_views_arr.push("UserLogin");
                glb_custom_views_arr_data.push([0]);
            }
            break;
        case 3: //Search
            glb_last_panel = Ext.Viewport.getActiveItem().id;
            Ext.Viewport.animateActiveItem("Search", {
                type: 'fade',
            });
            searchviewbackhack();
            custom_views_arr.push("Search");
            glb_custom_views_arr_data.push([0]);
            break;
        case 4: //Settings
            glb_last_panel = Ext.Viewport.getActiveItem().id;
            Ext.Viewport.animateActiveItem("Settings", {
                type: 'fade',
            });
            searchviewbackhack();
            custom_views_arr.push("Settings");
            glb_custom_views_arr_data.push([0]);
            break;
        case 5: //General
            load_more();
            Ext.Viewport.animateActiveItem("General", {
                type: 'fade',
            });
            searchviewbackhack();
            custom_views_arr.push("General");
            glb_custom_views_arr_data.push([0]);
            break;
        default:
            break;
    }
};

var onSwipeUserList = function(direction) {
    if (direction == 'right') {
        if (account.get("user_id")){
            Ext.Viewport.animateActiveItem("UserMenu", {
                type: 'fade',
            });
            searchviewbackhack();
            custom_views_arr.push("UserMenu");
            glb_custom_views_arr_data.push([0]);
        }
        else{
            Ext.Viewport.animateActiveItem("Home", {
                type: 'fade',
            });
            searchviewbackhack();
            custom_views_arr.push("Home");
            glb_custom_views_arr_data.push([0]);
        }
    } else if (direction == 'left') {
        Ext.Viewport.animateActiveItem("Search", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("Search");
        glb_custom_views_arr_data.push([0]);
    }
};

var onSwipeVideo = function(direction) {
    if (direction == 'up' || direction == 'down') return true;

    if (direction == 'left' && glb_next_video != '' && glb_next_video != null) {
        getVideo(glb_next_video, glb_video_type, true);
    } else if (direction == 'right' && glb_prev_video != '' && glb_prev_video != null) {
        getVideo(glb_prev_video, glb_video_type, true);
    } else if (glb_video_type == 'videobydownload') {
        list_videos('Downloads', account.get('user_id'), 0);
    } else if (glb_channel_id) {
        list_videos('Channel', glb_channel_id, 0);
    } else if (direction == 'right') {
        Ext.Viewport.animateActiveItem("Search", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("Search");
        glb_custom_views_arr_data.push([0]);
    } else if (direction == 'left') {
        Ext.Viewport.animateActiveItem("Settings", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("Settings");
        glb_custom_views_arr_data.push([0]);
    }
};

function mute(ref) {
    ref.close();
    window.onfocus = function() {};
}

function load_settings() {
    // try/catch added by sterling dev
    try {
        document.getElementById("version_ider").remove();
        document.getElementById("version_ider2").remove();
    } catch (err) {}
    var version_sz = '';
    if (glb_device_type == 'tablet') {
        version_sz = 'style="font-size:1.6em;"';
    }
    var new_check = "0";
    var newItem = {
        html: '<div id="version_ider" style="text-align:center;" ' + version_sz + '><br>MyVidster<br>Version: ' + glb_version + '<br/> contact@myvidster.com<br/><br/></div>'
    };
    Ext.getCmp('help_general').add(newItem);
    if (1 == 1) {
        var user_a = navigator.userAgent;
        if (glb_video_history) {
            var hist3 = "IDS;" + " https://www.myvidster.com/video/" + glb_video_history[0] + "  " + "https://www.myvidster.com/video/" + glb_video_history[1] + "  " + "https://www.myvidster.com/video/" + glb_video_history[2];
        } else {
            var hist3 = "IDS;0,0,0"
        }
        if (Ext.os.is('iOS')) {
            var devos = 1;
        } else {
            var devos = 0;
        }
        var ddata = 'Device Data (' + glb_device_type + " on: " + glb_version + ' | Network: ' + checkConnectionType() + '):(User ID: ' + account.get("user_id") + ')' + user_a + ":" + hist3;
        var feedback_lnk = 'mailto:marques@myvidster.com?cc=sebi@myvidster.com&subject=Feedback From: ' + account.get("user_id") + '&body=' + 'Device Data (' + glb_version + ' | Network: ' + checkConnectionType() + '):' + user_a + ":" + hist3 + '%0D%0A%0D%0A%0D%0A%0D%0A' + 'Message:%0D%0A';
        var url_open = 'https://www.myvidster.com/app/android_update';
    } else {
        var feedback_lnk = 'https://itunes.apple.com/us/app/myvidster/id611470289?mt=8';
        var url_open = 'https://itunes.apple.com/us/app/myvidster/id611470289?mt=8';
    }

    var feedback_btn_sz = '';
    if (glb_device_type == 'tablet') {
        feedback_btn_sz = "font-size:1.2em;margin-top:70px;";
    }

    if (typeof device != 'undefined') {
        update_app2();
        var feedback_btn = {
            layout: {
                type: 'hbox',
                pack: 'center',
            },
            items: [{
                xtype: 'button',
                id: 'version_ider2',
                text: 'Provide Feedback',
                width: '200',
                margin: '10',
                style: feedback_btn_sz,
                handler: function() {
                    feedback_func(ddata, devos);
                }
            }]
        };
    } else {
        var feedback_btn = {
            layout: {
                type: 'hbox',
                pack: 'center',
            },
            items: [{
                xtype: 'button',
                id: 'version_ider2',
                text: 'Provide Feedback',
                width: '200',
                margin: '10',
                style: feedback_btn_sz,
                handler: function() {
                    feedback_func(ddata, devos);
                }
            }]
        };
    }

    Ext.getCmp('help_general').add(feedback_btn);
}
var onMoreDown = function() {
    var more_btn_sz = '';
    if (glb_device_type == 'tablet') {
        more_btn_sz = 'font-size: 1.22em;';
    }
    var video_actionSheet = Ext.create('Ext.ActionSheet', {
        items: [{
            text: 'Settings',
            style: more_btn_sz,
            handler: function(btn, evt) {
                if (glb_current_list['type']) {
                    glb_back_data['type'] = glb_current_list['type'];
                    glb_back_data['id'] = glb_current_list['id'];
                }
                glb_last_panel = Ext.Viewport.getActiveItem().id;
                Ext.Viewport.setActiveItem("Settings");
                searchviewbackhack();
                custom_views_arr.push("Settings");
                glb_custom_views_arr_data.push([0]);
                push_alert_bug_fix();
                video_actionSheet.hide();
            }
        }, {
            text: 'Feedback and Help',
            //id: 'fbh_btn',
            style: more_btn_sz,
            handler: function(btn, evt) {
                if (glb_current_list['type']) {
                    glb_back_data['type'] = glb_current_list['type'];
                    glb_back_data['id'] = glb_current_list['id'];
                }
                glb_last_panel = Ext.Viewport.getActiveItem().id;
                Ext.Viewport.setActiveItem("Help");
                searchviewbackhack();
                custom_views_arr.push("Help");
                glb_custom_views_arr_data.push([0]);
                load_settings();
                push_alert_bug_fix();
                video_actionSheet.hide();
            }
        }, {
            text: 'Cancel',
            ui: 'decline',
            style: more_btn_sz,
            handler: function(btn, evt) {
                video_actionSheet.hide();
            }
        }]
    });
    Ext.Viewport.add(video_actionSheet);
    video_actionSheet.show();
}
var searchviewbackhack = function(){
        Ext.getCmp('home_refresh_var').deselectAll();
        Ext.getCmp('search_var').deselectAll();
}
var deletion_backs = function(){
    // try/catch needed added in case of server lag
    try{
    searchviewbackhack();
    custom_views_arr = [];
    custom_views_arr.push('Home');
    var array_data = [0];
    glb_custom_views_arr_data = [];
    glb_custom_views_arr_data.push(array_data);
    custom_views_arr.push('UserMenu');
    glb_custom_views_arr_data.push(array_data);
    }catch(err){}
}

function arraysEqual(a, b) {
    if (a === b) return true;
    if (a == null || b == null) return false;
    if (a.length != b.length) return false;
    for (var i = 0; i < a.length; ++i) {
      if (a[i] !== b[i]) return false;
    }
    return true;
  }

var onBackKeyDown = function() {
        setMask('Loading... ');
        // try/catch needed added in case of server lag or fast clicks
        try{
            var last_view3 = custom_views_arr[custom_views_arr.length - 1];
            var last_view_data3 = glb_custom_views_arr_data[glb_custom_views_arr_data.length - 1];
            var last_view4 = custom_views_arr[custom_views_arr.length - 2];
            var last_view_data4 = glb_custom_views_arr_data[glb_custom_views_arr_data.length - 2];
    
            if(last_view3 == last_view4 && arraysEqual(last_view_data3, last_view_data4) || last_view3 == 'Search' && last_view4 == 'Search' || last_view3 == 'UserMenu' && last_view4 == 'UserMenu' || last_view3 == 'MsgList' && last_view4 == 'MsgList' || last_view3 == 'Video' && last_view4 == 'Video' && last_view_data3[0] === last_view_data4[0] && last_view_data3[1] === last_view_data4[1] || last_view3 == 'Video2' && last_view4 == 'Video2' && last_view_data3[0] === last_view_data4[0] && last_view_data3[1] === last_view_data4[1]){
                custom_views_arr.pop();
                glb_custom_views_arr_data.pop();
                custom_views_arr.pop();
                glb_custom_views_arr_data.pop();
                custom_views_arr.push(last_view3);
                glb_custom_views_arr_data.push(last_view_data3);
                onBackKeyDown();
                return;
            }
        }catch(err){
            clearMask();
            custom_views_arr = [];
            custom_views_arr.push('Home');
            var array_data = [0];
            glb_custom_views_arr_data = [];
            glb_custom_views_arr_data.push(array_data);
            Ext.Viewport.setActiveItem('Home');
        }

            Ext.getCmp('home_refresh_var').deselectAll();
            Ext.getCmp('search_var').deselectAll();

        if(custom_views_arr.length > 1){
            custom_views_arr.pop();
            glb_custom_views_arr_data.pop();
            if(custom_views_arr[0] !== 'Home'){
                custom_views_arr = [];
                custom_views_arr.push('Home');
                glb_custom_views_arr_data = [];
                glb_custom_views_arr_data.push([0]);
            }
            
            var last_view = custom_views_arr[custom_views_arr.length - 1];
            var last_view_data = glb_custom_views_arr_data[glb_custom_views_arr_data.length - 1];
            
         if(last_view == 'MsgUser'){

            var uid5 = last_view_data[0];
            var uid_username = last_view_data[1];
            if (uid_username == "" || uid_username == null) {
                var old_title = Ext.getCmp('userListToolbarMessage').getActiveTab().title;
                uid_username = old_title;
            }
    
        glb_last_panel = Ext.Viewport.getActiveItem().id;
        glb_current_messaging = uid5;
        Ext.getCmp('userListToolbarMessage').setTitle("" + uid_username);
        list_detailed_msgs_obj.removeAll();
        var dt = new Date();
        var offset_time = dt.getTimezoneOffset();
        
        list_detailed_msgs_obj.getProxy().setExtraParams({
            type: 'detailed_messages',
            user_to: uid5,
            user_id: account.get('user_id'),
            pkey: account.get('pw'),
            token: glb_msg_token,
            start: 0,
            device_type: glb_device_type,
            id: Math.random(),
            offset: offset_time
        });
        list_detailed_msgs_obj.load({ 
            callback: function(){
                Ext.Viewport.setActiveItem(last_view);
                clearMask();
            }
        });

        var my_id = account.get('user_id');
        var user_id = uid5;
        Ext.getCmp('to_c').setValue(user_id);
        Ext.getCmp('from_c').setValue(my_id);
        document.getElementById('msg_c').value = ""; 
        setTimeout(function() {
            var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();
            scroller.scrollToEnd(false);
        }, 99);

        setTimeout(function() {
            try{
                var send_to_id = "presence-" + String(uid5);
                var presenceChannel = pusher.unsubscribe(send_to_id);
            }
            catch(err){myvAlert('Please restart the app!');}
        }, 2900);
            return;
         }

         if(last_view == 'Search'){

            if(last_view_data.length > 1){
                if(search_back_preserve_hack == 1){
                    glb_seb_back = 1;
                    video_search(last_view_data[0], last_view_data[1], last_view_data[2], last_view_data[3], last_view_data[4]);
                }
                Ext.Viewport.setActiveItem(last_view);
                clearMask();
            }else{
                Ext.Viewport.setActiveItem(last_view);
                clearMask();
            }
            search_back_preserve_hack = 1;

            return;
         }

         if(last_view == 'Video' || last_view == 'Video2'){
            glb_seb_back = 1;
            setMask('Loading...');

            if(document.getElementById("player_pic")){
                document.getElementById("player_pic").src = 'resources/themes/images/default/pictos/play2.png';
                application.set("play_playlist", 0);
                application.setDirty();
                app_store.sync();
                application = app_store.first();
                glb_play_playlist = 0;
            }
            getVideo(last_view_data[0],last_view_data[1],last_view_data[2],last_view_data[3],last_view_data[4],last_view_data[5]);
            return;
         }
         if(last_view == 'UserList'){

            glb_seb_back = 1;
            if(last_view_data[0] == 'Channel'){
                list_videos(last_view_data[0], last_view_data[1], last_view_data[2]);
            }else{
                list_videos(last_view_data[1], last_view_data[0], last_view_data[2]);
            }
            
            clearMask();
            return;
         }
         if(last_view == 'UserLogin'){
            if(account.get('user_id')){
                onBackKeyDown();
            }else{
                Ext.Viewport.setActiveItem(last_view);
                clearMask();  
            }
            return;
         } 
         if(last_view){
            Ext.Viewport.setActiveItem(last_view);
            clearMask();
            return;
         } 
         return;

        }else{
            clearMask();
        }
};

var onBackKeyDown_col = function() {
    push_alert_bug_fix();
    onBackKeyDown();
    return;
}

var onSearchKeyDown = function() {
    push_alert_bug_fix();
    clearCountdown();
    // sets mask if not already on search view
    if (Ext.Viewport.getActiveItem().id != "Search") {
      //setMask('Loading...');
    }

    glb_bottom_panel = "Search";
    if (glb_scope != 'profiles' && glb_scope != 'web' && glb_scope != 'subscription_list') {
        // followers and following
        Ext.getCmp('search_opt2').show();
    } else {
        Ext.getCmp('search_opt2').hide();
    }
    if (glb_current_list['type']) {
        glb_back_data['type'] = glb_current_list['type'];
        glb_back_data['id'] = glb_current_list['id'];
    }
    clear_search();
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    Ext.Viewport.animateActiveItem("Search", {
        type: 'fade',
    });
};

function clear_search(override) {
    if (override) {
        Ext.getCmp('search_bar').setValue('');
        search_obj.removeAll();
    } else if (glb_scope != 'myVidster' && glb_scope != 'web' && glb_scope != 'user') {
        glb_scope = 'myVidster';
        glb_scope_id = 0;
        create_search_opt();
        Ext.getCmp('search_bar').setValue('');

        search_obj.removeAll();

    } else {}
}

var onMenuKeyDown = function() {
    inDiscoverView = false;
    push_alert_bug_fix();
    clearCountdown();
    glb_bottom_panel = "UserMenu";
    if (glb_current_list['type']) {
        glb_back_data['type'] = glb_current_list['type'];
        glb_back_data['id'] = glb_current_list['id'];
    }
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    if (account.get("user_id")) {
        // sets mask if not already on user menu view
        if (Ext.Viewport.getActiveItem().id != "UserMenu") {
          //setMask('Loading...');
        }
        Ext.Viewport.animateActiveItem("UserMenu", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("UserMenu");
        glb_custom_views_arr_data.push([0]);
        //reloadUserMenu();
        if (isIphoneX && Ext.Viewport.determineOrientation() === 'landscape') {
          Ext.Viewport.setWidth('95%');
        }
    } else {
        // sets mask if not already on user login
        if (Ext.Viewport.getActiveItem().id != "UserLogin") {
          //setMask('Loading...');
        }
        Ext.Viewport.animateActiveItem("UserLogin", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("UserLogin"); 
        glb_custom_views_arr_data.push([0]);
    }
}

var exitmyVidster = function(buttonIndex) {
    if (buttonIndex == 1)
        navigator.app.exitApp();
};
var alertDismissed = function() {};
var myvAlert = function(response) {
    if (typeof device != 'undefined')
        navigator.notification.alert(response, alertDismissed, "MyVidster");
    else {
        Ext.Msg.defaultAllowedConfig.showAnimation = false;
        Ext.Msg.alert(null, response);
    }
};

var load_ad_wall = function(url) {
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    Ext.Viewport.animateActiveItem("General", {
        type: 'fade',
    });
    myGeneral.removeAll();
    var newItem = {
        html: '<div id="wrapper"><iframe style="position:fixed;top:0;left:0;float:left;z-index:1;" hspace=0 vspace=0 border=0 frameborder=0 marginheight=0 marginwidth=0 width="100%" height="2000px" allowtransparency=true src="' + url + '"></div>'
    };

    myGeneral.add(newItem);
};

var load_more = function() {
    var version_sz = '';
    if (glb_device_type == 'tablet') {
        version_sz = 'style="font-size:1.6em;"';
    }
    var new_check = "0";
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    myGeneral.removeAll();
    var newItem = {
        html: '<div align="center" ' + version_sz + '><br>MyVidster<br>Collect the videos you love<br>Version: ' + glb_version + '</div>'
    };
    myGeneral.add(newItem);
    if (true) {
        var user_a = navigator.userAgent;
        if (glb_video_history) {
            var hist3 = "IDS;" + " https://www.myvidster.com/video/" + glb_video_history[0] + "  " + "https://www.myvidster.com/video/" + glb_video_history[1] + "  " + "https://www.myvidster.com/video/" + glb_video_history[2];
        } else {
            var hist3 = "IDS;0,0,0"
        }
        if (Ext.os.is('iOS')) {
            var devos = 1;
        } else {
            var devos = 0;
        }

        var ddata = 'Device Data (' + glb_device_type + " on: " + glb_version + ' | Network: ' + checkConnectionType() + '):(User ID: ' + account.get("user_id") + ')' + user_a + ":" + hist3;
        var feedback_lnk = 'mailto:marques@myvidster.com?cc=sebi@myvidster.com&subject=Feedback From: ' + account.get("user_id") + '&body=' + 'Device Data (' + glb_version + ' | Network: ' + checkConnectionType() + '):' + user_a + ":" + hist3 + '%0D%0A%0D%0A%0D%0A%0D%0A' + 'Message:%0D%0A';
        var url_open = 'https://www.myvidster.com/app/android_update';
    } 

    var feedback_btn_sz = '';
    if (glb_device_type == 'tablet') {
        feedback_btn_sz = "font-size:1.2em;margin-top:70px;";
    }

    if (typeof device != 'undefined') {
        update_app2();
        var feedback_btn = {
            layout: {
                type: 'hbox',
                pack: 'center'
            },
            items: [{
                xtype: 'button',
                text: 'Provide Feedback',
                width: '200',
                margin: '10',
                style: feedback_btn_sz,
                handler: function() {

                    feedback_func(ddata, devos);

                }
            }]
        };
    } else {
        var feedback_btn = {
            layout: {
                type: 'hbox',
                pack: 'center'
            },
            items: [{
                xtype: 'button',
                text: 'Provide Feedback',
                width: '200',
                margin: '10',
                style: feedback_btn_sz,
                handler: function() {
                    feedback_func(ddata, devos);
                }
            }]
        };
    }
    myGeneral.add(feedback_btn);
};

var endcall = function(){
    document.getElementById("videochat_container_table").style.display = "none";
    document.getElementById("videochat_container_table").style.zIndex = "-100";
    document.getElementById("end_call").innerText = 'Ringing... End Call';
    document.getElementById("mute_btn").innerText = 'Mute';
    // try/catch needed due to opentok having know bugs
    try{video_chat_session.disconnect();}catch(err){}
    try{video_chat_session.unpublish(video_chat_publisher);}catch(err){}
    if(typeof device != 'undefined'){window.plugins.insomnia.allowSleepAgain();}
    try{
        var pub2 = document.createElement('div');
        pub2.setAttribute("id", "puber2");
        var sub2 = document.createElement('div');
        sub2.setAttribute("id", "suber2");
        document.getElementById("puber").appendChild(pub2);
        document.getElementById("suber").appendChild(sub2);
    }catch(err){alert('Error, restart the app!');}
};

var mutecall = function(){
    // try/catch needed due to known bugs of opentok plugin
    if(document.getElementById("mute_btn").innerText == 'Mute'){
        document.getElementById("mute_btn").innerText = 'Muted';
        try{video_chat_publisher.publishAudio(false);}catch(err){}
    }else{
        document.getElementById("mute_btn").innerText = 'Mute';
        try{video_chat_publisher.publishAudio(true);}catch(err){}
    }
};
function handleVideoChatError(error) {
    if (error){alert('Video Chat Error');}
};
function check_vid_session(param, param2) {
    if (document.getElementById("videochat_container_table").style.display != "none") {
        setTimeout(check_vid_session, 2000, param, param2);
    }else{
        // try/catch needed for known opentok plugin bugs
        try{param2.disconnect();}catch(err){} 
        try{param2.unpublish(param);}catch(err){}
    }
}
function initializeSession(to, apiKey, sessionId, vtoken) {
    if(Ext.os.is('iOS')){alert('Coming soon');return;}
    if(typeof OT != 'undefined' && typeof device != 'undefined'){
    }else{
        alert('Please update your app to use this feature!');
        return;
    }
    var session = OT.initSession(apiKey, sessionId);
    video_chat_session = session;
    var vid_width = (window.innerWidth - 140.0);
    var vid_height = (window.innerHeight - 90.0);
    vid_height = parseInt(vid_height);
    vid_width = parseInt(vid_width);
    var publisher = OT.initPublisher('puber2', {insertMode: 'replace', width:120, height:120}, handleVideoChatError);
    video_chat_publisher = publisher;

    if(typeof device != 'undefined'){window.plugins.insomnia.keepAwake();}
    // try/catch needed for known opentok plugin bugs
    try{
        setTimeout(function() {check_vid_session(publisher, session);}, 2500);
    }catch(err){}
    session.on({
    streamCreated: function(event) {
        document.getElementById("end_call").innerText = 'End Call';
        session.subscribe(event.stream, 'suber2', {insertMode: 'replace', width:vid_width, height:vid_height}, handleVideoChatError);
    },
    streamDestroyed: function(event) {
        if(event.reason == 'clientDisconnected'){
            endcall();
            alert("Call Ended");
    }
    },
    sessionConnected: function(event) {
        session.publish(publisher);
    }
});

session.connect(vtoken, function(error) {
 if (error) {
   //console.log('There was an error connecting to the session');
 }
});

document.getElementById("videochat_container_table").style.zIndex = "100";
document.getElementById("videochat_container_table").style.display = "";

if(to != '' && to != 'none'){
Ext.util.JSONP.request({
    url: 'https://chat.myvidster.com/tixtok/web/api.php',
    callbackKey: 'callback',
    params: {
        type: 'send_videochat',
        user_id: account.get('user_id'),
        username: String(account.get('disp_name')),
        to_userid: to,
        video_token: vtoken,
        video_session: sessionId,
        video_apikey: apiKey,
        uniquify: Math.random(),
        token: glb_msg_token,
        pushalert: 1
    },
    callback: function(success, result) {
        if(result){
            if (result.items[0].response == "Success") {clearMask();}
            else {myvAlert(result.items[0].response);clearMask();}
        }
}
});

setTimeout(function() {
    Ext.util.JSONP.request({
        url: 'https://chat.myvidster.com/tixtok/web/api.php',
        callbackKey: 'callback',
        params: {
            type: 'send_videochat',
            user_id: account.get('user_id'),
            username: String(account.get('disp_name')),
            to_userid: to,
            video_token: vtoken,
            video_session: sessionId,
            video_apikey: apiKey,
            uniquify: Math.random(),
            token: glb_msg_token,
            pushalert: 0
        },
        callback: function(success, result) {
            if(result){
                if (result.items[0].response == "Success") {clearMask();}
                else {myvAlert(result.items[0].response);clearMask();}
            }
    }
    });
}, 13000);
}
};

var load_webpage = function(url, external, msg) {
    if (msg) {
        myvAlert(msg);
    }

    if (typeof device != 'undefined') {
        if (external) {
            window.open(url, "_system");
        } else {
            //var ref = window.open(url, '_blank', 'location=yes');
           var ref = cordova.InAppBrowser.open(url, '_blank', 'location=yes');

          /*
          var code_var = "var bnr_ad = document.createElement('div'); bnr_ad.setAttribute('id', 'bnr_ad'); document.getElementsByTagName('body')[0].appendChild(bnr_ad); document.getElementById('bnr_ad').innerText = 'MyVidster Ads Here'; document.getElementById('bnr_ad').style.zIndex = '10000'; document.getElementById('bnr_ad').style.width = '100%'; document.getElementById('bnr_ad').style.height = '55px'; document.getElementById('bnr_ad').style.display = 'block'; document.getElementById('bnr_ad').style.backgroundColor = 'orange'; document.getElementById('bnr_ad').style.color = 'white'; document.getElementById('bnr_ad').style.textAlign = 'center'; document.getElementById('bnr_ad').style.position = 'absolute'; document.getElementById('bnr_ad').style.bottom = '0px';";

            ref.addEventListener('loaderror', function() {
              ref.executeScript({code: code_var));
            });

            ref.addEventListener('loadstop', function() {
              ref.executeScript({code: code_var));
            });
            */


           if (masterCounter || (glb_last_panel != 'Video' && glb_last_panel != 'Video2') ) {
                countdownCancelled = true;
                clearInterval(masterCounter);
                counterNumber = 10;
                clearInterval(masterVideoCounter);
                document.getElementById('countdown').style.display = 'none';
                document.getElementById('countdownText').innerText = 'Next Video Playing in 10...';
                setTimeout(function() {
                  countdownCancelled = false;
                },1000);
           
              ref.addEventListener('exit', function() {
                if (glb_play_playlist) {
                  counterNumber = 10;
                  setTimeout(function() {
                    startBasicCountdown();
                  },2000);
                }
		ref.close();
              });
            }
        }
    } else {
        window.open(url);
    }
};

var pro_upgrade = function(msg) {
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    myGeneral.removeAll();
    var html;
    if (Ext.os.is('iOS')) {
        html = '<div align="center" style="margin:10px;">';
        html += '<span class="titlebig">Upgrade to MyVidster PRO!</span>';
        html += '</div>';
        html += '<div align="center">';
        html += '<img onclick="pro_func()" src="https://cdn2.myvidster.com/images/myv_pro.png">';
        html += '</div>';
        html += '<div align="center" style="margin:10px;">';
        html += msg + '<br><br>';
        html += '<ul><li>Up to 512 GBs of private cloud storage</li>';
        html += '<li>No restrictions on video downloads</li>';
        html += '<li>Ad free mobile app and MyVidster.com</li></ul>';
        html += '<br>Rates starting at $6.99 a month<br><br>';
        html += '<a href="#" onclick="pro_func()">Upgrade to PRO!<a>';
        html += '</div>';
    } else {
        html = '<div align="center" style="margin:10px;">';
        html += '<span class="titlebig">Upgrade to MyVidster PRO!</span>';
        html += '</div>';
        html += '<div align="center">';
        html += '<img onclick="pro_upgrade_cc(0)" src="https://cdn2.myvidster.com/images/myv_pro.png">';
        html += '</div>';
        html += '<div align="center" style="margin:10px;">';
        html += msg + '<br><br>';
        html += '<ul><li>Up to 512 GBs of private cloud storage</li>';
        html += '<li>No restrictions on video downloads</li>';
        html += '<li>Ad free mobile app and MyVidster.com</li></ul>';
        html += '<br>Rates starting at $6.99 a month<br><br>';
        html += '<a href="#" onclick="pro_upgrade_cc(0)">Upgrade to PRO!<a>';
        html += '</div>';
    }
    var newItem = {
        html: html
    };
    myGeneral.add(newItem);
    Ext.Viewport.animateActiveItem("General", {
        type: 'fade',
    });
    searchviewbackhack();
    custom_views_arr.push("General");
    glb_custom_views_arr_data.push([0]);
};

var cancel_pro = function(pro_val, tier) {
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'cancel_pro',
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            uniquify: Math.random(),
            sandbox: 0,
            id: pro_val
        },
        callback: function(success, result) {
            if (result.items) {

                var res = result.items[0].response;
                myvAlert(res);

                glb_last_panel = Ext.Viewport.getActiveItem().id;

                setTimeout(function() {
                    pro_func();
                }, 1500);
                clearMask();
            }
        }
    });
};

var pro_upgrade_cc = function(pro_val, tier) {
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'get_pro_options',
            id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            uniquify: Math.random(),
            tiers: tier
        },
        callback: function(success, result) {
            if (result.items) {
                var upgrade_opt = new Array();
                var len = result.items.length;
                for (var i = 0; i < len; i++) {
                    upgrade_opt[i] = {
                        text: result.items[i].myv_item_desc,
                        value: result.items[i].myv_item
                    };
                }
                Ext.getCmp('myv_item').setOptions(upgrade_opt);
                Ext.getCmp('myv_item').setValue(result.items[pro_val].myv_item);
                Ext.getCmp('tiers').setValue(tier);
                Ext.getCmp('cust_id').setValue(account.get('user_id'));
                Ext.getCmp('auto_bill_1').setChecked(true);
                Ext.getCmp('auto_bill_1').setHidden(false);
                Ext.getCmp('auto_bill_2').setHidden(false);
                Ext.getCmp('auto_bill_label').setHidden(false);
                Ext.getCmp('payment_btn').setText('Upgrade');
                document.getElementById("cycle_text").style.visibility = "visible";
                glb_last_panel = Ext.Viewport.getActiveItem().id;
                clearMask();
                // try/catch added to prevent payment page from loading even when DOM elements don't load 
                try {
                    document.getElementById("card_code_alert").getElementsByTagName('span')[0].innerHTML = 'Card Code<br/><a onclick="load_webpage(\'' + 'https://account.authorize.net/help/Miscellaneous/Pop-up_Terms/Virtual_Terminal/Card_Code.htm' + '\',false);" style="font-size:.78em;text-decoration:none;">What\'s this?</a>';
                } catch (err) {}

                Ext.Viewport.animateActiveItem("Payment", {
                    type: 'fade',
                });
                searchviewbackhack();
                custom_views_arr.push("Payment");
                glb_custom_views_arr_data.push([0]);
            }
        }
    });
};

var pro_upgrade_cc_auto = function(pro_val, tier, auto_b) {
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'get_pro_options',
            id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            uniquify: Math.random(),
            tiers: tier
        },
        callback: function(success, result) {
            if (result.items) {
                var upgrade_opt = new Array();
                var len = result.items.length;
                for (var i = 0; i < len; i++) {
                    upgrade_opt[i] = {
                        text: result.items[i].myv_item_desc,
                        value: result.items[i].myv_item
                    };
                }
                Ext.getCmp('myv_item').setOptions(upgrade_opt);
                Ext.getCmp('myv_item').setValue(result.items[pro_val].myv_item);
                Ext.getCmp('tiers').setValue(tier);
                Ext.getCmp('cust_id').setValue(account.get('user_id'));
                Ext.getCmp('auto_bill_1').setChecked(true);
                Ext.getCmp('auto_bill_1').setHidden(true);
                Ext.getCmp('auto_bill_2').setHidden(true);
                Ext.getCmp('auto_bill_label').setHidden(true);
                Ext.getCmp('payment_btn').setText('Update');
                document.getElementById("cycle_text").style.visibility = "hidden";
                document.getElementById("auto_bill_text").innerHTML = auto_b;
                glb_last_panel = Ext.Viewport.getActiveItem().id;
                clearMask();
                try {
                    document.getElementById("card_code_alert").getElementsByTagName('span')[0].innerHTML = 'Card Code<br/><a onclick="load_webpage(\'' + 'https://account.authorize.net/help/Miscellaneous/Pop-up_Terms/Virtual_Terminal/Card_Code.htm' + '\',false);" style="font-size:.78em;text-decoration:none;">What\'s this?</a>';
                } catch (err) {}

                Ext.Viewport.animateActiveItem("Payment", {
                    type: 'fade',
                });
                searchviewbackhack();
                custom_views_arr.push("Payment");
                var array_data = [0];
                glb_custom_views_arr_data.push(array_data);
            }
        }
    });
};

function process_cc(card_num, exp_date, cust_id, first_name, last_name, myv_item, card_code, tiers) {
    setMask('Processing...');
    var auto_bill = '';
    if (Ext.ComponentQuery.query('radiofield[name=auto_bill]')[0]['_checked'] == true) {
        auto_bill = 0;
    } else {
        auto_bill = 1;
    }
    var type_c = 'process_cc';
    var auto_b = document.getElementById("auto_bill_text").innerHTML;
    if (auto_b && auto_b != 0) {
        type_c = 'update_sub';
        auto_bill = auto_b;
    }
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: type_c,
            card_num: card_num,
            exp_date: exp_date,
            cust_id: cust_id,
            first_name: first_name,
            last_name: last_name,
            myv_item: myv_item,
            card_code: card_code,
            sandbox: 0,
            token: glb_msg_token,
            auto_bill: auto_bill,
            tiers: Ext.getCmp('tiers').getValue(),
            token: glb_msg_token,
            uniquify: Math.random()
        },
        callback: function(success, result) {
            if (result.items) {
                var response = result.items[0].response;
                if (response == 'Success') {
                    if (auto_b) {
                        myvAlert("Your subscription has been successfully modified!");
                    } else {
                        myvAlert("You have been successfully upgraded to PRO!");
                    }
                    Ext.getCmp('PaymentForm').setValues({
                        card_num: '',
                        card_code: '',
                        exp_date: '',
                        first_name: '',
                        last_name: '',
                        tiers: ''
                    });
                    if (glb_last_panel == 'ProMember') {
                        setTimeout(function() {
                            pro_func();
                        }, 1500);

                    } else {
                        getVideo(glb_current_video, glb_video_type, true);
                    }
                    Ext.getCmp('PaymentForm').setValues({
                        card_num: '',
                        card_code: '',
                        exp_date: '',
                        first_name: '',
                        last_name: '',
                        tiers: ''
                    });
                } else {
                    myvAlert(response);
                }
                clearMask();
            }
        }
    });
}

var collect_video = function(video_id, video_title, access, action) {
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    Ext.getCmp('collect_video_tags').setValue('');
    if (action == 'record') {
        Ext.getCmp('collect_btn').setText('record');
    } else {
        Ext.getCmp('collect_btn').setText('collect');
    }
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'channelbyid',
            id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            video_id: video_id
        },
        callback: function(success, result) {
            if (result.items) {
                var channel_opt = new Array();
                var len = result.items.length;
                for (var i = 0; i < len; i++) {
                    channel_opt[i] = {
                        text: result.items[i].name,
                        value: result.items[i].channel_id
                    };
                }
                Ext.getCmp('collect_channel').setOptions(channel_opt);
                Ext.getCmp('collect_channel').setValue(result.items[0].channel_id);
                Ext.getCmp('collect_video_title').setValue(video_title);
                Ext.getCmp('collect_video_id').setValue(video_id);
                Ext.getCmp('videoCollectForm').setValues({
                    access: parseInt(glb_defaultAccess)
                });
                if (parseInt(access) == 2) {
                    // try/catch prevent app crashing from dom issues
                    if (result.items[0].hide == 1) {
                        var myButton33 = Ext.getCmp('private_fix');
                        try {
                            myButton33.hide();
                        } catch (err) {}
                    } else {
                        var myButton33 = Ext.getCmp('private_fix');
                        try {
                            myButton33.show();
                        } catch (err) {}
                    }
                } else {
                    var myButton33 = Ext.getCmp('private_fix');
                    try {
                        myButton33.show();
                    } catch (err) {}
                }

                Ext.Viewport.animateActiveItem("Collect", {
                    type: 'fade',
                });
                searchviewbackhack();
                custom_views_arr.push("Collect");
                glb_custom_views_arr_data.push([video_id, video_title, access, action]);
            }
            clearMask();
        }
    });
};

function isInt(n) {
    return n % 1 === 0;
}

var bookmark_video = function(video_id, video_title, channel, video_tags, access) {
    if (isInt(video_id))
        var url = 'https://www.myvidster.com/video/' + video_id;
    else
        var url = video_id;

    setMask('Bookmarking...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/user/api.php',
        callbackKey: 'callback',
        params: {
            email: account.get('email'),
            password: account.get('pw'),
            url: url,
            channel_id: channel,
            title: video_title,
            tags: video_tags,
            format: 'json',
            action: 'insert',
            method: 'server',
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            access: access
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var status = result.items[0].status;
                if (status == 'Success') {
                    myvAlert(video_title + ' has been bookmarked to your collection');
                    gobacktovideo();
                } else {
                    myvAlert(status);
                    gobacktovideo();
                }
            }
        }
    });
}

var queue_video = function(video_id, master_id, video_title, status) {
    if (status == 2)
        var msg = "Adding to queue...";
    else
        var msg = "Setting to watched...";
    setMask(msg);
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'queue_video',
            id: video_id,
            status: status,
            master_id: master_id,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            uniquify: Math.random()
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var response = result.items[0].response;
                if (response == 'Success') {
                    if (status == 2) {
                        var x = ' has been added to your queue';
                        Ext.get('myv_video_title').setHtml('<span style="weight:bold;color:green;">[In Queue]</span> ' + video_title);
                    } else {
                        var x = ' has been set to watched';
                        Ext.get('myv_video_title').setHtml('<span style="weight:bold;color:red;">[Watched]</span> ' + video_title);
                    }
                    myvAlert(video_title + x);
                } else {
                    myvAlert(response);
                }
            }
        }
    });
}

var add_video = function() {
    var embed = '<embed src="https://www.myvidster.com/player.swf?file=https://api.myvidster.com/user/videos/' + glb_file_name + '">';
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/user/api.php',
        callbackKey: 'callback',
        params: {
            email: account.get('email'),
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            password: account.get('pw'),
            url: 'https://api.myvidster.com/profile/' + account.get('disp_name'),
            channel_id: glb_upload_channel,
            title: glb_upload_video_title,
            tags: glb_upload_video_tags,
            format: 'json',
            action: 'insert',
            method: 'server',
            access: glb_upload_access,
            embed: embed,
            backup4416: 1
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var status = result.items[0].status;
                if (status == 'Success') {
                    var video_id = result.items[0].video_id;
                    myGeneral.removeAll();
                    var newItem = {
                        html: '<div align="center"><br><br>' + glb_upload_video_title + ' has been uploaded to your collection<br>Redirecting in 3 seconds.'
                    };
                    myGeneral.add(newItem);
                    Ext.Viewport.setActiveItem("General");
                    var collect_timeout = window.setTimeout("getVideo(" + video_id + ",'videobyid',false);", 3000);
                    clearMask();
                    //release();
                } else {
                    myGeneral.removeAll();
                    var newItem = {
                        html: '<div align="center"><br><br>' + status + '<br>Redirecting in 5 seconds.'
                    };
                    myGeneral.add(newItem);
                    Ext.Viewport.setActiveItem("General");
                    var collect_timeout = window.setTimeout("gobacktovideo()", 5000);
                    clearMask();
                    //release();
                }
            }
        }
    });
}

var getVideo_wait = function() {
    getVideo(glb_current_video, glb_video_type, true);
}

var card_handler = function(thisContainer, newCard, oldCard) {
	//video player fullscreen support
	if (newCard.getId() == 'Video' || newCard.getId() == 'Video2') {
		if(v_orientation_chk == 1) {
			if (typeof device != 'undefined') {
				screen.unlockOrientation();
            }
            v_orientation_chk = 3;
		}
	}
	else {
		 if(glb_myPlayer) {
			glb_myPlayer.dispose();
			glb_myPlayer = false;
		}
		if(v_orientation_chk == 3) {
			if(Ext.Viewport.determineOrientation() == 'portrait') { //ios bug do not lock the screen while its in landscape
				if (typeof device != 'undefined') { 
					screen.lockOrientation('portrait');
				}
				v_orientation_chk = 1;
			}
		}
	}
	if (!account) return true;
    if (
        (oldCard.getId() == 'Video' || oldCard.getId() == 'Video2') && (newCard.getId() != 'Video' && newCard.getId() != 'Video2')
    ) {
        account.set("current_video", '');
        account.set("current_video_type", '');
        account.setDirty();
        store.sync();
    }

    if (newCard.getId() != 'LockScreen' && newCard.getId() != 'ChromeCast') {
        if (oldCard.getId() == 'Video2') {
            Ext.getCmp('video_holder2').removeAll();
        }
        if (oldCard.getId() == 'Video') {
            Ext.getCmp('video_holder').removeAll();
        }
    }
}

var reset_video_page = function(isRelease) {
    if (isRelease && !glb_downloading)
        release();
}

var timeout_req = function() {
    if (Ext.util.JSONP) Ext.util.JSONP.callback({
        'error': 'timeout'
    });
}

var gobacktovideo = function() {
    // try/catch needed added in case of server lag and fast back btn clicks
    try{
        custom_views_arr.pop();
        glb_custom_views_arr_data.pop();
        custom_views_arr.pop();
        glb_custom_views_arr_data.pop();
    }catch(err){}
    getVideo(glb_current_video, glb_video_type);
}
var postComment_setup = function(title, master_id, ip) {
    //set the back button
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    Ext.getCmp('videoCommentForm').setValues({
        comment_name: account.get("disp_name"),
        comment_email: account.get("email"),
        comment_master_id: master_id,
        comment_title: title,
        comment_post: '',
        comment_ip: ip
    });

    Ext.Viewport.animateActiveItem("Comment", {
        type: 'fade',
    });
    searchviewbackhack();
    custom_views_arr.push("Comment");
    glb_custom_views_arr_data.push([title, master_id, ip]);

}

var postComment = function(comment_name, comment_email, comment_post, comment_ip, comment_master_id, video_title) {
    setMask('Posting comment...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'postcomment',
            author_name: comment_name,
            author_email: comment_email, //account.get("email"),
            message: comment_post,
            ip_address: comment_ip,
            master_id: comment_master_id,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pkey: account.get('pw')
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var response = result.items[0].response;
                if (response == 'Success') {
                    myGeneral.removeAll();
                    var newItem = {
                        html: '<div align="center" style="max-width:320px;text-align:center;margin:0px auto;word-break:break-all;"><br><br>Your comment has been posted to ' + video_title + '<br>Redirecting in 3 seconds.</div>'
                    };
                    myGeneral.add(newItem);
                    Ext.Viewport.setActiveItem("General");
                    var collect_timeout = window.setTimeout("gobacktovideo()", 3000);
                } else {
                    myGeneral.removeAll();
                    var newItem = {
                        html: '<div align="center" style="max-width:320px;text-align:center;margin:0px auto;word-break:break-all;"><br><br>' + response + '<br>Redirecting in 5 seconds.</div>'
                    };
                    myGeneral.add(newItem);
                    Ext.Viewport.setActiveItem("General");
                    var collect_timeout = window.setTimeout("gobacktovideo()", 5000);
                }
            }
        }
    });
}
function HtmlEncode(s) {
    s = s.replace(/(\r\n|\n|\r)/gm, " ");
    var el = document.createElement("div");
    el.innerText = el.textContent = s;
    s = el.innerHTML;
    return s;
}
function playlist_handler() {
	if (glb_play_playlist == 0) {
            document.getElementById("player_pic").src = 'resources/themes/images/default/pictos/stop1.png';
            application.set("play_playlist", 1);
            application.setDirty();
            app_store.sync();
            application = app_store.first();
            glb_play_playlist = 1;
        } else {
            document.getElementById("player_pic").src = 'resources/themes/images/default/pictos/play2.png';
            application.set("play_playlist", 0);
            application.setDirty();
            app_store.sync();
            application = app_store.first();
            glb_play_playlist = 0;
        }
}
function show_comments() {
    // make persistent
    var first_check = document.getElementsByClassName("details_comment");
    var cmt_cnt = document.getElementById("cmt_cnt").innerText; // comment count 
    var cmt_button = document.getElementById("show_comments");
    var cmt = document.getElementById("comment_id_holder");

    if (first_check[0].style.display == 'none') {
        document.getElementById("comments_show_div").style.display = "none";
        for (var i = 0; i < first_check.length; i++) {
            first_check[i].style.display = "block";
        }
        if (cmt_cnt > 10) {
            document.getElementById("load_more_cmt").style.display = "block";
        }
        glb_comment_hidden = 0;
        Ext.getCmp('settingsForm').setValues({
            show_hide_comments: glb_comment_hidden
        });
    }
    application.set("show_hide_comments", 0); 
    application.setDirty();
    app_store.sync();
    application = app_store.first();
}

function removeElementsByClass(className) {
    var elements = document.getElementsByClassName(className);
    while (elements.length > 0) {
        elements[0].parentNode.removeChild(elements[0]);
    }
}

function load_comments_init(master_id, video_id, type, cur_video_holder) {
    removeElementsByClass('comment_title');
    removeElementsByClass('details_comment');
    removeElementsByClass('comment_load_more');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'list_comments',
            id: master_id,
            video_id: video_id,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pkey: account.get('pw'),
            start: 0,
            last_id: 0,
            uniquify: Math.random()
        },
        callback: function(success, result) {
            if (result.total > 0) {
                var items = result.items;
                var items_len = result.items.length;
                var items_len2 = result.total;
                var video_title_encoded = HtmlEncode(result.items[0]['video_title']);
                var ip = result.items[0]['ip'];
                // limit to first 10
                var html_cmt = '';
                var dis_check = '';
                if (glb_comment_hidden == 1) {
                    dis_check = 'none';
                } else {
                    dis_check = 'block';
                }
                html_cmt = '<div class="comment_title" width="100%"><div style="float:left;"><span id="cmt_cnt" style="display:none;">' + items_len2 + '</span><span id="comment_id_holder">' + items_len2 + ' Comments</span></div> <div style="float:right;margin-right:6px;color:#2F4F4F;"><span onclick="comment_handler(\'' + video_title_encoded + '\',\'' + master_id + '\',\'' + ip + '\');" id="show_comments2" style="font-size:1em;">Add Comment</span></div> <div style="clear:both;"></div> </div>';
                var last_cmt_id = '';
                var x_smb = '';
                var owner = '0';
                var cmt_time = '';

                var cmt_remove_clr = '';
                for (x = 0; x < items_len; x++) {
                    cmt_remove_clr = '';
                    x_smb = '&#9873;';
                    owner = '0';
                    cmt_time = result.items[x]['createdAt']; 
                    cmt_time = cmt_time.split(' ');
                    cmt_time = cmt_time[0];

                    if(result.items[x]['owner_id'] === account.get('user_id')){cmt_remove_clr = 'red';x_smb = '&#10008;';owner = '1';}
                    html_cmt += '<span id="cmt_'+result.items[x]['comment_id']+'"> <div class="details_comment" id="details_comment" style="border-left:0px;margin-left:0px;display:' + dis_check + ';"><table width="100%" cellpadding="5" cellspacing="0" border="0"><tr><td width="40px" style="padding-right:8px;" valign="top"><div align="left" id="test" name="test" class="profile_thumb" style="height:50px;margin-left:0px;"><a href="javascript:void(0);" onclick="profile_handler(\'' + result.items[x]['c_user_id'] + '\');" ><img src="' + result.items[x]['c_profile_photo'] + '" width="40px" height="40px" style="border-radius: 50%;" /></a></div></td><td valign="top" style="padding:4px;"><div align="left" id="test" name="test"> <div style="float:left;"> <b>' + result.items[x]['name'] + '</b>  </div>  <div style="float:right;"> <b class="rpt_cmt" style="color:'+cmt_remove_clr+';" onclick=\'remove_comment("'+master_id+'","'+result.items[x]['comment_id']+'",'+owner+');\'>' + x_smb + '</b></div> <div style="clear:both;"></div> </div><div align="left" id="test" name="test" style="font-size:.84em;">' + result.items[x]['message'] + '<br/> <div class="cmt_replay" onclick="reply_comment(\''+result.items[x]['name']+'\');">Reply</div> <div class="cmt_time">'+cmt_time+'</div> <div style="clear:both;"></div> </div></td></tr> </table></div> </span>';
                    last_cmt_id = result.items[x]['comment_id'];
                }
                if (dis_check == 'none') {
                    html_cmt += '<div style="margin:0px auto;text-align:center;padding-top:10px;padding-bottom:30px;" id="comments_show_div"><span onclick="show_comments()" id="show_comments" style="font-size:1.05em;">Show Comments</span></div> ';
                }
                html_cmt = {
                    html: html_cmt
                };
                cur_video_holder.add(html_cmt);
                newItemCMT = {
                    html: '<div id="load_more_cmt" class="comment_load_more" width="100%" style="text-align:center;padding:20px 0px 30px;color:blue;display:' + dis_check + ';" onclick="load_more_click(\'' + master_id + '\', \'' + last_cmt_id + '\')">Load More Comments</div>'
                }
                if (items_len2 > 10) {
                    cur_video_holder.add(newItemCMT);
                }
            } else if (type != 'videobyurl') {
                var video_title_encoded = HtmlEncode(result['video_title']);
                var ip = result['ip'];
                newItem = {
                    html: '<div class="comment_title" width="100%">No Comments <div style="float:right;margin-right:6px;color:#2F4F4F;"><span onclick="comment_handler(\'' + video_title_encoded + '\',\'' + master_id + '\',\'' + ip + '\');" id="show_comments2" style="font-size:.9em;">Add Comment</span></div> <div style="clear:both;"></div> </div>'
                };
                cur_video_holder.add(newItem);
            } else {
                var video_title_encoded = HtmlEncode(result['video_title']);
                var ip = result.items['ip'];

                document.getElementById("load_more_cmt").remove();
                newItem = {
                    html: '<div class="comment_title" width="100%">No Comments <div style="float:right;margin-right:6px;color:#2F4F4F;"><span onclick="comment_handler(\'' + video_title_encoded + '\',\'' + master_id + '\',\'' + ip + '\');" id="show_comments2" style="font-size:.9em;">Add Comment</span></div> <div style="clear:both;"></div></div>'
                };
                cur_video_holder.add(newItem);
            }
        }
    });
}


function load_more_click(master_id, last_cmt_id) {
    if (glb_video_panel == 'Video') {
        var cur_video_holder = Ext.getCmp('video_holder');
    } else {
        var cur_video_holder = Ext.getCmp('video_holder2');
    }
    document.getElementById('load_more_cmt').removeAttribute("onclick");
    document.getElementById("load_more_cmt").innerText = 'Loading comments...';
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'list_comments',
            id: master_id,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pkey: account.get('pw'),
            start: 0,
            last_id: last_cmt_id,
            uniquify: Math.random()
        },
        callback: function(success, result) {
            if (result.total > 0) {
                var items = result.items;
                var items_len = result.items.length;
                var html_cmt = '';
                var last_cmt_id = '';
                var cmt_remove_clr = '';
                var x_smb = '';
                var owner = '0';
                var cmt_time = '';
                for (x = 0; x < items_len; x++) {
                    cmt_remove_clr = '';
                    x_smb = '&#9873;';
                    owner = '0';
                    cmt_time = result.items[x]['createdAt']; 
                    cmt_time = cmt_time.split(' ');
                    cmt_time = cmt_time[0];

                    if(result.items[x]['owner_id'] === account.get('user_id')){cmt_remove_clr = 'red';x_smb = '&#10008;';owner = '1';}
                    html_cmt += '<span id="cmt_'+result.items[x]['comment_id']+'"> <div class="details_comment" id="details_comment" style="border-left:0px;margin-left:0px;">                          <table width="100%" cellpadding="5" cellspacing="0" border="0"><tr><td width="40px" style="padding-right:8px;" valign="top"><div align="left" id="test" name="test" class="profile_thumb" style="height:50px;margin-left:0px;"><a href="javascript:void(0);" onclick="profile_handler(\'' + result.items[x]['c_user_id'] + '\');" ><img src="' + result.items[x]['c_profile_photo'] + '" width="40px" height="40px" style="border-radius: 50%;" /></a></div></td><td valign="top" style="padding:4px;"><div align="left" id="test" name="test"> <div style="float:left;"> <b>' + result.items[x]['name'] + '</b>  </div>  <div style="float:right;"> <b class="rpt_cmt" style="color:'+cmt_remove_clr+';" onclick=\'remove_comment("'+master_id+'","'+result.items[x]['comment_id']+'",'+owner+');\'>' + x_smb + '</b></div> <div style="clear:both;"></div> </div><div align="left" id="test" name="test" style="font-size:.84em;">' + result.items[x]['message'] + '<br/> <div class="cmt_replay" onclick="reply_comment(\''+result.items[x]['name']+'\');">Reply</div> <div class="cmt_time">'+cmt_time+'</div> <div style="clear:both;"></div> </div></td></tr> </table></div> </span>';
                    last_cmt_id = result.items[x]['comment_id'];
                }
                document.getElementById("load_more_cmt").remove();
                html_cmt = {
                    html: html_cmt
                };
                cur_video_holder.add(html_cmt);

                newItemCMT = {
                    html: '<div id="load_more_cmt" class="comment_load_more" width="100%" style="text-align:center;padding:20px 0px 30px;color:blue;" onclick="load_more_click(\'' + master_id + '\', \'' + last_cmt_id + '\')">Load More Comments</div>'
                }
                if (items_len > 9) {
                    cur_video_holder.add(newItemCMT);
                }
            } else {
                document.getElementById("load_more_cmt").remove();
            }
        }
    });
}

function playat_time(time) {
    var pro_for_dl = document.getElementById("dl_pro");
    if (typeof(pro_for_dl) != 'undefined' && pro_for_dl != null) {
        myvAlert('Please renew your Pro membership to use this feature.');
    } else {
        if (default_html5_playback == 1) {
            var video = document.getElementsByTagName("video")[0];
            if (typeof(video) != 'undefined' && video != null) {
				if(Ext.os.is('iOS')) {	
					glb_myPlayer.currentTime(time);
					glb_myPlayer.one('progress', function() {
						glb_myPlayer.currentTime(time);
					});
					if(Ext.Viewport.determineOrientation() == 'landscape') {
						glb_myPlayer.requestFullscreen();
					}
				}
				else {
					if(glb_myPlayer.readyState() == 0) {
						glb_myPlayer.one('progress', function() {
							glb_myPlayer.currentTime(time);
						});
					}
					else {
						glb_myPlayer.currentTime(time);
					}
					if(Ext.Viewport.determineOrientation() == 'landscape' && glb_device_type != 'tablet') {
						glb_myPlayer.requestFullscreen();
					}
				}
				video.play();
			}
        } else {
            myvAlert('This feature is available for HTML 5 player videos only.');
        }
    }
}
function pad_thumb(pad, str, padLeft) {
    if (typeof str === 'undefined')
        return pad;
    if (padLeft) {
        return (pad + str).slice(-pad.length);
    } else {
        return (str + pad).substring(0, pad.length);
    }
}
var getVideo = function(video_id, type, isRefresh, load_chromecast, reverse_fix, is_playlist) {
    var reverse;
    var uniquify;
    var pkey = account.get('pw');
    var user_id = account.get('user_id');
    glb_back_data['type'] = glb_current_list['type'];
    glb_back_data['id'] = glb_current_list['id'];
    // clear timeout for thumb slideshow
    try {
        // try/catch needed for potential video loading errors
        clearTimeout(myVarTimeOut);
    } catch (err) {}
    clearCountdown();

    if (!isRefresh) {
        glb_last_panel = Ext.Viewport.getActiveItem().id;
        uniquify = Math.random();
        if (glb_last_panel == 'General')
            reverse = true;
        else
            reverse = false;
    } else {
        uniquify = Math.random();
    }
    setMask('Loading...');
    var video_width = glb_panel_w;
	var video_height =	Math.round((video_width / 640) * 325);
    
	if (video_id == glb_next_video || video_id == glb_prev_video)
        glb_video_panel = (glb_video_panel == 'Video' ? 'Video2' : 'Video');

    if (Ext.Viewport.getActiveItem().id == 'Video' || Ext.Viewport.getActiveItem().id == 'Video2') {
        if (video_id == glb_prev_video)
            reverse = true;
        else if (video_id == glb_next_video)
            reverse = false;
    } else {
        reverse = false;
    }
    if(glb_seb_back == 0){
        if(video_id){
            searchviewbackhack();
            custom_views_arr.push(glb_video_panel);
            var array_data = [video_id, type, 0, 0, reverse_fix, is_playlist];
            glb_custom_views_arr_data.push(array_data);
        }
    }else{
        glb_seb_back = 0;
    }
    if (glb_video_panel == 'Video') {
        var cur_video_holder = Ext.getCmp('video_holder');
        var cur_video_breadcrumb = Ext.getCmp('video_breadcrumb');
        // only needed becuase sometimes there are issues with getting some videos, ex openload
        try{
            document.getElementById("vid_ref1").outerHTML = "";
        }catch(err){}
            var g = document.createElement("div");
            g.style.width = "100%";
            g.style.textAlign = "center";
            g.style.position = "absolute";
            g.style.padding = "15px";
            g.setAttribute("id", "vid_ref1");
            g.setAttribute("class","vid_ref");
            document.getElementById('video_holder').insertBefore(g, document.getElementById('video_holder').firstChild);
    } else {
        var cur_video_holder = Ext.getCmp('video_holder2');
        var cur_video_breadcrumb = Ext.getCmp('video_breadcrumb2');
        try{
            document.getElementById("vid_ref2").outerHTML = "";
        }catch(err){}
        var g = document.createElement("div");
        g.style.width = "100%";
        g.style.textAlign = "center";
        g.style.position = "absolute";
        g.style.padding = "15px";
        g.setAttribute("id", "vid_ref2");
        g.setAttribute("class","vid_ref");
        document.getElementById('video_holder2').insertBefore(g, document.getElementById('video_holder2').firstChild);
    }
    //I have added this b/c the card handler does not always work
    cur_video_holder.removeAll();
    //hard override for the collect panel
    if (Ext.Viewport.getActiveItem().id == 'Collect' || Ext.Viewport.getActiveItem().id == 'Comment') {
        reverse = true;
        glb_last_panel = "Home";
    }
    cur_video_breadcrumb.removeAll();
    glb_disable_ori_change = 1;
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: type,
            id: video_id,
            user_id: user_id,
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: pkey,
            width: video_width,
            html5: glb_HTML5_only,
            comments: 0,
            family_filter: family_filter,
            uniquify: uniquify,
            v: glb_ver,
            conn: checkConnectionType(),
            version: glb_version
        },
        callback: function(success, result) {
            if (result) {
                global_video = result;
                var items = result.items;
                var video_id = items[0].video_id;
                var master_id = items[0].master_id;
                var type_url = items[0].type_url;
                counter_url = items[0].type_url; // used for video counter
                var video_title = items[0].video_title;
                var video_type = items[0].video_type;
                var channel_name = items[0].channel_name;
                var channel_id = items[0].channel_id;
                var gallery_name = items[0].gallery_name;
                var gallery_id = items[0].gallery_id;
                var video_user_id = items[0].user_id;
                var posted_by = items[0].posted_by;
                var ip = items[0].ip;
                var video_thumbnail = items[0].video_thumbnail;
                glb_video_file = items[0].video_file;
                var video_file = items[0].video_file;
                var pro_download = items[0].pro_download;
                var is_dl = items[0].is_dl;
                var mobile_redirect = items[0].mobile_redirect;
                var mobile_redirect_cnt_v2 = items[0].mobile_redirect_cnt_v2;
                var mobile_redirect_elapsed = items[0].mobile_redirect_elapsed;
                var v_quota = items[0].v_quota;
                var video_type_id = items[0].video_type_id;
                var native_video = items[0].native_video;
                var access = items[0].access;
                var source_playback = items[0].source_playback;
                glb_source_playback = items[0].source_playback;
                glb_video_duration = items[0].duration;
                glb_thumbs_array = '';
                glb_thumbs_array = items[0].video_thumbs;

                var thumb_seconds = items[0].thumb_seconds;
                var thumb_duration = items[0].thumb_duration;
                var thumb_master_id = items[0].thumb_master_id;
                var vast_url = items[0].vast_url;
                var seek_thumbs = '';
                glb_html_chrome_seeker = '';

                if (thumb_master_id != null && thumb_seconds != null && thumb_duration != null) {
                    var max_thumbs = 60;
                    var z_factor = 1;
                    thumb_seconds = parseInt(thumb_seconds);
                    var time_array = [];
                    var num_thumbs = Math.ceil(thumb_duration / thumb_seconds);
                    if (num_thumbs > max_thumbs) {
                        z_factor = Math.ceil(thumb_duration / max_thumbs / thumb_seconds);
                    }

                    var thumb_time2 = parseInt(thumb_seconds);
                    for (im = z_factor; im <= num_thumbs; im += z_factor) {
                        var im2 = im;
                        new Image().src = "https://cdn1.myvidster.com/" + thumb_master_id + "/img" + pad_thumb('000', im2, true) + ".jpg";
                        seek_thumbs += "https://cdn1.myvidster.com/" + thumb_master_id + "/img" + pad_thumb('000', im2, true) + ".jpg ";
                        time_array.push(im2);
                    }

                    seek_thumbs = seek_thumbs.trim();
                    var thumbs = seek_thumbs;
                    var thumb_time = thumb_seconds;
                    thumbs = thumbs.split(" ");
                    var items44 = [];
					glb_topPaddingThumbnails = '';
                    glb_bottomPaddingThumbnails = '';
                    glb_seeker_duration = thumb_duration;
                    var seeker_time_w = 100;	//80 mrg
					var seeker_time_h = 60;	//40
					if(glb_device_type3 != 'phone'){ 
						seeker_time_w = seeker_time_w * 2.2;
						seeker_time_h = seeker_time_h * 2.2;
					}
					var seeker_padding = 15;
					glb_topPaddingThumbnails = 'padding-top: '+seeker_padding+'px;';
					glb_bottomPaddingThumbnails = 'padding-bottom: '+seeker_padding+'px;';
					glb_seek_carousel_h = seeker_time_h + (seeker_padding * 2);
                    var html_s = "";

                    // loads thumbnails for video skipping
                    for (thumb_pos = 0; thumb_pos < thumbs.length; thumb_pos++) {
                        if (thumb_pos < 20) {
                            new Image().src = thumbs[thumb_pos];
                            preload(thumbs[thumb_pos]);
                        }

                        html_s += '<a href="javascript:void(0);" ' +
                            'style="margin:0px 5px 0px;"><img src="' + thumbs[thumb_pos] +
                            '" height="' + seeker_time_h + 'px;" width="' + seeker_time_w +
                            'px;" border="0" onclick="playat_time(\'' + (time_array[thumb_pos] * 10) + '\')"></a>';

                        glb_html_chrome_seeker += '<a href="javascript:void(0);" ' +
                            'style="margin:0px 5px 0px;"><img src="' + thumbs[thumb_pos] +
                            '" height="' + seeker_time_h + 'px;" width="' + seeker_time_w +
                            'px;" border="0" onclick="chrome_thumb_seeker_func(' + time_array[thumb_pos] + ')"></a>';

                        thumb_time = thumb_time + thumb_seconds;
                    }

                    
                    // tell M to not include thumbs for iframe videos
                    

                    var seek_carousel_2 = {
                        xtype: 'panel',
                        scrollable: {
                            direction: 'horizontal',
                            directionLock: true,
                            indicators: false
                        },
                        config: {
                            inline: {
                                wrap: false
                            }
                        },
                        indicator: false,
                        height: glb_seek_carousel_h,
                        width: '100%',
                        html: '<div id="seek_carousel_2" style="margin:0px auto 0px;text-align:left;width:100%;overflow:hidden; white-space:nowrap;' + glb_bottomPaddingThumbnails + 'background-color:black;' + glb_topPaddingThumbnails + '">' + html_s + '</div>'
                    }

                    cur_video_holder.add(seek_carousel_2);
                }
                var source_playback_cnt = items[0].source_playback_cnt;
                var cloud_video = items[0].cloud_video;

                if (typeof device != 'undefined')
                    var banner_api = items[0].banner_api;
                else
                    items[0].banner_api = '';

                var ad_id = items[0].ad_id;
                glb_next_video = items[0].next_video;
                glb_prev_video = items[0].prev_video;
                glb_channel_id = items[0].channel_id;

                //custom vars for template
                items[0].type = type;
                items[0].video_title_encoded = HtmlEncode(video_title);
                items[0].type_url_encoded = encodeURIComponent(type_url);
                items[0].playlist = glb_play_playlist;
                if (glb_play_playlist == 1) {
                    items[0].playlist_btn = '<a href="javascript:void(0);" onclick="playlist_handler();"><div class="option_btn"><img id="player_pic" src="resources/themes/images/default/pictos/stop1.png" width="25"><div>auto play</div></div></a>';
                } else {
                    items[0].playlist_btn = '<a href="javascript:void(0);" onclick="playlist_handler();"><div class="option_btn"><img id="player_pic" src="resources/themes/images/default/pictos/play2.png" width="25"><div>auto play</div></div></a>';
                }
                if (type == 'videobydownload' && glb_myv_videos_dir && video_file.search("file://") >= 0) {
                    var video_file_tmp = video_file.split("/myv_videos/");
                    video_file = glb_myv_videos_dir + video_file_tmp[1];
                }

                if (video_user_id == user_id) {
                    items[0].isOwner = true;
                } else {
                    items[0].isOwner = false;
                }
                //reset version
                if (typeof device != 'undefined')
                    glb_ver = 2;

                //html5 support
                var video_thumb_left = Math.round(video_width / 2 - 50);
                var video_thumb_top = Math.round(video_height / 2 - 50);
                glb_source_dl = false;
                var playingSavedVideo = false;
                // used to playAd logic
                if (glb_session_start_time) {
                    var elapsed = new Date() - glb_session_start_time;
                } else {
                    var elapsed = mobile_redirect_elapsed + 1;
                }
                var startPlayAd = function() {
                    glb_ad_counter = 0; //reset
                    if (elapsed >= mobile_redirect_elapsed && !glb_play_playlist) {	//for now disable ads during autoplay b/c it screws with the player
						if (ad_id) {
                            playAd(ad_id);
                        }
                        glb_session_start_time = new Date();
                    }
                };
                if (type == 'videobydownload') {
                    account.set("current_video_type", type);
                    glb_video_type = type;
                    glb_current_video = items[0].download_id;
                    set_video_history(items[0].video_id);
                    if (typeof device != 'undefined') {
                        if (glb_downloading == glb_current_video) {
                            myvAlert("This video is downloading, please wait for the download to complete.");
                            Ext.Viewport.animateActiveItem("UserList", {
                                type: 'fade',
                            });
                            searchviewbackhack();
                            custom_views_arr.push("UserList");
                            glb_custom_views_arr_data.push([glb_video_type, glb_current_video]);
                            clearMask();
                            glb_disable_ori_change = 0;
                            return false;
                        }
                        if (video_file.substring(0, 4) != 'http') {
                            playingSavedVideo = true; // prevents ad from playing until video check is done
                            glb_dataDir.getFile(master_id + '.mp4', {
                                    create: false,
                                    exclusive: false
                                }, function(success) {
                                    startPlayAd(); // only play ad if video is found locally
                                    playingSavedVideo = false;
                                },
                                function(error) {
                                    playingSavedVideo = false;
                                    if (!glb_downloading)
                                        video_not_found(master_id, video_file, glb_current_video);
                                    else {
                                        myvAlert("Video not found and there is a download in progress, please wait for the download to complete.");
                                    }
                                });
                        }
                    }
                } else if (type == 'videobyurl') {
                    account.set("current_video_type", type);
                    glb_video_type = type;
                    glb_current_video = type_url;
                } else {
                    account.set("current_video_type", 'videobyid');
                    glb_video_type = 'videobyid';
                    glb_current_video = video_id;
                    set_video_history(glb_current_video);
                }
                //stuff to save to user storage
                account.set("current_video", glb_current_video);
                account.setDirty();
                store.sync();
                if (glb_last_list['type'] == 'Collection') {
                    glb_last_list['bc_type'] = glb_last_list['type'];
                    glb_last_list['bc_id'] = glb_last_list['id'];
                    glb_last_list['type'] = 'Channel';
                    glb_last_list['id'] = items[0].channel_id;
                    glb_last_list['title'] = items[0].channel_name;
                }
                //scroller
                items[0].width = video_width;
                items[0].height = video_height;

                //start download button logic
                if ((video_file || pro_download || video_type == 0 || source_playback || is_dl)) {
                    if (type == 'videobydownload') {
                        // check if pro user
                        var download_btn_func = "delete_download_handler('" + master_id + "', '" + encodeURIComponent(video_file) + "')";
                        items[0].download_btn = '<a href="javascript:void(0);" onclick="' + download_btn_func + '"><div class="option_btn"><img src="resources/themes/images/default/pictos/delete.png" width="25"><div>delete</div></div></a>';
                    } else if (video_type == 0 && Ext.os.is('iOS') && v_quota == 0) {
                        //this is dummy space for IOS/youtube
                        items[0].download_btn = '';
                    } else if (!is_dl && (source_playback != 'myvidster' || pro_download)) {
                        if (account.get('user_id')) {
                            if (pro_download == "get_pro") {
                                var download_btn_func = "pro_upgrade('PRO membership is needed to download this video.')";
                            } else if (pro_download) {
                                var download_btn_func = "download_video_handler('" + master_id + "', '" + video_id + "', '" + encodeURIComponent(type_url) + "', '" + encodeURIComponent(pro_download) + "', '" + encodeURIComponent(video_thumbnail) + "', '" + HtmlEncode(video_title) + "', '" + video_type_id + "','" + video_type + "')";
                            } else {
                                var download_btn_func = "download_video_handler('" + master_id + "', '" + video_id + "', '" + encodeURIComponent(type_url) + "', '" + encodeURIComponent(video_file) + "', '" + encodeURIComponent(video_thumbnail) + "', '" + HtmlEncode(video_title) + "', '" + video_type_id + "','" + video_type + "')";
                            }
                            // 2 = adlt, 1 = private
                            if (Ext.os.is('iOS')) {
                                if (access == 2 || access == 1) {
                                    items[0].download_btn = '<a href="javascript:void(0);" onclick="' + download_btn_func + '"><div class="option_btn"><img src="resources/themes/images/default/pictos/cloud_black_upload2.png" width="25"><div>cloud</div></div></a>';
                                }
                            } else {
                                items[0].download_btn = '<a href="javascript:void(0);" onclick="' + download_btn_func + '"><div class="option_btn"><img src="resources/themes/images/default/pictos/cloud_black_upload2.png" width="25"><div>cloud</div></div></a>';
                            }
                        } else {
                            if (Ext.os.is('iOS')) {
                                if (access == 2 || access == 1) {
                                    items[0].download_btn = '<a href="javascript:void(0);" onclick="Ext.Viewport.animateActiveItem(\'UserLogin\', { type: \'fade\' });"><div class="option_btn"><img src="resources/themes/images/default/pictos/cloud_black_upload2.png" width="25"><div>cloud</div></div></a>';
                                }
                            } else {
                                items[0].download_btn = '<a href="javascript:void(0);" onclick="Ext.Viewport.animateActiveItem(\'UserLogin\', { type: \'fade\' });"><div class="option_btn"><img src="resources/themes/images/default/pictos/cloud_black_upload2.png" width="25"><div>cloud</div></div></a>';
                            }
                        }
                    } else if (is_dl) {
                        if (Ext.os.is('iOS')) {

                            items[0].download_btn = '<a href="javascript:void(0);" onclick="getVideo(' + is_dl + ', \'videobydownload\', true);"><div class="option_btn"><img src="resources/themes/images/default/pictos/video.png" width="25"><div>saved</div></div></a>';

                        } else {
                            items[0].download_btn = '<a href="javascript:void(0);" onclick="getVideo(' + is_dl + ', \'videobydownload\', true);"><div class="option_btn"><img src="resources/themes/images/default/pictos/video.png" width="25"><div>saved</div></div></a>';
                        }

                    } else {
                        items[0].download_btn = '';
                    }
                } else {
                    items[0].download_btn = '';
                }
                //end download button logic 
				if(type != 'videobyurl')
					var videojs_id = "video_" + video_id;
				else
					var videojs_id = "video_web";
				
				var videojs_css = 'video-js';
				if (video_file) {

					if (typeof device != 'undefined' && (default_html5_playback == 0 || native_video)) {
                        error_obj = {
                            video_id: items[0].video_id,
                            master_id: items[0].master_id,
                            ip: items[0].ip
                        };
                        glb_video_file = video_file;
                        
						items[0].embed = '<div id="' + videojs_id + '" class="video-dev" style="background:url(' + video_thumbnail + ') no-repeat center;background-size:100% 100%;width:' + video_width + 'px;height:' + video_height + 'px;">';
                        items[0].embed += '<a href="javascript:void(0);" onclick="playVideo_handler(\'' + video_file + '\')"><span class="video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';
                        
						if (glb_session && video_file.search("http") != -1) items[0].embed += '<a href="javascript:void(0);" onclick="chomecast_load(\'' + video_file + '\',\'' + items[0].video_thumbnail + '\',\'' + items[0].video_title_encoded + '\',\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="casticon-span" style="right:' + 5 + 'px;top:' + 5 + 'px;"></span></a>';
                        
						items[0].embed += '</div>';

                        var tablet_countdown_adjustment = '';
                        if (glb_device_type == 'tablet') {
                            tablet_countdown_adjustment = 'font-size:150%;margin-top:-5px;';
                        }

                        items[0].embed +=
                            '<div id="countdown" style="display:none; width:100%">' +
                            '<h1 id="countdownText" style="padding: 5px;color:white;width:100%; transform: translateY(5px);' + tablet_countdown_adjustment + '">' + countdownMessage + '</h1> ' +
                            '<span style="width:50%;font-size:13pt;font-weight:bold;padding:5px;" >' +
                            '<div onclick="cancelCountdown()" style="color:white;background-color:red;padding:5px 0px;border-radius:8px;width:25vw; text-align:center"' +
                            '>Cancel</div> </span></div>';
                    } else {
                        
                        global_test = videojs_id;
                        glb_videojs_id = videojs_id;

                        //for some reason IOS does not like the contain property	controlsList="nodownload"
                        items[0].embed = '<div id="vid_div" class="video-dev" style="background-color:black;width:' + video_width + 'px;height:' + video_height + 'px;" >';

                    /*
                    if(items[0].iframe && items[0].iframe != 0 && items[0].iframe != null){
                        var ifrane_var = items[0].iframe;
                        items[0].embed += '<video id="' + videojs_id + '" class="' + videojs_css + ' vjs-default-skin" x-webkit-airplay="allow" ';
                        items[0].embed += ' playsinline controls   preload="none" width="' + video_width + '" height="' + video_height + '"';
                        items[0].embed += ' style="display:none;" ';
						items[0].embed += ' > ';
                        items[0].embed += '</video> ';

                             items[0].embed += '<iframe style="margin-top:0px;margin-left:0px" src="'+ifrane_var+'" width="'+video_width+'" height="'+video_height+'" frameborder="0" allowfullscreen allow="encrypted-media" scrolling="no"></iframe>'; 
                             items[0].embed += '</div>';
                   */
                   // }else{

						items[0].embed += '<video id="' + videojs_id + '" class="' + videojs_css + ' vjs-default-skin" x-webkit-airplay="allow" ';
                        items[0].embed += ' playsinline controls   preload="none" width="' + video_width + '" height="' + video_height + '"';
                        items[0].embed += ' style="background-color:black !important;" ';
						items[0].embed += ' poster="' + video_thumbnail + '" ';
						items[0].embed += ' > ';

                        if (Ext.os.is('iOS') && typeof device != 'undefined') {
							items[0].embed += '<source src="' + window.Ionic.normalizeURL(video_file) + '" type="video/mp4" /> ';
                        } else {
                            items[0].embed += '<source src="' + video_file + '" type="video/mp4" /> ';
                        }
				
                        items[0].embed += '</video> ';
                        

                        if ((glb_ios_glb_session || glb_session) && video_file.search("http") != -1) items[0].embed += '<a href="javascript:void(0);" onclick="chomecast_load(\'' + video_file + '\',\'' + items[0].video_thumbnail + '\',\'' + items[0].video_title_encoded + '\',\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="casticon-span" style="right:' + 5 + 'px;top:' + 5 + 'px; z-index:1000000;position:absolute;"></span></a>';

						if (glb_pip_support) items[0].embed += '<a href="javascript:void(0);" onclick="pip_load(\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="pipicon-span" style="left:' + 5 + 'px;top:' + 5 + 'px; z-index:1000000;position:absolute;"></span></a>';

                        items[0].embed += '</div>';

                   // }

                        var tablet_countdown_adjustment = '';
                        if (glb_device_type == 'tablet') {
                            tablet_countdown_adjustment = 'font-size:150%;margin-top:-5px;';
                        }

						items[0].embed +=
								'<div id="countdown" style="display:none; width:100%">' +
								'<h1 id="countdownText" style="padding: 5px;color:white;width:100%; transform: translateY(5px);' + tablet_countdown_adjustment + '">' + countdownMessage + '</h1> ' +
								'<span style="width:50%;font-size:13pt;font-weight:bold;padding:5px;" >' +
								'<div onclick="cancelCountdown()" style="color:white;background-color:red;padding:5px 0px;border-radius:8px;width:25vw; text-align:center"' +
								'>Cancel</div> </span></div>';
                    }

                    var newItem = {
                        html: video_pageTpl_HTML5.applyTemplate(items)
                    };
                } else if (source_playback) {
                    //////////////////
                    // SOURCE PLAYBACK
                    //////////////////

                    // needed because of online, no video_id in html5 video
                    if ((default_html5_playback == 0 || source_playback == "myvidster" || native_video)) {
                        var clickAction = 'cancelCountdownForBrowserVideoAndOpenBrowser()';
                        if (glb_last_panel != 'Video' && glb_last_panel != 'Video2') {
                            clickAction = 'cancelCountdownForBrowserVideo()';
                        }

                        // if video hosted locally and we need to decide what to do next for pro/non pro users in fetch_mp4, sometimes with source = myvidster we need to open child browser
                        var bg_style = 'background:url(' + video_thumbnail + ') no-repeat center;background-size:100% 100%;';
            

                           /* if(items[0].iframe && items[0].iframe != 0 && items[0].iframe != null){

                                var ifrane_var = items[0].iframe;

                                  items[0].embed = '<div class="video-dev" style="' + bg_style + 'width:' + video_width + 'px;height:' + video_height + 'px;">';
                             items[0].embed += '<iframe src="'+ifrane_var+'" width="'+video_width+'" height="'+video_height+'" frameborder="0" allowfullscreen allow="encrypted-media" scrolling="no"></iframe>'; 
                                 items[0].embed += '</div>';
                          */
                           // }else{

                        items[0].embed = '<div class="video-dev" style="' + bg_style + 'width:' + video_width + 'px;height:' + video_height + 'px;">';
                        items[0].embed += '<div id="' + videojs_id + '"><span class="loading-video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></div>';
                        items[0].embed += '</div>';

                           // }
                            


                        items[0].embed +=
                            '<div id="countdown" style="display:none; width:100%">' +
                            '<h1 id="countdownText" style="padding: 5px;color:white;width:100%; transform: translateY(5px);' + tablet_countdown_adjustment + '">' + countdownMessage + '</h1> ' +
                            '<span style="width:50%;font-size:13pt;font-weight:bold;padding:5px;" >' +
                            '<div id="countdownClick" onclick="' + clickAction + '" style="color:white;background-color:red;padding:5px 0px;border-radius:8px;width:25vw; text-align:center"' +
                            '>Cancel</div> </span></div>';

                            


                        // if video will launch in-app-browser if source_playback == "myvidster"
                        if (source_playback == "myvidster" &&
                            (glb_last_panel == 'Video' || glb_last_panel == 'Video2') // if last view was a video
                            &&
                            glb_play_playlist == 1) { // auto play turned on if 1
                            setTimeout(function() {
                                startBasicCountdown();
                            }, 10000);
                        }
                    } else {

						//fetch mp4 from device controlsList="nodownload"
						//
                        items[0].embed = '<div id="vid_div" class="video-dev" style="background-color:black;width:' + video_width + 'px;height:' + video_height + 'px;">';
                        items[0].embed += '<video id="' + videojs_id + '" class="' + videojs_css + ' vjs-default-skin" x-webkit-airplay="allow" ';
                        items[0].embed += ' playsinline controls  preload="none" width="' + video_width + '" height="' + video_height + '" style="background-color:black !important;" ';
                        items[0].embed += 'poster="' + video_thumbnail + '" ';
                        items[0].embed += ' > ';
						items[0].embed += '<source src="https://cdn2.myvidster.com/vids/videoplayback_sm.mp4" type="video/mp4" /> ';
                        items[0].embed += '</video> ';

                        if (glb_ios_glb_session || glb_session) {
                            items[0].embed += '<a href="javascript:void(0);" onclick="chomecast_load(glb_myPlayer.currentSrc(),\'' + items[0].video_thumbnail + '\',\'' + items[0].video_title_encoded + '\',\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="casticon-span" style="right:' + 5 + 'px;top:' + 5 + 'px; z-index:1000000;position:absolute;"></span></a>';
                        }

						if (glb_pip_support) items[0].embed += '<a href="javascript:void(0);" onclick="pip_load(\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="pipicon-span" style="left:' + 5 + 'px;top:' + 5 + 'px; z-index:1000000;position:absolute;"></span></a>';

                        items[0].embed += '</div>';

                        var tablet_countdown_adjustment2 = '';
                        if (glb_device_type == 'tablet') {
                            tablet_countdown_adjustment2 = 'font-size:150%;margin-top:-5px;';
                        }

                        items[0].embed +=
                            '<div id="countdown" style="display:none; width:100%">' +
                            '<h1 id="countdownText" style="padding: 5px;color:white;width:100%; transform: translateY(5px);' + tablet_countdown_adjustment2 + '">' + countdownMessage + '</h1> ' +
                            '<span style="width:50%;font-size:13pt;font-weight:bold;padding:5px;" >' +
                            '<div onclick="cancelCountdown()" style="color:white;background-color:red;padding:5px 0px;border-radius:8px;width:25vw; text-align:center"' +
                            '>Cancel</div> </span></div>';
                    }

                    var newItem = {
                        html: video_pageTpl_HTML5.applyTemplate(items)
                    };
                } else if (video_type == 0 && typeof device != 'undefined' && default_html5_playback == 0) {
                    ///////////////////////////
                    // YOUTUBE LEGACY PLAY CODE
                    ///////////////////////////
                    is_yt_playing = false;

                    items[0].playlist = glb_play_playlist;
                    items[0].embed = '<div class="video-dev" style="background:url(' + items[0].video_thumbnail + ') no-repeat center;background-size:100% 100%;width:' + items[0].width + 'px;height:' + items[0].height + 'px;">';
                    items[0].embed += '<a href="javascript:void(0);" onclick="youtube_playVideo(\'' + video_type_id + '\',onSuccess,onFail)"><span class="video-link-span" style="left:' + video_thumb_left + 'px;top:' + video_thumb_top + 'px;"></span></a>';

                    if (glb_session || glb_ios_glb_session) items[0].embed += '<a href="javascript:void(0);" onclick="yt_chomecast_load(\'' + video_type_id + '\',\'' + items[0].video_thumbnail + '\',\'' + items[0].video_title_encoded + '\',\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="casticon-span" style="right:' + 5 + 'px;top:' + 5 + 'px;"></span></a>';
                    else items[0].embed += '<a href="javascript:void(0);" onclick="load_youtube_legacy(\'' + video_id + '\',\'' + video_type_id + '\',\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="youtube-span" style="right:' + 5 + 'px;top:' + 5 + 'px;"></span></a>';

					if (glb_pip_support) items[0].embed += '<a href="javascript:void(0);" onclick="pip_load(\'' + items[0].width + '\',\'' + items[0].height + '\')"><span class="pipicon-span" style="left:' + 5 + 'px;top:' + 5 + 'px; z-index:1000000;position:absolute;"></span></a>';

                    items[0].embed += '</div>';

                    var newItem = {
                        html: video_pageTpl_HTML5.applyTemplate(items)
                    };
                } else if (video_type == 0) {
                    //////////////////////////
                    // YOUTUBE VIDEOS (IFRAME)
                    //////////////////////////
                    var extension = '?wmode=opaque&theme=light';
                    if (glb_play_playlist == 1) {
                        // extension = '?rel=0&autoplay=1&wmode=opaque&theme=light&mute=1';
                    }
                    items[0].playlist = glb_play_playlist;
                    glb_youtube_video_height = items[0].height;
                    glb_youtube_video_width = items[0].width;
                    glb_youtube_video_id = video_type_id;

                    glb_youtubeEmbedId = 'youtube_player' + glb_autoplayIndex;

                    items[0].embed = '<div id="' + glb_youtubeEmbedId + '"></div>';

                    var newItem = {
                        html: video_pageTpl_HTML5.applyTemplate(items)
                    };
                } else {
                    var newItem = {
                        html: video_pageTpl.applyTemplate(items)
                    };
                }
                cur_video_holder.add(newItem);
                ////////////////////////////////////////
                // YOUTUBE API HANDLING IF YOUTUBE VIDEO
                ////////////////////////////////////////
                if (video_type == 0) {
                    if (!glb_youtube_api) {
                        glb_youtube_api = document.createElement('script');
                        glb_youtube_api.src = "https://www.youtube.com/iframe_api";
                        var firstScriptTag = document.getElementsByTagName('script')[0];
                        firstScriptTag.parentNode.insertBefore(glb_youtube_api, firstScriptTag);
                        clearMask();
                    } else {
                        var youtubeVideo = new YT.Player(glb_youtubeEmbedId, {
                            height: glb_youtube_video_height,
                            width: glb_youtube_video_width,
                            videoId: glb_youtube_video_id,
                            events: {
                                'onReady': onPlayerReady,
                                'onStateChange': onPlayerStateChange
                            }
                        });
                    }
                }
                //breadcrumbs
                if (type == 'videobydownload') {
                        
                    var breadcrumbs = [{
                        xtype: 'spacer'
                    }, {
                        xtype: 'button',
                        pressedCls: '',
                        ui: 'round',
                        text: 'Downloads',
                        width: 100,
                        handler: function() {
                            clearCountdown();
                            list_videos('Downloads', user_id, 0);
                        }
                    }, {
                        xtype: 'spacer'
                    }];
                } else if (type == 'videobyurl') {
                    var breadcrumbs = [{
                        xtype: 'spacer'
                    }, {
                        xtype: 'button',
                        pressedCls: '',
                        ui: 'round',
                        text: 'Web',
                        width: 100,
                        handler: function() {
                            clearCountdown();
                            Ext.Viewport.animateActiveItem('Search', {
                                type: 'fade',
                            });
                            searchviewbackhack();
                            custom_views_arr.push("Search");
                            glb_custom_views_arr_data.push([0]);
                        }
                    }, {
                        xtype: 'spacer'
                    }];
                } else {
                    var breadcrumbs = [{
                        xtype: 'button',
                        pressedCls: '',
                        ui: 'round',
                        text: posted_by,
                        width: 100,
                        handler: function() {
                            clearCountdown();
                            list_videos('User', video_user_id, 0);
                        }
                    }, {
                        xtype: 'spacer',
                    }, {
                        xtype: 'button',
                        pressedCls: '',
                        ui: 'round',
                        text: gallery_name,
                        width: 100,
                        handler: function() {
                            clearCountdown();
                            list_videos('Collection', gallery_id, 0);
                        }
                    }, {
                        xtype: 'spacer',
                    }, {
                        xtype: 'button',
                        pressedCls: '',
                        ui: 'round',
                        text: channel_name,
                        width: 100,
                        handler: function() {
                            clearCountdown();
                            list_videos('Channel', channel_id, 0);
                        }
                    }];
                }
                if (breadcrumbs) {
                    cur_video_breadcrumb.add(breadcrumbs);
                }
				if (type != 'videobyurl') {
					load_comments_init(master_id, video_id, type, cur_video_holder);
				}
                if (reverse_fix) {
                    reverse = true;
                }
                Ext.Viewport.animateActiveItem(glb_video_panel, {
                    type: 'fade',
                });
                glb_master_id = master_id;
                if (typeof device != 'undefined') {
                    doPlayList();
                }
                if (glb_myPlayer) {
                    glb_myPlayer.dispose();
                    glb_myPlayer = false;
                }
                if (videojs_id) {
					var autoplay = false;

					if (glb_play_playlist && !isRefresh) {
						autoplay =  true;
					}
					var options = { autoplay: autoplay, 
						nativeControlsForTouch: false
					};
					if(default_html5_playback && source_playback != 'myvidster' && !native_video) {
						videojs(videojs_id, options).ready(function() {
							glb_myPlayer = this;
							
                            var techEl = glb_myPlayer.tech(true).el();
                            
							if(glb_device_type == 'tablet') {
								var enterOnRotate = false;
							}
							else {
								var enterOnRotate = true;
                            }
							if(typeof glb_myPlayer.mobileUi != 'undefined') {
								glb_myPlayer.mobileUi({
								  fullscreen: {
                                    enterOnRotate: enterOnRotate,
									lockOnRotate: false
								  },
								  touchControls: {
									seekSeconds: 15,
									tapTimeout: 300,
									disableOnEnd: false
								  }
								});
							}
							if(typeof glb_myPlayer.vjsdownload != 'undefined') {
								glb_myPlayer.vjsdownload({
								  beforeElement: 'playbackRateMenuButton',
								  textControl: 'Download video',
								  name: 'downloadButton',
								});
							}
							glb_myPlayer.volume(glb_cloud_volume); //iOS does not allow volume control
							if (Ext.os.is('iOS') && typeof device != 'undefined') {
								glb_myPlayer.on('error', function() { 
									ios_fallback_player(counter_url,video_id,master_id,ip);
								});
							}
							glb_myPlayer.on('ended', function() {
								if (glb_play_playlist) {
									getVideo(String(glb_next_video), glb_video_type);
                                }

                            });
							glb_myPlayer.on('fullscreenchange', function() {
								if(glb_immersiveMode) {
									if(glb_myPlayer.isFullscreen())
										AndroidFullScreen.immersiveMode(Ext.emptyFn, Ext.emptyFn);
									else
										AndroidFullScreen.showSystemUI(Ext.emptyFn, Ext.emptyFn);
                                }
                                
                            });
							glb_myPlayer.on('volumechange', function () {
								if(glb_myPlayer.muted())
									glb_cloud_volume=0;
								else
									glb_cloud_volume = Number((glb_myPlayer.volume()).toFixed(2));
							});
							if (typeof glb_myPlayer.vastClient != 'undefined' && vast_url && Ext.os.is('Android') && (!source_playback || !autoplay)) {
								var vastAd = glb_myPlayer.vastClient({
									adTagUrl: vast_url,
									playAdAlways: true,
									adCancelTimeout: 5000,
									adsEnabled: true
								});
							}
						});
					}
                }
                if (cloud_video == "1") {
                    source_playback = "get_pro2";
                }
                if (source_playback) {
					fetch_mp4(source_playback, items, cur_video_holder, video_thumb_top, video_thumb_left, source_playback_cnt, load_chromecast, native_video, videojs_id);
                } else {
                    //load chromecast
                    if (load_chromecast && video_file) {
                        chomecast_load(video_file, video_thumbnail, items[0].video_title_encoded, video_width, video_height);
                    } else if (load_chromecast && video_type == 0) {
                        yt_chomecast_load(video_type_id, video_thumbnail, items[0].video_title_encoded, video_width, video_height);
                    }
					clearMask();
                }
                if (banner_api)
                    fetch_banner_ad(banner_api, video_id, video_width);
                glb_disable_ori_change = 0;
                //ads start
                if (v_quota <= 0) { //run ad logic
                    if (typeof device == 'undefined') {

                        if (glb_ad_counter > 0) {

                            if (glb_ad_fix == 1)
                                Ext.DomHelper.append(document.body, "<div id='__kx_ad_2437'></div><script type=\"text/javascript\" src=\"https://www.myvidster.com/js/mobile_ad_tag.php?id=2437\"></script>");
                            else
                                Ext.DomHelper.append(document.body, "<div id='__kx_ad_2436'></div><script type=\"text/javascript\" src=\"https://www.myvidster.com/js/mobile_ad_tag.php?id=2436\"></script>");

                            glb_ad_counter == 1;
                        } else {
                            glb_ad_counter++;
                        }
                    } else {
                        if (glb_ad_counter == 0) {
                            if (ad_id && elapsed >= mobile_redirect_elapsed) {
                                loadAd(ad_id);
                            }
                            if (elapsed >= mobile_redirect_elapsed) {
                                glb_ad_counter++;
                            }
                        } else if (glb_ad_counter >= mobile_redirect_cnt_v2) {
                            if (!playingSavedVideo) {
                                startPlayAd();
                            }
                        } else {
                            if (elapsed >= mobile_redirect_elapsed) glb_ad_counter++;
                        }
                    }
                }
                //ads end
                if (result.error == 'timeout') {
                    Ext.Viewport.animateActiveItem(glb_last_panel, {
                        type: 'fade',
                    });
                    searchviewbackhack();
                    custom_views_arr.push(glb_last_panel);
                    glb_custom_views_arr_data.push([0]);
                    glb_disable_ori_change = 0;
                    clearMask();
                } 
                if (typeof device != 'undefined' && typeof window.ga != 'undefined') window.ga.trackView('/mobile/video/' + video_id);
            } else {
                //something bad happen
                myvAlert('No Video Found');
                delete Ext.util.JSONP.current;
                // try/catch in case something bad happends, from above Marques comments
                try{
                    if(custom_views_arr.length > 1){
                    custom_views_arr.pop();
                    glb_custom_views_arr_data.pop();
                    }
                }catch(err){}
                searchviewbackhack();
                glb_disable_ori_change = 0;
                clearMask();
            }
            if (glb_last_panel == 'Home')
                Ext.getCmp('home_refresh_var').deselectAll();
            else if (glb_last_panel == 'Search')
                Ext.getCmp('search_var').deselectAll();
            else
                Ext.getCmp('list_videos_var').deselectAll();
        }
    });
};

function onYouTubeIframeAPIReady() {
    var youtubeVideo = new YT.Player(glb_youtubeEmbedId, {
        height: glb_youtube_video_height,
        width: glb_youtube_video_width,
        videoId: glb_youtube_video_id,
        events: {
            'onError': onYouTubeError,
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange,
        }
    });
}

function onYouTubeError(event) {}
function onPlayerReady(event) {
    if ( glb_play_playlist ) {
        event.target.playVideo();
        glb_done_playing = false;
    }
}

function onPlayerStateChange(event) {
    if(event.data === 0 && !glb_done_playing){
        if ( glb_play_playlist ) {
            glb_done_playing = true;
            glb_autoplayIndex += 1;
            getVideo(String(glb_next_video), glb_video_type);
        }
        // the video is end, do something here.
    }
}

function queue_video_handler(video_id, master_id, video_title) {
    if (account.get('user_id')) {
        var more_btn_sz = '';
        if (glb_device_type == 'tablet') {
            more_btn_sz = 'font-size: 1.22em;';
        }
        var video_actionSheet = Ext.create('Ext.ActionSheet', {
            items: [{
                text: 'Queue',
                style: more_btn_sz,
                handler: function(btn, evt) {
                    queue_video(video_id, master_id, video_title, '2');
                    video_actionSheet.hide();
                }
            }, {
                text: 'Watched',
                style: more_btn_sz,
                handler: function(btn, evt) {
                    queue_video(video_id, master_id, video_title, '1');
                    video_actionSheet.hide();
                }
            }, {
                text: 'Cancel',
                ui: 'decline',
                style: more_btn_sz,
                handler: function(btn, evt) {
                    video_actionSheet.hide();
                }
            }]
        });
        Ext.Viewport.add(video_actionSheet);
        video_actionSheet.show();

    } else{
        Ext.Viewport.animateActiveItem("UserLogin", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("UserLogin");
        glb_custom_views_arr_data.push([0]);
    }
}

function collect_video_handler(video_id, video_title, access) {
    setMask('Collecting...');
    if (account.get('user_id')) {
        if (Ext.util.JSONP.current == null)
            collect_video(video_id, video_title, access, 'collect');

    } else {
        clearMask();
        Ext.Viewport.animateActiveItem("UserLogin", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("UserLogin");
        glb_custom_views_arr_data.push([0]);
    }
}

function comment_handler(video_title, master_id, ip) {
    if (account.get('user_id'))
        postComment_setup(video_title, master_id, ip);
    else{
        Ext.Viewport.animateActiveItem("UserLogin", {
            type: 'fade',
        });
        searchviewbackhack();
        custom_views_arr.push("UserLogin");
        glb_custom_views_arr_data.push([0]);
    }
}

function profile_handler(user_id) {
    if (user_id != '0') {
        list_videos('User', user_id, 0);
    } 
}

var logout = function() {
    account.set("email", '');
    account.set("pw", '');
    account.set("phone_number", '');
    account.set("user_id", '');
    account.set("disp_name", '');
    account.set("video_history", '');
    localStorage.setItem('video_history', '');
    localStorage.setItem('filter_by', '');
    account.set("video_history_chk", '');
    account.set("v_orientation_chk", '');
    store.getProxy().clear();
    glb_video_history = new Array();
    Ext.Viewport.animateActiveItem("Home", {
        type: 'fade',
    });
    searchviewbackhack();
    custom_views_arr.push("Home");
    glb_custom_views_arr_data.push([0]);
    clear_search();

    if (Ext.os.is('iOS') && typeof window.AppGroupsUserDefaults != 'undefined') {
        ///  7.30        
        var options3 = {
            key: "user_id",
            value: "",
            suite: "group.com.myvidster"
        };
        window.AppGroupsUserDefaults.save(options3,
            function() {
                // success
            },
            function() {
                // failed
            });

        var options4 = {
            key: "user_pwd",
            value: "",
            suite: "group.com.myvidster"
        };
        window.AppGroupsUserDefaults.save(options4,
            function() {
                // success
            },
            function() {
                // failed
            });
    }

    glb_msg_token = '';
    try{
        // important try/catch if there's an issue restarting the app
        location = location;
    }
    catch(err){myvAlert('Please restart the app!');}
}

var login_first = function(){
    store.load();
    if (store.getCount() == 0) {
        glb_logincheck_done = true;
        return;
    }
    account = store.first();
    app_store.load();
    if (app_store.getCount() == 0) {
        glb_logincheck_done = true;
        return;
    }
    application = app_store.first();
    if (account.get('email')) {
        glb_pusher_login_hack = 1;
        glb_already_loggedin = true;
        loginUser(account.get('email'), account.get('pw'), 'Home');
    } else {
        glb_logincheck_done = true;
        return;
    }
}

var loginUser = function(email, pw, panel) {                                                                                                                                                                                                      
    var recpt_data = '';                                                                                                                                                                                             
    if (panel == 'UserMenu') pw = SHA1(pw);
    uniquify = Math.random();
    setMask('Please wait...');
    if (typeof device != 'undefined') {
        cordova.getAppVersion(function(version) {
            glb_version = version;
        });
    }
    ///////////////////////// ////////////////////////////// 
    var http = new XMLHttpRequest();
    var url = "https://api.myvidster.com/mobile_json2.php";
    var params = "type=login&id=" + email + "&pkey=" + pw + "&uniquify=" + uniquify + "&token=" + glb_msg_token;
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.onreadystatechange = function() {	//this is trigging the Load masking
        if (http.status == 200 && http.readyState == 4) {
            var test_res = http.responseText;
            var result = JSON.parse(test_res);
            var response = result.items[0]['response'];
            if (response == 'success') { 
                if (Ext.os.is('iOS') && typeof window.AppGroupsUserDefaults != 'undefined') {
                    // text: now you can bookmark videos from your mobile browser! 
                    var alert_msg_data = '';
                    var options0 = {
                        key: "alert_msg_check",
                        suite: "group.com.myvidster"
                    };
                    window.AppGroupsUserDefaults.load(options0,
                        function(result) {
                            // success
                            alert_msg_data = result;
                            // disable Safari Sharing tutorial
                        },
                        function() {
                            alert_msg_data = "unseen";
                            var options3 = {
                                key: "alert_msg_check",
                                value: "unseen",
                                suite: "group.com.myvidster"
                            };
                            window.AppGroupsUserDefaults.save(options3,
                                function() {
                                    // success
                                },
                                function() {
                                    // failed
                                });
                        });
                    var options = {
                        key: "user_id",
                        value: email,
                        suite: "group.com.myvidster"
                    };
                    window.AppGroupsUserDefaults.save(options,
                        function() {
                            // success
                        },
                        function() {
                            // failed
                        });
                    var options2 = {
                        key: "user_pwd",
                        value: pw,
                        suite: "group.com.myvidster"
                    };
                    window.AppGroupsUserDefaults.save(options2,
                        function() {},
                        function() {});
                }
                var cloud_vids = result.items[0]['cloud_count'];
                glb_login_cloud_vids = cloud_vids;
                
                if (typeof device != 'undefined' && !glb_unlock) {
                    family_filter = result.items[0]['family_filter'];
                    glb_login_family_filter = family_filter;
                    if (family_filter == 0) {
                        Ext.getCmp('family_filter').show();
                        account.set("unlock", 1);
                    }
                }
                account.set("email", result.items[0]['user_email']);
                account.set("pw", pw);
                account.set("family_filter", family_filter);
                account.set("user_id", result.items[0]['user_id']);
                account.set("disp_name", result.items[0]['disp_name']);
                account.set("profile_photo", result.items[0]['profile_photo']);
                account.set("phone_number", result.items[0]['phone_number']);
                glb_login_disp_name = result.items[0]['disp_name'];
                //////////// receipt check 
                //if(Ext.os.is('iOS') && result.items[0]['user_id'] && pw && window.inAppPurchase !== undefined && recpt_data != ''){
                // checkReceipt(recpt_data);
                //}
                account.setDirty();
                store.getProxy().clear();
                store.add({
                    email: email,
                    pw: pw,
                    user_id: result.items[0]['user_id'],
                    disp_name: result.items[0]['disp_name'],
                    profile_photo: result.items[0]['profile_photo'],
                    family_filter: family_filter,
                    v_orientation_chk: v_orientation_chk,
                    anc1: 1,
                    video_history: glb_video_history,
                    video_history_chk: glb_video_history_chk,
                    HTML5_only: glb_HTML5_only,
                    filter_by: glb_filter_by
                });
                store.sync();
                account = store.first();
                var profile_photo = account.get('profile_photo') + '&mobile=' + Math.random();
                preload(profile_photo);

                var prof_pic_sz = 100;
                var text_sz = '';
                if (glb_device_type == 'tablet') {
                    prof_pic_sz = 130;
                    text_sz = 'font-size:1.4em; font-weight:bold;'
                }

                var video_count = result.items[0]['video_count'];
                glb_login_video_count = video_count;
                if (video_count == null) {
                    video_count = '0';
                }
                userFollowerCount = result.items[0]['follower_count'];
                glb_login_follower_count = userFollowerCount;
                if (userFollowerCount == null) {
                    userFollowerCount = '0';
                }
               userFollowingCount = result.items[0]['following_count'];
               glb_login_following_count = userFollowingCount;
                if (userFollowingCount == null) {
                    userFollowingCount = '0';
                }

                var zz = '';
                if (Ext.os.is('iOS') && Number(glb_version) < Number("7.35")) {
                    zz = '<table border="0" class="new_table"><tr><td rowspan="2" style="text-align:left;padding-left:15px;"><div style="position:relative;width:' + prof_pic_sz + 'px;"> <img onclick="upload_photo();" src="' + profile_photo + '" height="' + prof_pic_sz + '" width="' + prof_pic_sz + '" border="0" style="border-radius: 50%;"> </div></td><td height="50" style="text-align:center;">' + video_count + '<br><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td><td style="border-left:solid 0px;text-align:center;" onclick="search_handler(\'' + 'followers:' + result.items[0]['disp_name'] + '\');">' + userFollowerCount + '<br> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td><td style="border-left:solid 0px;text-align:center;"><span id="userFollowingCount">' + userFollowingCount + '</span><br> <span style="color:#808080;" onclick="search_handler(\'following:'+result.items[0]['disp_name']+'\');" class="dark_mode_txt">Following</span></td></tr><tr><td colspan="4" style="font-size:1.2em !important;" align="center"><div style="width:100%;float:left;padding-right:4px;">&nbsp;</div> <div style="clear:both;"></div> </td></tr></table>';
                } else {
                    zz = '<table border="0" class="new_table"><tr><td rowspan="2" style="text-align:left;padding-left:15px;"><div style="position:relative;width:' + prof_pic_sz + 'px;"> <img onclick="upload_photo();" src="' + profile_photo + '" height="' + prof_pic_sz + '" width="' + prof_pic_sz + '" border="0" style="border-radius: 50%;"> </div></td><td height="50" style="text-align:center;">' + video_count + '<br><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td><td style="border-left:solid 0px;text-align:center;" onclick="search_handler(\'' + 'followers:' + result.items[0]['disp_name'] + '\');">' + userFollowerCount + '<br> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td><td style="border-left:solid 0px;text-align:center;"><span id="userFollowingCount">' + userFollowingCount + '</span><br> <span style="color:#808080;" onclick="search_handler(\'following:'+result.items[0]['disp_name']+'\');" class="dark_mode_txt">Following</span></td></tr><tr><td colspan="4" style="font-size:1.2em !important;" align="center"><div style="width:100%;float:left;padding-right:4px;"><div class="follows" onclick="invite_friends();">Invite Friends</div> </div> <div style="clear:both;"></div></td></tr></table>';
                }
                glb_login_zz = zz;

                Ext.getCmp('user_pic_header').setHtml(zz);
                Ext.getCmp('userMenuToolbar').setTitle(account.get('disp_name'));
                glb_current_username = account.get('disp_name');

                Ext.util.JSONP.request({
                    url: 'https://api.myvidster.com/mobile_json2.php',
                    callbackKey: 'callback',
                    params: {
                        type: 'read_message',
                        user_id: account.get('user_id'),
                        token: glb_msg_token,
                        pro_status: glb_is_pro_ckeck,
                        pkey: account.get('pw'),
                        from_user_id: 1 
                    },
                    callback: function(success, result) {
                        if (result) {
                            // this is run upon app startup
                            glb_msg_token = result.items[0]['token'];
                            update_tab_badge(result.items[0]['unread_msgs']);
                            create_pusher();

                            Ext.util.JSONP.request({
                                url: 'https://api.myvidster.com/mobile_json2.php',
                                callbackKey: 'callback',
                                params: {
                                    type: 'check_proMember',
                                    user_id: account.get('user_id'),
                                    token: glb_msg_token,
                                    pkey: account.get('pw')
                                },
                                callback: function(success, result) {
                                    if (result) {
                                        if(result.items[0]['pro_mem'] > 0){
                                            glb_is_pro_ckeck = 1;
                                        }else{
                                            glb_is_pro_ckeck = 0;
                                        }
                                    }
                                }
                            });

                            glb_logincheck_done = true;
                        }else{
                            glb_logincheck_done = true;
                        }
                    }
                });
               clearMask();
                // refresh fron logout then login
                if(glb_pusher_login_hack == 0){
                    clearMask();
                    try{
                        Ext.Viewport.animateActiveItem("UserMenu", {
                            type: 'fade',
                        });
                        //window.location.reload();
                    }
                    catch(err){myvAlert('Please restart the app!');}
                    
                }
            } else {
                account.set("email", '');
                account.set("pw", '');
                account.set("user_id", '');
                account.set("disp_name", '');
                store.getProxy().clear();
                clearMask();
                myvAlert('Login Failed. \n\nYou Can Also Enter Your Username To Login.');
                glb_logincheck_done = true;
            }
        } else if (http.readyState == 4) {   
            clearMask();
            myvAlert('Connection to server failed <br/> <a href="https://twitter.com/myvidster" target="_blank" style="color:#f5f5f5;">Check website status</a><br/><br/>');
            glb_logincheck_done = true;
        }
    }
    http.send(params);
};


function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function cycle_text(val) {
    if (val == 0) {
        document.getElementById("cycle_text").innerHTML = "You will only be charged <b>1 time</b> and will have to manually renew when your membership expires.";
    } else {
        document.getElementById("cycle_text").innerHTML = "You will be charged <b>1 time</b> every month until you cancel your membership.";
    }
}

function myv_url_share(text) {
    // try/catch needed to prevent app crashing from from invalide videos 
    try {
        var urlRegex = /(https?:\/\/[^\s]+)/g;
        return text.replace(urlRegex, function(url) {
            var n = url.indexOf("myvidster.com/video/");
            var res = url.split("/");
            var vid_id = res[res.length - 1];
            var vid_id_web = res[res.length - 2];

            if (n != -1 && isNumeric(vid_id) && vid_id != 0) {
                return '<span style="color:#3C7A1E;font-weight:bold;" onclick="getVideo(\'' + vid_id + '\', \'videobyid\', false);">' + url + '</span>';
            }
            else if(n != -1 && isNumeric(vid_id_web) && vid_id_web != 0 ){ 
                return '<span style="color:#3C7A1E;font-weight:bold;" onclick="getVideo(\'' + vid_id_web + '\', \'videobyid\', false);">' + url + '</span>';
            } 
            else {
                return '<span style="color:#3C7A1E;font-weight:bold;" onclick="load_webpage(\'' + url + '\', false);">' + url + '</span>';
            }
        })
    } catch (err) {
    }
}

var reset_pw = function() {
    var email = Ext.getCmp('email').getValue();
    if (email) {
        var msg = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msg.show({
            title: 'Reset password',
            msg: 'Are you sure you want to reset your password?',
            buttons: [{
                text: 'Yes',
                itemId: 1
            }, {
                text: 'No',
                itemId: 0
            }],
            fn: function(response) {
                if (response == 1) {
                    uniquify = Math.random();
                    setMask('Please wait...');
                    Ext.util.JSONP.request({
                        url: 'https://api.myvidster.com/mobile_json2.php',
                        callbackKey: 'callback',
                        params: {
                            type: 'reset',
                            email: email,
                            token: glb_msg_token,
                            uniquify: uniquify
                        },
                        callback: function(success, result) {
                            if (result) {
                                var response = result.items[0]['response'];
                                myvAlert(response);
                                clearMask();
                            } else {
                                myvAlert('Reset failed, please try again');
                                clearMask();
                            }
                        }
                    });
                }
            }
        });
    } else {
        myvAlert("Please enter your account's email");
    }
}

// Function that will produce the bar at the top of the user list component with the profile picture, followers, and following numbers, as well as transition the view to the user list
function createUserListHeaderAndTransitionToUserList(result, didRegister) {
    var profile_photo = account.get('profile_photo') + '&mobile=' + Math.random();
    preload(profile_photo);
    var prof_pic_sz = 100;
    if (glb_device_type == 'tablet') {
        prof_pic_sz = 130;
    }
    var video_count = result.items[0]['video_count'];
    if (video_count == null) {
        video_count = '0';
    }
    userFollowerCount = result.items[0]['follower_count'];
    if (userFollowerCount == null) {
        userFollowerCount = '0';
    }
    userFollowingCount = result.items[0]['following_count'];
    if (userFollowingCount == null) {
        userFollowingCount = '0';
    }

    var disp_name = account.get('disp_name');
    var zz = '<table border="0" class="new_table"><tr><td rowspan="2" style="text-align:left;padding-left:15px;"><div style="position:relative;width:'+prof_pic_sz+'px;"> <img onclick="upload_photo();" src="' + profile_photo + '" height="' + prof_pic_sz + '" width="' + prof_pic_sz + '" border="0" style="border-radius: 50%;"> </div></td><td height="50" style="text-align:center;">'+video_count+'<br><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td><td style="border-left:solid 0px;text-align:center;" onclick="search_handler(\''+'followers:'+disp_name+'\', \'Followers\');">'+userFollowerCount+'<br> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td><td style="border-left:solid 0px;text-align:center;"><span id="userFollowingCount">'+userFollowingCount+'</span><br> <span style="color:#808080;" onclick="search_handler(\'following:'+result.items[0]['disp_name']+'\',\'Following\');" class="dark_mode_txt">Following</span></td></tr><tr><td colspan="4" style="font-size:1.2em !important;" align="center"><div style="width:100%;float:left;padding-right:4px;"><div class="follows" onclick="invite_friends();">Invite Friends</div> </div> <div style="clear:both;"></div> </td></tr></table>';
    Ext.getCmp('user_pic_header').setHtml(zz);
    Ext.getCmp('userMenuToolbar').setTitle(disp_name);
    // send to onboarding if user just registered
    if (didRegister) {
        setTutorialScreens(0);
        Ext.getCmp('tutorialCarousel').setActiveItem(0); // reset tutorial to start screen
		if (typeof device != 'undefined') {
			screen.lockOrientation('portrait');
			screen.lockOrientation(Ext.Viewport.determineOrientation());
        }
		Ext.getCmp('Tutorial').setItems([tutorialCarousel]);
        Ext.Viewport.setActiveItem('Tutorial');
        searchviewbackhack();
        custom_views_arr.push("Tutorial");
        glb_custom_views_arr_data.push([0]);

     } else {
         // try/catch needed in case we can't restart the app
         try{window.location.reload();}
         catch(err){
            Ext.Viewport.animateActiveItem("UserMenu", {
            type: 'fade',
            });
            searchviewbackhack();
            custom_views_arr.push("UserMenu");
            glb_custom_views_arr_data.push([0]);
        }
     }
    clearMask();
}

function validatePhoneNumber(dis_name, reg_email, phone_number, reg_password, check_pass, coll_name, coll_url) {
    var phoneno = /^\d{10}$/;
    if (phone_number.match(phoneno)) { 
            return RegisterUser(dis_name, reg_email, phone_number, reg_password, check_pass, coll_name, coll_url);
         }else {
            clearMask();  
            return myvAlert("Phone Number must be 10 digits.");   
        }
}

var RegisterUser = function(dis_name, reg_email, phone_number, reg_password, check_pass, coll_name, coll_url) {
    reg_password = SHA1(reg_password); 
    check_pass = SHA1(check_pass);
    var newItem = '';
    uniquify = Math.random();
    setMask('Please wait...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'register',
            dis_name: dis_name,
            reg_email: reg_email,
            reg_password: reg_password,
            token: glb_msg_token,
            check_pass: check_pass,
            coll_name: coll_name,
            coll_url: coll_url,
            token: glb_msg_token,
            uniquify: uniquify
        },
        callback: function(success, result) {
            if (result) {
                var response = result.items[0]['response'];
                if (response == 'success') {
                    family_filter = account.get('family_filter');
                    store.getProxy().clear();
                    store.load();
                    store.add({
                        email: reg_email,
                        pw: reg_password,
                        user_id: result.items[0]['user_id'],
                        disp_name: result.items[0]['disp_name'],
                        profile_photo: result.items[0]['profile_photo'],
                        family_filter: family_filter,
                        v_orientation_chk: v_orientation_chk,
                        anc1: 1,
                        video_history: glb_video_history,
                        video_history_chk: glb_video_history_chk,
                        HTML5_only: glb_HTML5_only,
                        filter_by: glb_filter_by
                    });
                    store.sync();
                    account = store.first();
                    if (phone_number) {
						Ext.util.JSONP.request({
							url: 'https://api.myvidster.com/mobile_json2.php',
                            callbackKey: 'callback',
							params: {
									type: 'phone_verify_start',
									email: account.get('email'),
                                    pkey: account.get('pw'),
                                    token: glb_msg_token,
									phone_number: phone_number,
									uniquify: uniquify
							},
							callback: function(success, result) {
									if(result.items[0].responseText === 'success') {
										clearMask();
                                        Ext.Viewport.setActiveItem("UserPhoneVerification");
                                        searchviewbackhack();
                                        custom_views_arr.push("UserPhoneVerification");
                                        glb_custom_views_arr_data.push([0]);
									}
									else {
										myvAlert('We\'re having trouble verifying your phone number. Your account was successfully created, and you may add your phone number at a later time.');
										createUserListHeaderAndTransitionToUserList(result, true);
									}
							}
						})
                    } else {
                        createUserListHeaderAndTransitionToUserList(result, true);
                    }
                } else {
                    clearMask();
                    myvAlert(response);
                }
            } else {
                clearMask();
                myvAlert('Connection to server failed');
            }
        }
    });
};

function getVerificationCode(user_id, phone_number) {
    setMask('Please wait...');
	Ext.util.JSONP.request({
		url: 'https://api.myvidster.com/mobile_json2.php',
		callbackKey: 'callback',
		params: {
				type: 'phone_verify_start',
				email: account.get('email'),
                pkey: account.get('pw'),
                token: glb_msg_token,
				phone_number: phone_number,
				uniquify: uniquify
		},
		callback: function(success, result) {
				if(result.items[0].responseText === 'success') {
					clearMask();
					Ext.Viewport.setActiveItem("UserPhoneVerification");
                     glb_existing_user_accessing_contacts = true;
                     searchviewbackhack();
                     custom_views_arr.push("UserPhoneVerification");
                     glb_custom_views_arr_data.push([0]);
				}
				else {
					clearMask();
					myvAlert('We\'re having trouble verifying your phone number. Please try again later. If the problem persists, please contact an administrator.');
				}
		}
	})
}

function save_phone(phone_number) {
	Ext.util.JSONP.request({
		url: 'https://api.myvidster.com/mobile_json2.php',
		callbackKey: 'callback',
		params: {
			type: 'save_phone',
			user_id: account.get('user_id'),
            pkey: account.get('pw'),
            token: glb_msg_token,
			phone_number: phone_number
		},
		callback: function(success, result) {
				var response = result.items[0].response;
				if (response == 'success') {
					account.set('phone_number', phone_number);
					myvAlert('Thanks for verifying your phone number!');
					if (glb_existing_user_accessing_contacts) {
					    clearMask();
					    // If user came from the 'existing user enter phone number' screen, take them to invite friends instead of their user page
					    invite_friends();
					    glb_existing_user_accessing_contacts = false;
					} else {
					    Ext.util.JSONP.request({
					        url: 'https://api.myvidster.com/mobile_json2.php',
					        callbackKey: 'callback',
					        params: {
					            type: 'login',
					            email: account.get('email'),
                                pkey: account.get('pw'),
                                token: glb_msg_token,
					            uniquify: uniquify
					        },
					        callback: function(success, result) {
					            if (result) createUserListHeaderAndTransitionToUserList(result);
					        }
					    })
					}
				} else if (response === 'Phone number already saved' || response === 'phone number already saved') {
					myvAlert(response);
					if (glb_existing_user_accessing_contacts) {
						clearMask();
						// If user came from the 'existing user enter phone number' screen, take them to invite friends instead of their user page
						invite_friends();
						glb_existing_user_accessing_contacts = false;
					} else {
						Ext.util.JSONP.request({
							url: 'https://api.myvidster.com/mobile_json2.php',
							callbackKey: 'callback',
							params: {
								type: 'login',
								email: account.get('email'),
                                pkey: account.get('pw'),
                                token: glb_msg_token,
								uniquify: uniquify
							},
							callback: function(success, result) {
								if (result) createUserListHeaderAndTransitionToUserList(result);
							}
						})
					}
				} else {
					clearMask();
					// Otherwise, let user know that there was an error and they can do it at a later point in time
					myvAlert('We\'re having trouble verifying your phone number. Your account was successfully created, and you may add your phone number at a later time.');
						Ext.util.JSONP.request({
							url: 'https://api.myvidster.com/mobile_json2.php',
							callbackKey: 'callback',
							params: {
								type: 'login',
								email: account.get('email'),
                                pkey: account.get('pw'),
                                token: glb_msg_token,
								uniquify: uniquify
						},
						callback: function(success, result) {
							if (result) createUserListHeaderAndTransitionToUserList(result);
						}
					})
				}
			}
		})
}

function verifyPhoneNumber(user_id, code, phone_number) {
    if (user_id && verification_code && phone_number) {
        setMask('Please wait...');
		Ext.util.JSONP.request({
			url: 'https://api.myvidster.com/mobile_json2.php',
			callbackKey: 'callback',
			params: {
					type: 'phone_verify_check',
					email: account.get('email'),
					pkey: account.get('pw'),
                    phone_number: phone_number,
                    token: glb_msg_token,
					code: code,
					uniquify: uniquify
			},
			callback: function(success, result) {
					if(result.items[0].responseText === 'success') {
						save_phone(phone_number)
					}
					else {
						myvAlert(result.items[0].responseText);
					}
			}
		})
    } else {
        // Continue without phone number verification
        Ext.util.JSONP.request({
            url: 'https://api.myvidster.com/mobile_json2.php',
            callbackKey: 'callback',
            params: {
                type: 'login',
                email: account.get('email'),
                pkey: account.get('pw'),
                token: glb_msg_token,
                uniquify: uniquify
            },
            callback: function(success, result) {
                if (result) createUserListHeaderAndTransitionToUserList(result);
            }
        })
    }
}

var follow_user = function(type, id, isFollow, imgSrc) {
    if (isFollow == '555') {
        unblock_user(id);
        return false;
    }
    uniquify = Math.random();
    var pkey = account.get('pw');
    var user_id = account.get('user_id');

    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'follow',
            user_id: user_id,
            pkey: pkey,
            follow_type: type,
            token: glb_msg_token,
            id: id,
            isFollow: isFollow,
            uniquify: uniquify
        },
        callback: function(success, result) {
            var response = result.items[0]['response'];
            myvAlert(response);
            if (isFollow == '0') { //0 = follow user
                if (glb_current_list['type'] == 'User') {
                    var spl_for_user = document.getElementById("spl_for_user");
                    var spl_for_user2 = document.getElementById("spl_for_user2");
                    var zz2_2 = '<div class="follows_2" onclick="follow_user(\'' + glb_current_list['type'] + '\',' + glb_current_list['id'] + ',' + '1);">Unfollow</div>  ';
                    Ext.get('special_1').setHtml(zz2_2);
                    document.getElementById("special_1").appendChild(spl_for_user);
                    document.getElementById("special_1").appendChild(spl_for_user2);
                } else {
                    var zz2 = '<div class="follows" onclick="follow_user(\'' + glb_current_list['type'] + '\',' + glb_current_list['id'] + ',' + '1);">Unfollow ' + glb_current_list['type'] + '</div>';
                    Ext.get('special_1').setHtml(zz2);
                }
            } else {
                if (glb_current_list['type'] == 'User') {
                    var spl_for_user = document.getElementById("spl_for_user");
                    var spl_for_user2 = document.getElementById("spl_for_user2");
                    var zz2_2 = '<div class="follows_2" onclick="follow_user(\'' + glb_current_list['type'] + '\',' + glb_current_list['id'] + ',' + '1);">Follow</div>  ';
                    Ext.get('special_1').setHtml(zz2_2);
                    document.getElementById("special_1").appendChild(spl_for_user);
                    document.getElementById("special_1").appendChild(spl_for_user2);
                } else {
                    var zz2 = '<div class="follows" onclick="follow_user(\'' + glb_current_list['type'] + '\',' + glb_current_list['id'] + ',' + '0);">Follow ' + glb_current_list['type'] + '</div>';
                    Ext.get('special_1').setHtml(zz2);
                }
            }
        }
    });
}

var retry_list_videos = function() {
    list_videos(glb_current_list['type'], glb_current_list['id'], 0, 1, true);
}
var glb_device_type7 = '';
var agent_mobile = (navigator.userAgent).toLowerCase().indexOf('mobile') > -1;
var agent_mobile2 = (navigator.userAgent).toLowerCase().indexOf('ipad') > -1;
if (Ext.os.is.Desktop) {
    glb_device_type7 = 'desktop';
} else if (agent_mobile && !agent_mobile2) {
    glb_device_type7 = 'phone';
} else {
    glb_device_type7 = 'tablet';
} 

var pro_sz1 = '';
var pro_btn_sz_h1 = '35px';
var pro_btn_sz_w1 = '300px';
var pro_btn_m1 = '8px';
var pro_btn_font = '';
if (glb_device_type7 == 'tablet') {
    pro_sz1 = 'font-size:1.35em;';
    pro_btn_sz_h1 = '50px';
    pro_btn_sz_w1 = '400px';
    pro_btn_m1 = '22px';
    pro_btn_font = 'font-size:1.09em;';
}
var pro_btns1 = {
    layout: {
        type: 'hbox',
        pack: 'center'
    },
    items: [{
        xtype: 'button',
        text: 'Silver (128GB $6.99/Mo)',
        style: pro_btn_font,
        width: pro_btn_sz_w1,
        height: pro_btn_sz_h1,
        margin: pro_btn_m1,
        handler: function() {
            pro_upgrade_cc(0, "new");
            glb_crypto_plan = 'silver';
        }
    }]
};

var pro_btns2 = {
    layout: {
        type: 'hbox',
        pack: 'center'
    },
    items: [{
        xtype: 'button',
        text: 'Gold (256GB $9.99/Mo)',
        style: pro_btn_font,
        width: pro_btn_sz_w1,
        height: pro_btn_sz_h1,
        margin: pro_btn_m1,
        handler: function() {
            pro_upgrade_cc(1, "new");
            glb_crypto_plan = 'gold';
        }
    }]
};

var pro_btns3 = {
    layout: {
        type: 'hbox',
        pack: 'center'
    },
    items: [{
        xtype: 'button',
        text: 'Platinum (512GB $14.99/Mo)',
        style: pro_btn_font,
        width: pro_btn_sz_w1,
        height: pro_btn_sz_h1,
        margin: pro_btn_m1,
        handler: function() {
            pro_upgrade_cc(2, "new");
            glb_crypto_plan = 'platinum';
        }
    }]
};

var pro_btns4 = {
    layout: {
        type: 'hbox',
        pack: 'center'
    },
    items: [{
        xtype: 'button',
        text: 'Silver (128GB $6.99/Mo)',
        style: pro_btn_font,
        width: pro_btn_sz_w1,
        height: pro_btn_sz_h1,
        margin: pro_btn_m1,
        handler: function() {
            pro_upgrade_cc(0, "renew");
            glb_crypto_plan = 'silver';
        }
    }]
};

var pro_btns5 = {
    layout: {
        type: 'hbox',
        pack: 'center'
    },
    items: [{
        xtype: 'button',
        text: 'Gold (256GB $9.99/Mo)',
        style: pro_btn_font,
        width: pro_btn_sz_w1,
        height: pro_btn_sz_h1,
        margin: pro_btn_m1,
        handler: function() {
            pro_upgrade_cc(1, "renew");
            glb_crypto_plan = 'gold';
        }
    }]
};

var pro_btns6 = {
    layout: {
        type: 'hbox',
        pack: 'center'
    },
    items: [{
        xtype: 'button',
        text: 'Platinum (512GB $14.99/Mo)',
        style: pro_btn_font,
        width: pro_btn_sz_w1,
        height: pro_btn_sz_h1,
        margin: pro_btn_m1,
        handler: function() {
            pro_upgrade_cc(2, "renew");
            glb_crypto_plan = 'platinum';
        }
    }]
};

 var car_func = function() {
    Ext.getCmp('sub_carousel').removeAll();
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'profile_suggestion',
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pkey: account.get('pw')
        },
        callback: function(success, result) {
            if (result) {
                Ext.getCmp('sub_carousel').setHidden(true);
                var data = result.items[0]['id'];
                var real_panels = result.items.length;
                var panels = result.items.length + 4;
                var items4 = [];
                var i = 0;
                var counter = 0;
                var html_s = "";
                for (x = 0; x < panels; x++) {
                    if (result.items[x]) {
                        html_s += '<a href="javascript:void(0);" style="margin:0px 10px 5px;" onclick="list_videos(\'Channel\',\'' + result.items[x]['channel_id'] + '\',0);"><img src="' + result.items[x]['profile_photo'] + '" height="36" width="36" border="0" style="border-radius: 50%;"></a>'
                    }

                    counter = counter + 1;
                    if (counter > 4) {
                        items4.push({
                            xtype: 'panel',
                            html: '<div style="margin:5px auto 0px;text-align:center;width:100%;height:45x;">' + html_s + '</div>'
                        });
                        html_s = "";
                        counter = 0;
                    }
                }
                Ext.getCmp('sub_carousel').setItems(items4);
            } else {
                Ext.getCmp('sub_carousel').setHidden(true);
            }
            list_videos_obj.load();
        }
    });
 }; 

var feedback_func = function(ddata, devd) {
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    if (devd == 1) {
        Ext.getCmp('devd_c2').setHidden(false);
    } else {
        Ext.getCmp('devd_c2').setHidden(true);
    }
    if (devd == 3) {
        Ext.getCmp('text_c').setValue("I would like to be upgraded beyond Platinum!");
    } else {
        Ext.getCmp('text_c').setValue("");
    }
    Ext.getCmp('ddata_c').setValue(ddata);
    Ext.getCmp('email_c').setValue(account.get("email"));
    Ext.Viewport.animateActiveItem("FeedBack", {
        type: 'fade'
    });
    searchviewbackhack();
    custom_views_arr.push("FeedBack");
    glb_custom_views_arr_data.push([0]);
}

var myFunction_plat_plus = function() {
    var user_a = navigator.userAgent;
    var ddata = 'Device Data (' + glb_device_type + " on: " + glb_version + ' | Network: ' + checkConnectionType() + '):(User ID: ' + account.get("user_id") + ')' + user_a;
    feedback_func(ddata, 3);
}

var pro_func = function() {
    var pro_sz = '';
    var pro_btn_sz_h = '35px';
    var pro_btn_sz_w = '300px';
    var pro_btn_m = '8px';
    var pro_btn_font2 = '';
    if (glb_device_type == 'tablet') {
        pro_sz = 'font-size:1.35em;';
        pro_btn_sz_h = '50px';
        pro_btn_sz_w = '400px';
        pro_btn_m = '22px';
        pro_btn_font2 = 'font-size:1.09em;';
    }
    Ext.getCmp('ProMember').removeAll();
    Ext.getCmp('user_top_seach_btn').setHidden(true);
    Ext.getCmp('clear_history_btn').setHidden(true);
    document.getElementById("auto_bill_text").innerHTML = '';

    glb_last_panel = Ext.Viewport.getActiveItem().id;

    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'check_proMember',
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pkey: account.get('pw')
        },
        callback: function(success, result) {
            if (result) {
                var auto_bill_ck1 = result.items[0]['auto_bill'];
                var is_inapp_ck1 = result.items[0]['is_inapp'];
                var is_pro_ck1 = result.items[0]['pro_mem'];
                if (!Ext.os.is('iOS')) {
                    var storage = result.items[0]['storage_left'];
                    var days = result.items[0]['days_left'];
                    var pro = result.items[0]['pro_mem'];
                    var auto_bill = result.items[0]['auto_bill'];
                    var storage_left_mb = result.items[0]['storage_left_mb'];
                    var is_inapp = result.items[0]['is_inapp'];
                    if (storage_left_mb == "" || storage_left_mb == null) {
                        storage_left_mb = 0;
                    }
                    if (storage_left_mb > 999) {
                        storage_left_mb = Math.round(storage_left_mb / 1000) + ' MBs';
                    } else {
                        storage_left_mb = storage_left_mb + ' MBs';
                    }
                    var color1 = 'green';
                    var color2 = 'green';
                    if (storage < 10) {
                        color1 = 'red';
                    }
                    if (days < 10) {
                        color2 = 'red';
                    }
                    var pro_btns_ab0 = {
                        layout: {
                            type: 'hbox',
                            pack: 'center'
                        },
                        items: [{
                            xtype: 'button',
                            id: 'pro_btns_ab0',
                            text: 'Silver (128GB $6.99/Mo)',
                            style: pro_btn_font2,
                            width: pro_btn_sz_w,
                            height: pro_btn_sz_h,
                            margin: pro_btn_m,
                            handler: function() {
                                pro_upgrade_cc_auto(0, "update_sub", auto_bill);
                                glb_crypto_plan = 'silver';
                            }
                        }]
                    };
                    var pro_btns_ab1 = {
                        layout: {
                            type: 'hbox',
                            pack: 'center'
                        },
                        items: [{
                            xtype: 'button',
                            id: 'pro_btns_ab1',
                            text: 'Gold (256GB $9.99/Mo)',
                            style: pro_btn_font2,
                            width: pro_btn_sz_w,
                            height: pro_btn_sz_h,
                            margin: pro_btn_m,
                            handler: function() {
                                pro_upgrade_cc_auto(1, "update_sub", auto_bill);
                                glb_crypto_plan = 'gold';
                            }
                        }]
                    };
                    var pro_btns_ab2 = {
                        layout: {
                            type: 'hbox',
                            pack: 'center'
                        },
                        items: [{
                            xtype: 'button',
                            id: 'pro_btns_ab2',
                            text: 'Platinum (512GB $14.99/Mo)',
                            style: pro_btn_font2,
                            width: pro_btn_sz_w,
                            height: pro_btn_sz_h,
                            margin: pro_btn_m,
                            handler: function() {
                                pro_upgrade_cc_auto(2, "update_sub", auto_bill);
                                glb_crypto_plan = 'platinum';
                            }
                        }]
                    };
                    if (auto_bill > 0) {
                        var headerItem = {
                            html: '<div style="' + pro_sz + '"><div style="text-align:center;margin:18px auto 0px;"> <p><b>- With Pro Membership you get -</b></p></div> <div style="text-align:center;margin:10px auto 0px;">Ad free browsing</div> <div style="text-align:center;margin:10px auto 0px;">No restrictions on video downloads</div> <div style="text-align:center;margin:10px auto 0px;">Up to 512 GBs of cloud storage</div> <div style="text-align:center;margin:10px auto 0px;">It\'s cheap, starting at $6.99</div>' + '<div style="text-align:center;margin:22px auto 10px;font-weight:bold;"> Autobill Pro Membership: <span style="color:green;">On</span> </div> <div style="text-align:center;margin:8px auto 10px;font-weight:bold;">Pro storage remaining:  <span style="color:' + color1 + ';">' + storage_left_mb + '</span></div> <div style="text-align:center;margin:8px auto 18px;font-weight:bold;">% of pro storage remaining:  <span style="color:' + color1 + ';">' + storage + '%</span></div>  <br/></div>'
                        };
                    } else {
                        var headerItem = {
                            html: '<div style="' + pro_sz + '"><div style="text-align:center;margin:18px auto 0px;"> <p><b>- With Pro Membership you get -</b></p></div> <div style="text-align:center;margin:10px auto 0px;">Ad free browsing</div> <div style="text-align:center;margin:10px auto 0px;">No restrictions on video downloads</div> <div style="text-align:center;margin:10px auto 0px;">Up to 512 GBs of cloud storage</div> <div style="text-align:center;margin:10px auto 0px;">It\'s cheap, starting at $6.99</div>' + '<div style="text-align:center;margin:22px auto 10px;font-weight:bold;">Membership days remaining: <span style="color:' + color2 + ';">' + days + '</span></div> <div style="text-align:center;margin:8px auto 10px;font-weight:bold;">Pro storage remaining:  <span style="color:' + color1 + ';">' + storage_left_mb + '</span></div> <div style="text-align:center;margin:8px auto 18px;font-weight:bold;">% of pro storage remaining:  <span style="color:' + color1 + ';">' + storage + '%</span></div>  <br/></div>'
                        };
                    }
                    var pro_opt = pro - 1; // hack
                    var pro_btns0 = {
                        layout: {
                            type: 'hbox',
                            pack: 'center'
                        },
                        items: [{
                            xtype: 'button',
                            text: 'Renew',
                            style: pro_btn_font2,
                            width: pro_btn_sz_w,
                            height: pro_btn_sz_h,
                            margin: pro_btn_m,
                            handler: function() {
                                pro_upgrade_cc(pro_opt, "renew")
                            }
                        }]
                    };
                    var pro_btns10 = {
                        layout: {
                            type: 'hbox',
                            pack: 'center'
                        },
                        items: [{
                            xtype: 'button',
                            text: 'Cancel Membership',
                            style: pro_btn_font2,
                            width: pro_btn_sz_w,
                            height: pro_btn_sz_h,
                            margin: pro_btn_m,
                            ui: 'decline',
                            handler: function() {
                                if (typeof device != 'undefined') {
                                    navigator.notification.confirm('Click yes to confirm.',
                                        function(buttonIndex) {
                                            if (buttonIndex == 1) cancel_pro(auto_bill, "cancel");
                                        }, 'Cancel pro auto billing?', 'yes,cancel');
                                } else {
                                    var msg = new Ext.MessageBox({
                                        width: 300,
                                        floating: 1,
                                        margin: '80 0 0 0'
                                    });
                                    msg.show({
                                        title: 'Cancel pro auto billing?',
                                        //msg: 'Select an option.',
                                        buttons: [{
                                            text: 'yes',
                                            itemId: 'yes'
                                        }, {
                                            text: 'cancel',
                                            itemId: 'cancel'
                                        }],
                                        fn: function(response) {
                                            if (response == 'yes') {
                                                cancel_pro(auto_bill, "cancel");
                                            } else if (response == 'cancel') {

                                            }
                                        }
                                    });
                                }
                            }
                        }]
                    };
                    Ext.getCmp('ProMember').add(headerItem);
                    if (pro > 0) { 
                        if (auto_bill > 0) { 
                            // add features for auto bill user
                            var add_html = {
                                html: "<div style='width:100%;text-align:center;'>Change to</div>"
                            };
                            Ext.getCmp('ProMember').add(add_html);
                            if (pro != 1) {
                                Ext.getCmp('ProMember').add(pro_btns_ab0);
                            }
                            if (pro != 2) {
                                Ext.getCmp('ProMember').add(pro_btns_ab1);
                            }
                            if (pro != 3) {
                                Ext.getCmp('ProMember').add(pro_btns_ab2);
                            }
                            if (pro == 1) {
                                Ext.getCmp('ProMember').add(pro_btns_ab0);
                                Ext.getCmp('pro_btns_ab0').setText('Edit Membership');
                            }
                            if (pro == 2) {
                                Ext.getCmp('ProMember').add(pro_btns_ab1);
                                Ext.getCmp('pro_btns_ab1').setText('Edit Membership');
                            }
                            if (pro == 3) {
                                Ext.getCmp('ProMember').add(pro_btns_ab2);
                                Ext.getCmp('pro_btns_ab2').setText('Edit Membership');
                            }
                            var add_html2 = {
                                html: "<div style='width:80%;text-align:center;margin-top:10px;margin-bottom:30px;margin-left:auto;margin-right:auto;'>Already have Platinum and need more storage space? Please <span style='color:blue;' onclick='myFunction_plat_plus()'>contact</span> us!</div>"
                            }
                            Ext.getCmp('ProMember').add(add_html2);
                            Ext.getCmp('ProMember').add(pro_btns10); // also unhide radio 

                        } else {
                            Ext.getCmp('ProMember').add(pro_btns0);
                            var add_html = {
                                html: "<div style='width:100%;text-align:center;'>Or Increase Your Quota</div>"
                            };
                            Ext.getCmp('ProMember').add(add_html);
                            Ext.getCmp('ProMember').add(pro_btns1);
                            Ext.getCmp('ProMember').add(pro_btns2);
                            Ext.getCmp('ProMember').add(pro_btns3);
                        }
                    } else {
                        Ext.getCmp('ProMember').add(pro_btns1);
                        Ext.getCmp('ProMember').add(pro_btns2);
                        Ext.getCmp('ProMember').add(pro_btns3);
                    }
                    Ext.getCmp('userListToolbar2').setTitle("Pro Membership");
                    Ext.Viewport.animateActiveItem("ProMember", {
                        type: 'fade',
                    });
                    searchviewbackhack();
                    custom_views_arr.push("ProMember");
                    var array_data = [0];
                    glb_custom_views_arr_data.push(array_data);
                } else {
                    // ios only  //
                    var storage = result.items[0]['storage_left'];
                    var days = result.items[0]['days_left'];
                    var pro = result.items[0]['pro_mem'];
                    var auto_bill = result.items[0]['auto_bill'];
                    var storage_left_mb = result.items[0]['storage_left_mb'];
                    var is_inapp = result.items[0]['is_inapp'];
                    var html_btns = '<div style="width:100%;text-align:center;"> <div id="pro_1" style="width:80%;margin:10px auto 20px;background-color:dodgerblue;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;" onclick="ios_upgrade(1)"> Silver (128GB $6.99/mo) </div> <div id="pro_2" style="width:80%;margin:10px auto 20px;background-color:dodgerblue;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;" onclick="ios_upgrade(2)"> Gold (256GB $9.99/mo) </div> <div id="pro_3" style="width:80%;margin:10px auto 20px;background-color:dodgerblue;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;" onclick="ios_upgrade(3)"> Platinum (512GB $14.99/mo) </div> <div id="restore_btn" style="width:80%;margin:10px auto 20px;background-color:#12D12D;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;" onclick="restore_purchase()"> Restore Purchases </div> <br/>     <div style="padding:10px 2px;text-align:center;"> MyVidster <a onclick="load_webpage(\'' + 'https://www.myvidster.com/docs/tos' + '\', true);" style="color:blue;">terms</a> and <a onclick="load_webpage(\'' + 'https://www.myvidster.com/docs/privacy' + '\', true);" style="color:blue;">privacy</a> policy  </div>        </div>';
                    if (pro == 1) {
                        html_btns = '<div style="width:100%;text-align:center;">Change to</div> <div style="width:100%;'+
                        'text-align:center;">  <div id="pro_2" style="width:80%;margin:10px auto 20px;background-color:'+
                        'dodgerblue;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;'+
                        'font-family:helvetica;" onclick="ios_upgrade(2)"> Gold (256GB $9.99/mo) </div> <div id="pro_3" '+
                        'style="width:80%;margin:10px auto 20px;background-color:dodgerblue;border-radius:5px;padding:12px'+
                        ' 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;" onclick="ios_upgrade(3)"'+
                        '> Platinum (512GB $14.99/mo) </div> <div id="pro_1" style="width:80%;margin:10px auto 20px;'+
                        'background-color:dodgerblue;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;'+
                        'font-weight:bold;font-family:helvetica;" onclick="ios_upgrade(1)"> Edit Membership </div> '+
                        '  <div id="restore_btn" style="width:80%;margin:10px auto 20px;background-color:#12D12D;'+
                        'border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:'+
                        'helvetica;" onclick="restore_purchase()"> Restore Purchases </div> <br/>    <div style="padding:'+
                        '10px 2px;text-align:center;"> MyVidster <a onclick="load_webpage(\'' +
                        'https://www.myvidster.com/docs/tos' + '\', true);" style="color:blue;">terms</a> and <a '+
                        'onclick="load_webpage(\'' + 'https://www.myvidster.com/docs/privacy' + '\', true);" style="color:'+
                        'blue;">privacy</a> policy </div>     </div>';
                    }
                    if (pro == 2) {
                        html_btns = '<div style="width:100%;text-align:center;">Change to</div> <div style="width:100%;'+
                        'text-align:center;">  <div id="pro_1" style="width:80%;margin:10px auto 20px;background-color:'+
                        'dodgerblue;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;'+
                        'font-family:helvetica;" onclick="ios_upgrade(1)"> Silver (128GB $6.99/mo) </div>  <div id="pro_3" '+
                        'style="width:80%;margin:10px auto 20px;background-color:dodgerblue;border-radius:5px;padding:'+
                        '12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;" '+
                        'onclick="ios_upgrade(3)"> Platinum (512GB $14.99/mo) </div> <div id="pro_2" style="width:80%;'+
                        'margin:10px auto 20px;background-color:dodgerblue;border-radius:5px;padding:12px 0px;'+
                        'font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;" onclick="ios_upgrade(2)"> '+
                        'Edit Membership </div> <div id="restore_btn" style="width:80%;margin:10px auto 20px;'+
                        'background-color:#12D12D;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;'+
                        'font-weight:bold;font-family:helvetica;" onclick="restore_purchase()"> Restore Purchases </div>'+
                        ' <br/>    <div style="padding:10px 2px;text-align:center;"> MyVidster <a '+
                        'onclick="load_webpage(\'' + 'https://www.myvidster.com/docs/tos' + '\', true);'+
                        '" style="color:blue;">terms</a> and <a onclick="load_webpage(\'' +
                        'https://www.myvidster.com/docs/privacy' + '\', true);" style="color:blue;">privacy</a> policy </div>     </div>';
                    }
                    if (pro == 3) {
                        html_btns = '<div style="width:100%;text-align:center;">Change to</div> <div style="width:100%;'+
                        'text-align:center;"> <div id="pro_1" style="width:80%;margin:10px auto 20px;background-color:dodgerblue;'+
                        'border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;"'+
                        ' onclick="ios_upgrade(1)"> Silver (128GB $6.99/mo) </div> <div id="pro_2" style="width:80%;margin:10px'+
                        ' auto 20px;background-color:dodgerblue;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;'+
                        'font-weight:bold;font-family:helvetica;" onclick="ios_upgrade(2)"> Gold (256GB $9.99/mo) </div>'+
                        '  <div id="pro_3" style="width:80%;margin:10px auto 20px;background-color:dodgerblue;border-radius:5px;'+
                        'padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica;"'+
                        ' onclick="ios_upgrade(3)"> Edit Membership </div> <div id="restore_btn" style="width:80%;margin:10px auto '+
                        '20px;background-color:#12D12D;border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;'+
                        'font-weight:bold;font-family:helvetica;" onclick="restore_purchase()"> Restore Purchases </div> <br/> '+
                        '      <div style="padding:10px 2px;text-align:center;"> MyVidster <a onclick="load_webpage(\'' +
                        'https://www.myvidster.com/docs/tos' + '\', true);" style="color:blue;">terms</a> </div> and <a'+
                        ' onclick="load_webpage(\'' + 'https://www.myvidster.com/docs/privacy' +
                        '\', true);" style="color:blue;">privacy</a> policy   </div>';
                    }
                    if (Number(glb_version) < Number("7.30")) {
                        html_btns = '<div style="margin:0px auto 0px; width:80%;"> Apple now requires us to use their in-app purchasing for PRO membership upgrades and renewals. We are working hard to provide this functional soon. In the meanwhile you can visit <a onclick="load_webpage(\'' + 'https://www.myvidster.com/user/upgrade.php' + '\', true);" style="color:blue;text-decoration:none;">myvidster.com</a> to manage and upgrade your PRO membership. </div>';
                    }
                    if (storage_left_mb == "" || storage_left_mb == null) {
                        storage_left_mb = 0;
                    }
                    if (storage_left_mb > 999) {
                        storage_left_mb = Math.round(storage_left_mb / 1000) + ' MBs';
                    } else {
                        storage_left_mb = storage_left_mb + ' MBs';
                    }
                    var color1 = 'green';
                    var color2 = 'green';
                    var msg_txt = 'All Subscriptions are for 1 month periods, and are priced at $6.99, $9.99, and $14.99 for the 3 tiers (silver - 128GB cloud storage, gold - 256GB cloud storage, platinum - 512GB cloud storage). Subscriptions automatically renew unless auto-renew is turned off at least 24-hours before the end of the current period. Your account will be charged for renewal within 24-hours prior to the end of the current period, and identify the cost of the renewal. Subscriptions may be managed by the user and auto-renewal may be turned off by going to the user\'s Account Settings after purchase. ';
                    if (storage < 10) {
                        color1 = 'red';
                    }
                    if (days < 10) {
                        color2 = 'red';
                    }
                    if (auto_bill > 0) {
                        var headerItem = {
                            html: '<div style="' + pro_sz + '"><div style="text-align:center;margin:18px auto 0px;"> <p><b>- With Pro Membership you get -</b></p></div> <div style="text-align:center;margin:10px auto 0px;">Ad free browsing</div> <div style="text-align:center;margin:10px auto 0px;">No restrictions on video downloads</div> <div style="text-align:center;margin:10px auto 0px;">Up to 512 GBs of cloud storage</div> <div style="text-align:center;margin:10px auto 0px;">It\'s cheap, starting at $6.99</div>' + ' <div style="text-align:center;margin:8px auto 10px;font-weight:bold;">Pro storage remaining:  <span style="color:' + color1 + ';">' + storage_left_mb + '</span></div> <div style="text-align:center;margin:8px auto 18px;font-weight:bold;">% of pro storage remaining:  <span style="color:' + color1 + ';">' + storage + '%</span></div>   <div style="text-align:center;margin:8px auto 10px;" onclick="iap_desc_alert()"> <span style="border-bottom:1px solid;">Payment Policy</span> </div> <div style="text-align:center;margin:8px auto 10px;width:88%;display:block;font-size:.9em;" id="reveal_iap">' + msg_txt + '</div>  <br/></div>'
                        };
                    } else {
                        var headerItem = {
                            html: '<div style="' + pro_sz + '"><div style="text-align:center;margin:18px auto 0px;"> <p><b>- With Pro Membership you get -</b></p></div> <div style="text-align:center;margin:10px auto 0px;">Ad free browsing</div> <div style="text-align:center;margin:10px auto 0px;">No restrictions on video downloads</div> <div style="text-align:center;margin:10px auto 0px;">Up to 512 GBs of cloud storage</div> <div style="text-align:center;margin:10px auto 0px;">It\'s cheap, starting at $6.99</div>' + ' <div style="text-align:center;margin:8px auto 10px;font-weight:bold;">Pro storage remaining:  <span style="color:' + color1 + ';">' + storage_left_mb + '</span></div> <div style="text-align:center;margin:8px auto 18px;font-weight:bold;">% of pro storage remaining:  <span style="color:' + color1 + ';">' + storage + '%</span></div>   <div style="text-align:center;margin:8px auto 10px;" onclick="iap_desc_alert()"> <span style="border-bottom:1px solid;">Payment Policy</span> </div> <div style="text-align:center;margin:8px auto 10px;width:88%;display:block;font-size:.9em;" id="reveal_iap">' + msg_txt + '</div>  <br/></div>'
                        };
                    }
                    Ext.getCmp('ProMember').add(headerItem);
                    var headerItem44 = {
                        // ios_upgrade
                        html: html_btns
                    };
                    Ext.getCmp('ProMember').add(headerItem44);
                    Ext.getCmp('userListToolbar2').setTitle("Pro Membership");
                    Ext.Viewport.animateActiveItem("ProMember", {
                        type: 'fade',
                    });
                    searchviewbackhack();
                    custom_views_arr.push("ProMember");
                    glb_custom_views_arr_data.push([0]);
                }
            } else {
            }
            //clears mask for pro membership screen
            clearMask(); 
        }
    });
}

var get_profile = function(id, activeView) {
    var limit = 9;
    if (glb_device_type == 'tablet') {
        limit = 15;
    }
    glb_current_list['type'] = 'User';
    glb_current_list['id'] = id;
    glb_current_list['start'] = 0;
    list_videos_obj.getProxy().setExtraParams({
        type: 'User',
        id: id,
        user_id: account.get('user_id'),
        pkey: account.get('pw'),
        start: 0,
        device_type: glb_device_type,
        token: glb_msg_token,
        limit: limit,
        version: glb_version,
        family_filter: family_filter,
        html5: '1',
        q: '',
        uniquify: Math.random()
    });
    glb_last_panel = activeView;
    list_videos_obj.load({
        callback: function(){
            clearMask();
        }
    });
    document.getElementById('user_top_channelcollection_btn').style.visibility = 'hidden';   
}

var list_videos = function(type, id, start, isRefresh, retry, q) {
   if (glb_current_list['reset'] != 1) {
        if (glb_current_list['type']) {
            glb_back_data['type'] = glb_current_list['type'];
            glb_back_data['id'] = glb_current_list['id'];
        } else {
            glb_back_data['type'] = type;
            glb_back_data['id'] = id;
        }
    } else {
        glb_back_data['type'] = '';
        glb_back_data['id'] = '';
        glb_current_list['reset'] = 0;
    }
    if (type == 'History_V2') {
        var video_history = glb_video_history.slice(0);
        id = video_history.reverse().join(",");
    }
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    if (type == 'User' || type == 'collectors_v2') {
        if(isRefresh == 'nocoll2'){
            isRefresh = '';
        }else{
            setMask('Loading...');
            setTimeout(function() { clearMask();}, 2500);
        }
    }
    if (type == glb_current_list['type'] && id == glb_current_list['id']) {
        //force a refresh during downloading and for collectors list
        if (type != 'Download' && !glb_downloading && type != 'collectors') {
            glb_last_list['type'] = glb_last_list['bc_type']; 
            glb_last_list['id'] = glb_last_list['bc_id'];

            if (glb_last_panel == 'Video' || glb_last_panel == 'Video2' || glb_last_panel == 'MsgUser') {
                Ext.Viewport.animateActiveItem("UserList", {
                    type: 'fade',
                }); 
                if(glb_seb_back == 0){
                searchviewbackhack();
                custom_views_arr.push("UserList");
                var array_data = [type, id, start, isRefresh, retry, q];
                glb_custom_views_arr_data.push(array_data);
                }else{
                    glb_seb_back = 0;
                }
            }    
            else { 
               Ext.Viewport.animateActiveItem("UserList", {
                    type: 'fade',
                });
                if(glb_seb_back == 0){
                searchviewbackhack();
                custom_views_arr.push("UserList");
                var array_data = [type, id, start, isRefresh, retry, q];
                glb_custom_views_arr_data.push(array_data);
                }else{
                    glb_seb_back = 0;
                }
            }
            return true;
        }
    } else {
        glb_current_list['type'] = type;
        glb_current_list['id'] = id;
        glb_current_list['q'] = q;
        glb_current_list['start'] = start;
    }
    var pkey = account.get('pw');
    var user_id = account.get('user_id');
    var limit = 10;
    if (glb_device_type == 'tablet') {
        limit = 15;
    }
    // seb 2020
    Ext.getCmp('list_videos_var').setStore(dummy_obj);
    list_videos_obj.removeAll();
    list_videos_obj.currentPage = 1;
    list_videos_obj.getProxy().setExtraParams({
        type: type,
        id: id,
        user_id: account.get('user_id'),
        pkey: account.get('pw'),
        token: glb_msg_token,
        start: start,
        device_type: glb_device_type,
        limit: limit,
        version: glb_version,
        family_filter: family_filter,
        html5: glb_HTML5_only,
        q: q,
        uniquify: Math.random()
    });
    
    if (type == 'subscriptions') {
        car_func();
        document.getElementById('user_top_channelcollection_btn').style.visibility = 'hidden';  
        Ext.getCmp('list_videos_var').setStore(list_videos_obj);
    } else {
        Ext.getCmp('sub_carousel').setHidden(true);
        list_videos_obj.load({
            callback: function(){
                Ext.getCmp('list_videos_var').setStore(list_videos_obj);
                 if(type == 'Profile' || type == 'Queue' || type == 'Channel' && glb_profile_check == 1){
                    document.getElementById('user_top_seach_btn').style.visibility = 'visible';
                    document.getElementById('user_top_channelcollection_btn').style.setProperty ("display", "none", "important");
                }else{
                    document.getElementById('user_top_seach_btn').style.visibility = 'hidden';
                    document.getElementById('user_top_channelcollection_btn').style.setProperty ("display", "", "important");
                }
                if (typeof device != 'undefined' || Ext.os.is.iOS) {
                    glb_addbtn_fix2 = 1;
                }else{
                    glb_addbtn_fix2 = 0;
                }
                 if(type == 'Collection' && glb_addbtn_fix == 1 && glb_addbtn_fix2 == 1 || type == 'User' && glb_addbtn_fix == 1 && glb_addbtn_fix2 == 1){
                    glb_gallery_id = id; 
                    glb_top_channelcollection_btn = type;
                    document.getElementById('user_top_channelcollection_btn').style.visibility = 'visible';
                    document.getElementById('user_top_seach_btn').style.setProperty ("display", "none", "important");

                }else{
                    document.getElementById('user_top_channelcollection_btn').style.visibility = 'hidden';   
                    document.getElementById('user_top_seach_btn').style.setProperty ("display", "", "important"); 
                }
                clearMask();
            } 
       });
    } 
    if(type == 'subscriptions' || type == 'History_V2' || type == 'collectors_v2'){
        Ext.getCmp('search_bar_userlistholder').setHidden(true);
    }else{
        Ext.getCmp('search_bar_userlistholder').setHidden(false);
    }
    

}

// this is used in Search view 
function create_search_opt() {
    var search_opt = new Array();
    search_opt[0] = {
        text: 'Users',
        value: 'profiles'
    };
    search_opt[1] = {
        text: 'MyVidster',
        value: 'myVidster'
    };
    search_opt[2] = {
        text: 'Web',
        value: 'web'
    };
    Ext.getCmp('search_opt').setOptions(search_opt);
    Ext.getCmp('search_opt').setValue('myVidster');
}
var video_search = function(q, page, type, id, list_name) {
    glb_scope = type;
    glb_scope_id = id;
    glb_list_name = list_name;
    if(glb_seb_back == 0){
        searchviewbackhack();
        custom_views_arr.push("Search");
        var array_data = [String(q), page, type, id, list_name];
        glb_custom_views_arr_data.push(array_data);
    
        search_back_preserve_hack = 0;
    }else{
        glb_seb_back = 0;
    }
    if (type != 'profiles' && type != 'web' && type != 'subscription_list') {
        Ext.getCmp('search_opt2').show();
    } else {
        Ext.getCmp('search_opt2').hide();
    }
    if (glb_current_list['reset'] != 1) {
        glb_back_data['type'] = glb_current_list['type'];
        glb_back_data['id'] = glb_current_list['id'];
    } else {
        glb_back_data['type'] = '';
        glb_back_data['id'] = '';
        glb_current_list['reset'] = 0;
    }
    if (Ext.Viewport.getActiveItem().id != 'Search') {
        glb_last_panel = Ext.Viewport.getActiveItem().id;
    }
    var search_opt = new Array();
    var z = 0;
    Ext.getCmp('search_bar').setValue(q);
    if (type != 'myVidster' && type != 'web' && type != 'profiles') {
        search_opt[z++] = {
            text: list_name,
            value: type
        };
    }
    search_opt[z++] = {
        text: 'Users',
        value: 'profiles'
    };
    search_opt[z++] = {
        text: 'MyVidster',
        value: 'myVidster'
    };
    search_opt[z++] = {
        text: 'Web',
        value: 'web'
    };
    Ext.getCmp('search_opt').setOptions(search_opt);
    Ext.getCmp('search_opt').setValue(type);
    if (type == 'web') {
        var url = "https://www.myvidster.com/web_search.php";
        search_obj.getProxy().setUrl(url);
        filter_by = glb_filter_by;
    } else { 
        var url = "https://www.myvidster.com/moblie_search.php";
        search_obj.getProxy().setUrl(url);
        filter_by = type;
    }
    if (q) {
        setMask("Searching...");
        if (type == 'Downloads') {
            Ext.getCmp('search_var').setItemTpl(download_listTpl);
        } else if (type == 'web') {
            Ext.getCmp('search_var').setItemTpl(external_listTpl);
        } else if (type == 'profiles') {
            Ext.getCmp('search_var').setItemTpl(profiles_listTpl);
        } else if (type == 'subscription_list') {
            Ext.getCmp('search_var').setItemTpl(channel_listTpl);
        } else {
            Ext.getCmp('search_var').setItemTpl(recent_listTpl);
        }
        var limit = 9;
        if (glb_device_type == 'tablet') {
            limit = 15;
        }
        search_obj.getProxy().setExtraParams({
            q: q,
            filter_by: filter_by,
            cfilter_by: glb_filter_by,
            id: id,
            family_filter: family_filter,
            scope: glb_scope,
            device_type: glb_device_type,
            sortby: glb_search_sort,
            limit: limit,
            html5: glb_HTML5_only,
            user_id: account.get('user_id'),
            pkey: account.get('pw'),
            token: glb_msg_token,
            uniquify: Math.random()
        });

        glb_current_list['page'] = page;

        if (page == 1) {
            search_obj.removeAll();
            glb_search_page = 2;
        } else {
            glb_search_page += 1;
        }
        search_obj.currentPage = 1;
        search_obj.load({
            callback: function(){ clearMask(); }
        });
        clearMask();
        if (typeof device != 'undefined' && typeof window.ga != 'undefined') window.ga.trackView('/mobile/search/');
    } else {}
};

function push_func_start() {
    if (typeof PushNotification != 'undefined' && typeof device != 'undefined') {
		 push = PushNotification.init({
            "android": {
            },
            browser: {
                pushServiceURL: 'https://push.api.phonegap.com/v1/push'
            },
            "ios": {
                "alert": "true",
                "badge": "true",
                "sound": "true"
            },
            "windows": {}
        });
        if (glb_push != 1) {
            push.unregister(function() {
            }, function() {});
        }
        push.on('registration', function(data) {
            if (glb_push != 1) {
                push.unregister(function() {

                }, function() {
                });
            }
            var device_type = "";
            if (Ext.os.is('Android')) {
                device_type = "android";
            } else if (Ext.os.is('iOS')) {
                device_type = "ios";
            }

            if (account.get('user_id')) {
                Ext.util.JSONP.request({
                    url: 'https://api.myvidster.com/mobile_json2.php',
                    callbackKey: 'callback',
                    params: {
                        type: 'device_type',
                        user_id: account.get('user_id'),
                        token: glb_msg_token,
                        pkey: account.get('pw'),
                        device: device_type,
                        reg_id: data.registrationId
                    },
                    callback: function(success, result) {
                    }
                });
            }

        });

        push.on('notification', function(data) {
            push.setApplicationIconBadgeNumber(function() {
            }, function() {
            }, data.additionalData['count']);
            if (Ext.os.is.Android) {
                update_tab_badge(data.count);
            } else {
              update_tab_badge(data.additionalData['count']);
            }
            if (data.additionalData['foreground'] == false) {
                glb_push_coldstart = 1;
            }
            // notifications active here
            if (glb_alert == 1 && glb_alert_active == 0 && data.additionalData['foreground'] == true && data.additionalData['from_user_id'] != glb_current_messaging && data.additionalData['from_user_id'] != account.get('user_id')) {
                if (Ext.os.is.Android) {
                    update_tab_badge(data.count);
                } else {
                  update_tab_badge(data.additionalData['count']);
                }
                navigator.vibrate(500);
                list_msgs_obj.removeAll();
                list_msgs_obj.currentPage = 1;
                list_msgs_obj.load();
            }
            if (glb_push == 1 &&
                data.additionalData['push'] == 1 &&
                data.additionalData['to_user_id'] == account.get('user_id') &&
                data.additionalData['coldstart'] == true && data.additionalData['from_user_id'] != account.get('user_id') &&
                data.additionalData['foreground'] == false) {
                msg_a_user(data.additionalData['from_user_id'], data.additionalData['chat_user_name'], 0);
                navigator.vibrate(500);
                setTimeout(function() {
                    read_msg(account.get('user_id'), account.get('pw'), data.additionalData['from_user_id'], 1);
                }, 5000);

            } else {}
            glb_push_coldstart = 0;
            push.finish(function() {});
        });
        push.on('error', function(e) {});
    }
}

function load_app_data() {
    if (typeof device != 'undefined') {
        cordova.getAppVersion(function(version) {
            glb_version = version;
            if (glb_device_type == 'tablet') {
                Ext.getCmp('v_orientation_chk').setHidden(false);
            }
            if (Ext.os.is('iOS') && Number(glb_version) < Number("7.20")) {
                Ext.getCmp('v_orientation_chk').hide();
            }
            if(account && account.get('email')){ 
                var prof_pic_sz = 100;
                var text_sz = '';
                if (glb_device_type == 'tablet') {
                    prof_pic_sz = 130;
                    text_sz = 'font-size:1.4em; font-weight:bold;'
                }
                var video_count = glb_login_video_count;
                if (video_count == null) {
                    video_count = '0';
                }
                userFollowerCount = glb_login_follower_count;
                if (userFollowerCount == null) {
                    userFollowerCount = '0';
                }
               userFollowingCount = glb_login_following_count;
                if (userFollowingCount == null) {
                    userFollowingCount = '0';
                }

                if (account.get('profile_photo')) {
                    var profile_photo = account.get('profile_photo') + '&mobile=' + Math.random();
                    preload(profile_photo);
                }
                if (Ext.os.is('iOS') && Number(glb_version) < Number("7.35")) {
                    zzz = '<table border="0" class="new_table"><tr><td rowspan="2" style="text-align:left;padding-left:15px;"><div style="position:relative;width:' + prof_pic_sz + 'px;"> <img onclick="upload_photo();" src="' + profile_photo + '" height="' + prof_pic_sz + '" width="' + prof_pic_sz + '" border="0" style="border-radius: 50%;"> </div></td><td height="50" style="text-align:center;">' + video_count + '<br><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td><td style="border-left:solid 0px;text-align:center;" onclick="search_handler(\'' + 'followers:' + glb_login_disp_name + '\');">' + userFollowerCount + '<br> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td><td style="border-left:solid 0px;text-align:center;"><span id="userFollowingCount">' + userFollowingCount + '</span><br> <span style="color:#808080;" onclick="search_handler(\'following:'+glb_login_disp_name+'\');" class="dark_mode_txt">Following</span></td></tr><tr><td colspan="4" style="font-size:1.2em !important;" align="center"><div style="width:100%;float:left;padding-right:4px;">&nbsp;</div> <div style="clear:both;"></div> </td></tr></table>';
                } else {
                    zzz = '<table border="0" class="new_table"><tr><td rowspan="2" style="text-align:left;padding-left:15px;"><div style="position:relative;width:' + prof_pic_sz + 'px;"> <img onclick="upload_photo();" src="' + profile_photo + '" height="' + prof_pic_sz + '" width="' + prof_pic_sz + '" border="0" style="border-radius: 50%;"> </div></td><td height="50" style="text-align:center;">' + video_count + '<br><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td><td style="border-left:solid 0px;text-align:center;" onclick="search_handler(\'' + 'followers:' + glb_login_disp_name + '\');">' + userFollowerCount + '<br> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td><td style="border-left:solid 0px;text-align:center;"><span id="userFollowingCount">' + userFollowingCount + '</span><br> <span style="color:#808080;" onclick="search_handler(\'following:'+glb_login_disp_name+'\');" class="dark_mode_txt">Following</span></td></tr><tr><td colspan="4" style="font-size:1.2em !important;" align="center"><div style="width:100%;float:left;padding-right:4px;"><div class="follows" onclick="invite_friends();">Invite Friends</div> </div> <div style="clear:both;"></div></td></tr></table>';
                }
                Ext.getCmp('user_pic_header').setHtml(zzz);
            }
        });
    }else{
        if(account && account.get('email')){ 
            var prof_pic_sz = 100;
            var text_sz = '';
            if (glb_device_type == 'tablet') {
                prof_pic_sz = 130;
                text_sz = 'font-size:1.4em; font-weight:bold;'
            }
            var video_count = glb_login_video_count;
            if (video_count == null) {
                video_count = '0';
            }
            userFollowerCount = glb_login_follower_count;
            if (userFollowerCount == null) {
                userFollowerCount = '0';
            }
           userFollowingCount = glb_login_following_count;
            if (userFollowingCount == null) {
                userFollowingCount = '0';
            }
            if (account.get('profile_photo')) {
                var profile_photo = account.get('profile_photo') + '&mobile=' + Math.random();
                preload(profile_photo);
            }
            if (Ext.os.is('iOS') && Number(glb_version) < Number("7.35")) {
                zzz = '<table border="0" class="new_table"><tr><td rowspan="2" style="text-align:left;padding-left:15px;"><div style="position:relative;width:' + prof_pic_sz + 'px;"> <img onclick="upload_photo();" src="' + profile_photo + '" height="' + prof_pic_sz + '" width="' + prof_pic_sz + '" border="0" style="border-radius: 50%;"> </div></td><td height="50" style="text-align:center;">' + video_count + '<br><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td><td style="border-left:solid 0px;text-align:center;" onclick="search_handler(\'' + 'followers:' + glb_login_disp_name + '\');">' + userFollowerCount + '<br> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td><td style="border-left:solid 0px;text-align:center;"><span id="userFollowingCount">' + userFollowingCount + '</span><br> <span style="color:#808080;" onclick="search_handler(\'following:'+glb_login_disp_name+'\');" class="dark_mode_txt">Following</span></td></tr><tr><td colspan="4" style="font-size:1.2em !important;" align="center"><div style="width:100%;float:left;padding-right:4px;">&nbsp;</div> <div style="clear:both;"></div> </td></tr></table>';
            } else {
                zzz = '<table border="0" class="new_table"><tr><td rowspan="2" style="text-align:left;padding-left:15px;"><div style="position:relative;width:' + prof_pic_sz + 'px;"> <img onclick="upload_photo();" src="' + profile_photo + '" height="' + prof_pic_sz + '" width="' + prof_pic_sz + '" border="0" style="border-radius: 50%;"> </div></td><td height="50" style="text-align:center;">' + video_count + '<br><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td><td style="border-left:solid 0px;text-align:center;" onclick="search_handler(\'' + 'followers:' + glb_login_disp_name + '\');">' + userFollowerCount + '<br> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td><td style="border-left:solid 0px;text-align:center;"><span id="userFollowingCount">' + userFollowingCount + '</span><br> <span style="color:#808080;" onclick="search_handler(\'following:'+glb_login_disp_name+'\');" class="dark_mode_txt">Following</span></td></tr><tr><td colspan="4" style="font-size:1.2em !important;" align="center"><div style="width:100%;float:left;padding-right:4px;"><div class="follows" onclick="invite_friends();">Invite Friends</div> </div> <div style="clear:both;"></div></td></tr></table>';
            }
            Ext.getCmp('user_pic_header').setHtml(zzz);
        }
    }


    store.load();
    if (store.getCount() == 0) {
        store.add({
            family_filter: 1,
            v_orientation_chk: v_orientation_chk,
            video_history_chk: 1,
            HTML5_only: glb_HTML5_only
        });
        store.sync();
    }
    account = store.first();
    app_store.load();
    if (app_store.getCount() == 0) {
        app_store.add({
            lock_screen: 0,
            alerts_set: 0,
            push_set: 0,
            default_html5_playback: 1,
            show_hide_comments: 0,
            play_playlist: 0,
            chrome_cast_setting: 0,
            darkTheme: 0,
            videoCall: 0,
            defaultAccess: 0
        });
        app_store.sync();
    }
    application = app_store.first();
    if (typeof device == 'undefined') {
        Ext.getCmp('v_orientation_chk').hide();
       Ext.getCmp('default_html5_playback').hide();
    }
    if (!Ext.os.is('Android')) {
        Ext.getCmp('default_html5_playback').hide();
    }
    if (glb_device_type !== 'tablet') {
        Ext.getCmp('v_orientation_chk').hide();
        Ext.getCmp('v_orientation_chk').setHidden(true);
        account.set("v_orientation_chk", 1);
        localStorage.setItem('v_orientation_chk', 1);
        account.setDirty();
        store.sync();
        account = store.first();
    }
    preload("https://cdn2.myvidster.com/images/myv_pro.png");
    if (application.get('lock_screen'))
        glb_lock_screen = application.get('lock_screen');
    if (application.get('alerts_set'))
        glb_alert = application.get('alerts_set');
    if (application.get('push_set'))
        glb_push = application.get('push_set');
    if (application.get('default_html5_playback')) {
      default_html5_playback = application.get('default_html5_playback');
    } 
    if (application.get('show_hide_comments'))
        glb_comment_hidden = application.get('show_hide_comments');
    if (application.get('play_playlist'))
		glb_play_playlist = application.get('play_playlist');
    if (application.get('chrome_cast_setting'))
        glb_chrome_cast_setting = application.get('chrome_cast_setting');
    if (account.get('disp_name')) {
        Ext.getCmp('userMenuToolbar').setTitle(account.get('disp_name'));
    }
    if(application.get('darkTheme')){
        glb_darkTheme = application.get('darkTheme');
    }
    if(application.get('defaultAccess')){
        glb_defaultAccess = application.get('defaultAccess');
    }
    if(application.get('videoCall')){
        glb_videoCall = application.get('videoCall');
    }
    if (application && application.get("receipt")) {
        recpt_data = application.get("receipt");
        if(Ext.os.is('iOS') && account.get('user_id') && account.get('pw') && window.inAppPurchase !== undefined && recpt_data != ''){
            checkReceipt(recpt_data);
        }
    }
    if (account.get('family_filter') === "0" || account.get('family_filter') === "1") {
      if (localStorage.getItem('family_filter')) {
        if (account.get('family_filter') != localStorage.getItem('family_filter')) {
          family_filter = localStorage.getItem('family_filter');
        } else {
          family_filter = account.get('family_filter');
        }
      } else {
        family_filter = account.get('family_filter');
      }
    }     
    if (account.get('video_history')) {
      if (account.get('video_history').length === 0) {
        if (localStorage.getItem('video_history')) {
          if (JSON.parse(localStorage.getItem('video_history')).length > 0) {
            glb_video_history = JSON.parse(localStorage.getItem('video_history'));
          }
        }
      } else {
        glb_video_history = account.get('video_history');
      }
    }
    if (account.get('video_history_chk'))
        glb_video_history_chk = account.get('video_history_chk');
    if (account.get('v_orientation_chk') === "0" || account.get('v_orientation_chk') === "1") {
      if (localStorage.getItem('v_orientation_chk')) {
        if (account.get('v_orientation_chk') != localStorage.getItem('v_orientation_chk')) {
          v_orientation_chk = localStorage.getItem('v_orientation_chk');
        } else {
          v_orientation_chk = account.get('v_orientation_chk');
        }
      } else {
        v_orientation_chk = account.get('v_orientation_chk');
      }
    }     
    if (account.get('HTML5_only') === "0" || account.get('HTML5_only') === "1" ) {
      if (localStorage.getItem('HTML5_only')) {
        if (account.get('HTML5_only') != localStorage.getItem('HTML5_only')) {
          glb_HTML5_only = localStorage.getItem('HTML5_only');
        } else {
          glb_HTML5_only = account.get('HTML5_only');
        }
      } else {
        glb_HTML5_only = account.get('HTML5_only');
      }
    }   
    if (account.get('filter_by')) {
      if (localStorage.getItem('filter_by')) {
        if (account.get('filter_by') != localStorage.getItem('filter_by')) {
          glb_filter_by = localStorage.getItem('filter_by');
        } else {
          glb_filter_by = account.get('filter_by');
        }
      } else {
        glb_filter_by = account.get('filter_by');
      }
    }
    //dirty hack to unlock andriod phones
    if (Ext.os.is.Android) {
        glb_unlock = 1;
        // Ext.getCmp('family_filter').hide(); // hide Family Filter on Android
    }    
    else if (account.get('unlock')) {
        glb_unlock = account.get('unlock');
    }
    if (typeof device != 'undefined' && !glb_unlock) {
        Ext.getCmp('family_filter').hide();
    }
    if (default_html5_playback == null) {
      default_html5_playback = 1;
    }
    glb_set_family_filter = false;
    if(family_filter == 1 || family_filter == "1"){
            Ext.getCmp('family_filter1').setChecked(  true );
            Ext.getCmp('family_filter2').setChecked(  false );   
    }else{
            Ext.getCmp('family_filter1').setChecked(  false );
            Ext.getCmp('family_filter2').setChecked(  true );     
    }
    glb_set_family_filter = true;
    Ext.getCmp('settingsForm').setValues({
        family_filter: family_filter,
        v_orientation_chk: v_orientation_chk,
        video_history_chk: glb_video_history_chk,
        HTML5_only: glb_HTML5_only,
        lock_screen: glb_lock_screen,
        alerts_set: glb_alert,
        push_set: glb_push,
        default_html5_playback: default_html5_playback,
        show_hide_comments: glb_comment_hidden,
        darkTheme: glb_darkTheme,
        videoCall: glb_videoCall,
        //defaultAccess: glb_defaultAccess
    });

    glb_lock_check_done = true;

    if(Ext.os.is.iOS || Ext.os.is.Android){
    }else{
        Ext.getCmp('lock_app').setHidden(true);
        application.set("lock_screen", 0);
        application.setDirty();
        app_store.sync();
        application = app_store.first();
    }

    if (account.get('anc1') == 0) {
        myvAlert('Tip: Swipe left/right to navigate though the main menu and videos!');
        account.set("anc1", 1);
        account.setDirty();
        store.sync();
        account = store.first();
    }
	//resetting play list button for everyone
	if (account.get('anc1') == 1 && glb_play_playlist == 1 && typeof device != 'undefined') {
		application.set("play_playlist", 0);
        application.setDirty();
        app_store.sync();
        application = app_store.first();
		glb_play_playlist = 0;
		account.set("anc1", 2);
        account.setDirty();
        store.sync();
        account = store.first();
	}
	else if(account.get('anc1') == 1) {
		account.set("anc1", 2);
        account.setDirty();
        store.sync();
        account = store.first();
	}
    if (typeof device != 'undefined') {
        if (typeof cordova.plugins.idfa != 'undefined' && account.get('email')) {
			cordova.plugins.idfa.getInfo().then(function(info) {
				if (!info.limitAdTracking) {
					uuid_success(info.idfa || info.aaid);
				}
			});
        }
		else if(typeof analytics.getAdId != 'undefined') {
			var uuid_func = window.setTimeout(function() {
				analytics.getAdId(uuid_success, onFail);
			}, 500);
		}
    }
        if (account.get('email')) {
            homepage_preloader();
            var cloud_vids = glb_login_cloud_vids;
            if (typeof device != 'undefined' && !glb_unlock) {
                family_filter = glb_login_family_filter;
                if (family_filter == 0) {
                    Ext.getCmp('family_filter').show();
                    account.set("unlock", 1);
                }
            }
            Ext.getCmp('userMenuToolbar').setTitle(account.get('disp_name'));
            glb_current_username = account.get('disp_name');
            clearMask();
            Ext.getCmp('settingsForm').setValues({
                family_filter: family_filter,
                v_orientation_chk: v_orientation_chk,
                video_history_chk: glb_video_history_chk,
                HTML5_only: glb_HTML5_only,
                lock_screen: glb_lock_screen
            });
                custom_views_arr = [];
                searchviewbackhack();
                custom_views_arr.push('Home');
                var array_data = [0];
                glb_custom_views_arr_data = [];
                glb_custom_views_arr_data.push(array_data);
                try{
                    push_func_start();
                }catch(err){}
               clearMask();
        } else {
            homepage_preloader();
        }
}
function homepage_preloader() {
    if (window.innerWidth < window.innerHeight) {
        glb_panel_w = window.innerWidth;
        glb_panel_h = window.innerHeight;
    } else {
        glb_panel_w = window.innerHeight;
        glb_panel_h = window.innerWidth;
    }
    if (!glb_lock_screen_override && glb_lock_screen) {
    } else {
        setMask('Loading...');
        load_homepage(0, 0);
        if (typeof device != 'undefined') {
            setTimeout(function() {
                update_app();
            }, 2500);
        }
    }
}
function report_video_handle(video_id, master_id, ip, access) {
    var more_btn_sz3 = '';
    if (glb_device_type == 'tablet') {
        more_btn_sz3 = 'font-size: 1.22em;';
    }
    if (access == 2) {
        var video_actionSheet = Ext.create('Ext.ActionSheet', {
            items: [{
                text: 'Explicit',
                style: more_btn_sz3,
                handler: function(btn, evt) {
                    report_video(video_id, master_id, ip, 'mobile_adult');
                    video_actionSheet.hide();
                }
            }, {
                text: 'Broken',
                style: more_btn_sz3,
                handler: function(btn, evt) {
                    report_video(video_id, master_id, ip, 'mobile_broken');
                    video_actionSheet.hide();
                }
            }, {
                text: 'Underage',
                style: more_btn_sz3,
                handler: function(btn, evt) {
                    report_video(video_id, master_id, ip, 'a_underage');
                    video_actionSheet.hide();
                }
            }, {
                text: 'Cancel',
                style: more_btn_sz3,
                ui: 'decline',
                handler: function(btn, evt) {
                    video_actionSheet.hide();
                }
            }]
        });
    } else {
        var video_actionSheet = Ext.create('Ext.ActionSheet', {
            items: [{
                text: 'Explicit',
                style: more_btn_sz3,
                handler: function(btn, evt) {
                    report_video(video_id, master_id, ip, 'mobile_adult');
                    video_actionSheet.hide();
                }
            }, {
                text: 'Broken',
                style: more_btn_sz3,
                handler: function(btn, evt) {
                    report_video(video_id, master_id, ip, 'mobile_broken');
                    video_actionSheet.hide();
                }
            }, {
                text: 'Cancel',
                style: more_btn_sz3,
                ui: 'decline',
                handler: function(btn, evt) {
                    video_actionSheet.hide();
                }
            }]
        });
    }
    Ext.Viewport.add(video_actionSheet);
    video_actionSheet.show();
}
function report_video(video_id, master_id, ip, reason, silent) {
    if (silent == "" || typeof silent == "undefined") {
        silent = false;
    }
    setMask('Reporting video...');
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'report_video',
            id: video_id,
            master_id: master_id,
            ip: ip,
            reason: reason,
            token: glb_msg_token,
            uniquify: Math.random()
        },
        callback: function(success, result) {
            clearMask();
            if (result.items) {
                var response = result.items[0].response;
                if (response == 'Success') {
                    if (silent == false) {
                        myvAlert('Video has been reported');
                    }
                } else {
                    myvAlert(response);
                }
            }
        }
    });
}
function share_video(title, video_id) {
    var subject;
    var text;
    var video_link;
    subject = "Watch " + title + " from MyVidster";
    video_link = "https://www.myvidster.com/video/" + video_id;
    glb_lock_screen_override = true;

    if (typeof window.plugins.socialsharing != 'undefined') {
        text = account.data.disp_name + " wants you to check out this video:\r\n" + title + "\r\n" + video_link;
        window.plugins.socialsharing.share(text, null, null, null);
    } else if (typeof navigator.share != 'undefined') {
        text = account.data.disp_name + " wants you to check out this video:\r\n" + title + "\r\n" + video_link;
        navigator.share(text, subject);
    } else {
        var share_code;
        var msg = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msg.show({
            title: 'Video Sharing',
            msg: 'Select an option below.',
            buttons: [{
                text: 'twitter',
                itemId: 'twitter'
            }, {
                text: 'facebook',
                itemId: 'fb'
            }, {
                text: 'cancel',
                itemId: '0'
            }],
            fn: function(response) {
                if (response == 'email') {
                    text =  account.data.disp_name + " wants you to check out this video:%0D%0A" + title + "%0D%0A" + video_link;
                    load_webpage("mailto:?subject=" + subject + "&body=" + text, false);
                } else if (response == 'fb') {
                    load_webpage("https://m.facebook.com/sharer.php?u=" + video_link, false);
                } else if (response == 'twitter') {
                    load_webpage("https://twitter.com/share?url=" + video_link, false);
                }
            }
        });
    }
}
function set_filter_by(type) {
    glb_filter_by = type;
    account.set("filter_by", glb_filter_by);
    localStorage.setItem('filter_by', glb_filter_by);
    account.setDirty();
    store.sync();
    account = store.first();
    load_homepage(0);
}
function initSuccess()  {  }
function initError(err) {  }

var load_homepage = function(start, last_id, retry) {
    glb_homecheck_done = true;
    var limit = 10;
    if (glb_device_type == 'tablet') {
        limit = 14;
    }
    if(!glb_sortby){
        return;
    }
    // ios home scroll fix seb 2020
    Ext.getCmp('home_refresh_var').setStore(search_obj);
	home_obj.getProxy().setExtraParams({
        type: glb_sortby,
        family_filter: family_filter,
        filter_by: glb_filter_by,
        last_id: last_id,
        device_type: glb_device_type,
        token: glb_msg_token,
        start: start,
        limit: limit,
        html5: glb_HTML5_only,
        uniquify: Math.random(),
        user_id: account.get('user_id'),
        pkey: account.get('pw'),
        token: glb_msg_token
    });
    home_obj.currentPage = 1;
    home_obj.load({
        callback: function(){
            clearMask();
            setTimeout(function(){ Ext.getCmp('home_refresh_var').setStore(home_obj); }, 500);
        }
    });
   
    //setTimeout(function(){ Ext.getCmp('home_refresh_var').setStore(home_obj); }, 500);

    if (typeof device != 'undefined') navigator.splashscreen.hide();
};

/**
 *  Secure Hash Algorithm (SHA1) https://www.webtoolkit.info/
 **/

function SHA1(msg) {
    function rotate_left(n, s) {
        var t4 = (n << s) | (n >>> (32 - s));
        return t4;
    };

    function lsb_hex(val) {
        var str = "";
        var i;
        var vh;
        var vl;
        for (i = 0; i <= 6; i += 2) {
            vh = (val >>> (i * 4 + 4)) & 0x0f;
            vl = (val >>> (i * 4)) & 0x0f;
            str += vh.toString(16) + vl.toString(16);
        }
        return str;
    };

    function cvt_hex(val) {
        var str = "";
        var i;
        var v;
        for (i = 7; i >= 0; i--) {
            v = (val >>> (i * 4)) & 0x0f;
            str += v.toString(16);
        }
        return str;
    };

    function Utf8Encode(string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
        }
        return utftext;
    };

    var blockstart;
    var i, j;
    var W = new Array(80);
    var H0 = 0x67452301;
    var H1 = 0xEFCDAB89;
    var H2 = 0x98BADCFE;
    var H3 = 0x10325476;
    var H4 = 0xC3D2E1F0;
    var A, B, C, D, E;
    var temp;
    msg = Utf8Encode(msg);
    var msg_len = msg.length;

    var word_array = new Array();
    for (i = 0; i < msg_len - 3; i += 4) {
        j = msg.charCodeAt(i) << 24 | msg.charCodeAt(i + 1) << 16 |
            msg.charCodeAt(i + 2) << 8 | msg.charCodeAt(i + 3);
        word_array.push(j);
    }

    switch (msg_len % 4) {
        case 0:
            i = 0x080000000;
            break;
        case 1:
            i = msg.charCodeAt(msg_len - 1) << 24 | 0x0800000;
            break;

        case 2:
            i = msg.charCodeAt(msg_len - 2) << 24 | msg.charCodeAt(msg_len - 1) << 16 | 0x08000;
            break;

        case 3:
            i = msg.charCodeAt(msg_len - 3) << 24 | msg.charCodeAt(msg_len - 2) << 16 | msg.charCodeAt(msg_len - 1) << 8 | 0x80;
            break;
    }

    word_array.push(i);
    while ((word_array.length % 16) != 14) word_array.push(0);

    word_array.push(msg_len >>> 29);
    word_array.push((msg_len << 3) & 0x0ffffffff);
    for (blockstart = 0; blockstart < word_array.length; blockstart += 16) {
        for (i = 0; i < 16; i++) W[i] = word_array[blockstart + i];
        for (i = 16; i <= 79; i++) W[i] = rotate_left(W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16], 1);

        A = H0;
        B = H1;
        C = H2;
        D = H3;
        E = H4;

        for (i = 0; i <= 19; i++) {
            temp = (rotate_left(A, 5) + ((B & C) | (~B & D)) + E + W[i] + 0x5A827999) & 0x0ffffffff;
            E = D;
            D = C;
            C = rotate_left(B, 30);
            B = A;
            A = temp;
        }
        for (i = 20; i <= 39; i++) {
            temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0x6ED9EBA1) & 0x0ffffffff;
            E = D;
            D = C;
            C = rotate_left(B, 30);
            B = A;
            A = temp;
        }
        for (i = 40; i <= 59; i++) {
            temp = (rotate_left(A, 5) + ((B & C) | (B & D) | (C & D)) + E + W[i] + 0x8F1BBCDC) & 0x0ffffffff;
            E = D;
            D = C;
            C = rotate_left(B, 30);
            B = A;
            A = temp;
        }
        for (i = 60; i <= 79; i++) {
            temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0xCA62C1D6) & 0x0ffffffff;
            E = D;
            D = C;
            C = rotate_left(B, 30);
            B = A;
            A = temp;
        }
        H0 = (H0 + A) & 0x0ffffffff;
        H1 = (H1 + B) & 0x0ffffffff;
        H2 = (H2 + C) & 0x0ffffffff;
        H3 = (H3 + D) & 0x0ffffffff;
        H4 = (H4 + E) & 0x0ffffffff;
    }
    var temp = cvt_hex(H0) + cvt_hex(H1) + cvt_hex(H2) + cvt_hex(H3) + cvt_hex(H4);
    return temp.toLowerCase();
}

function msg_a_user(uid5, uid_username, back) {
    if (uid_username == "" || uid_username == null) {
        var old_title = Ext.getCmp('userListToolbarMessage').getActiveTab().title;
        uid_username = old_title;
    }
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    glb_current_messaging = uid5;
    Ext.getCmp('userListToolbarMessage').setTitle("" + uid_username);
    list_detailed_msgs_obj.removeAll();
    var dt = new Date();
    var offset_time = dt.getTimezoneOffset();
    list_detailed_msgs_obj.getProxy().setExtraParams({
        type: 'detailed_messages',
        user_to: uid5,
        user_id: account.get('user_id'),
        pkey: account.get('pw'),
        token: glb_msg_token,
        start: 0,
        device_type: glb_device_type,
        id: Math.random(),
        offset: offset_time
    });
    list_detailed_msgs_obj.load();
    msg_user(uid5, account.get('user_id'), uid_username);
    setTimeout(function() {
        // try/catch needed due to pusher plugin being buggy sometimes
        try{
            var send_to_id = "presence-" + String(uid5);
            var presenceChannel = pusher.unsubscribe(send_to_id);
        }catch(err){myvAlert('Please restart the app!');}
    }, 2900);
}
function ii(i, len) {
    var s = i + "";
    len = len || 2;
    while (s.length < len) s = "0" + s;
    return s;
}
function img_btn_fix_one() {
    //document.getElementById('msglistscroll').style.pointerEvents = 'none';
    //Ext.getCmp('home_tabbar_MsgUser').hide();
}
function img_btn_fix_two() {
    //if(typeof device != 'undefined' && Ext.os.is('iOS')){
        //var chat_ui_fix = Ext.create('Ext.ActionSheet'); // fix for ios issue causing chat screen not to go down
        //Ext.Viewport.add(chat_ui_fix); 
        //chat_ui_fix.show();
        //chat_ui_fix.hide();
    //}
    if (glb_chat_send_btn == 1) {
        document.getElementById("msg_c").focus();
    }
    setTimeout(function() {
        document.getElementById('msglistscroll').style.pointerEvents = 'auto';
    }, 600);
}
function send_msg_btn2() {
    glb_chat_send_btn = 1;
    document.getElementById("msg_c").focus();
    var to = Ext.getCmp('to_c').getValue();
    var from = Ext.getCmp('from_c').getValue();
    var msg = document.getElementById('msg_c').value; 
    if (msg && glb_chat_block == '') {
        // try/catch prevent issues from special chars 
        try {
            var msg1 = escape(msg);
            msg1 = decodeURIComponent(msg1);
            msg1 = encodeHTML(msg1);
        } catch (e) {
            var msg1 = encodeURI(msg);
            msg1 = decodeURIComponent(msg1);
            msg1 = encodeHTML(msg1);
        }
        msg1 = myv_url_share(msg1);
        var days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
        var d = new Date();
        var day = days[d.getDay()];
        var hr = d.getHours();
        var min = d.getMinutes();
        min = String(min);
        if (min.length < 2) {
            min = '0' + min;
        }
        var ampm = hr < 12 ? " AM" : " PM";
        if (hr > 12) {
            hr -= 12;
        } else if (hr === 0) {
            hr = 12;
        }
        var date = d.getDate();
        var time_f = day + " " + hr + ":" + min + ampm;
        list_detailed_msgs_obj.add({
            from_u_profile_photo: account.get('profile_photo'),
            message: msg1,
            to_u_profile_photo: account.get('profile_photo'),
            from_u_id: from,
            to_u: to,
            float_u: 'right',
            time: time_f
        });
        clearMask();
        setTimeout(function() {
            var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();
            scroller.scrollToEnd(true);
        }, 800);
        pusher_test(from, to, msg, null);
    } else {
        clearMask();
    }
    if (glb_chat_block != '') {
        myvAlert('You are blocked from messaging this user.');
    }
    document.getElementById('msg_c').value = "";
    read_msg(account.get('user_id'), account.get('pw'), to, 1);
    setTimeout(function() {
        glb_chat_send_btn = 0;
    }, 700);
}
var clear_msg = function(glb_last_chat_id) {
    if (typeof device != 'undefined') {
        navigator.notification.confirm('Clear Message History?',
            function(buttonIndex) {
                if (buttonIndex == 1) {
                    Ext.util.JSONP.request({
                        url: 'https://api.myvidster.com/mobile_json2.php',
                        callbackKey: 'callback',
                        params: {
                            type: 'delete_msg',
                            user_id: account.get('user_id'),
                            pkey: account.get('pw'),
                            token: glb_msg_token,
                            user_to: glb_current_messaging,
                            chat_id: glb_last_chat_id
                        },
                        callback: function(success, result) {
                            var response = result.items[0]['response'];
                            list_detailed_msgs_obj.removeAll();
                        }
                    });
                }
            }, 'Message History', 'Clear,Cancel');
    } else {
        var msgA = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msgA.show({
            title: 'Message History',
            buttons: [{
                text: 'Clear Messages?',
                itemId: '1'
            }, {
                text: 'cancel',
                itemId: '0'
            }],
            fn: function(response) {
                if (response != 0) {
                    Ext.util.JSONP.request({
                        url: 'https://api.myvidster.com/mobile_json2.php',
                        callbackKey: 'callback',
                        params: {
                            type: 'delete_msg',
                            user_id: account.get('user_id'),
                            pkey: account.get('pw'),
                            token: glb_msg_token,
                            user_to: glb_current_messaging,
                            chat_id: glb_last_chat_id
                        },
                        callback: function(success, result) {
                            var response = result.items[0]['response'];

                            list_detailed_msgs_obj.removeAll();
                        }
                    });
                }
            }
        });
    }
}
var block_msg = function(to, last_id, block, rowIndex) {
    if (typeof device != 'undefined') {
        navigator.notification.confirm('Block User?',
            function(buttonIndex) {
                if (buttonIndex == 1) {
                    setMask('Loading...');
                    delete_chat_hist(to, last_id, '1', rowIndex);
                }
            }, 'User', 'Block,Cancel');
    } else {
        var msgA = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msgA.show({
            title: 'User',
            buttons: [{
                text: 'Block User?',
                itemId: '1'
            }, {
                text: 'cancel',
                itemId: '0'
            }],
            fn: function(response) {
                if (response != 0) {
                    setMask('Loading...');
                    delete_chat_hist(to, last_id, '1', rowIndex);
                }
            }
        });
    }
}
var unblock_user = function(id) {
    if (typeof device != 'undefined') {
        navigator.notification.confirm('Unblock User?',
            function(buttonIndex) {
                if (buttonIndex == 1) {
                    setMask('Loading...');
                    Ext.util.JSONP.request({
                        url: 'https://api.myvidster.com/mobile_json2.php',
                        callbackKey: 'callback',
                        params: {
                            user_id: account.get('user_id'),
                            pkey: account.get('pw'),
                            type: 'unblock',
                            token: glb_msg_token,
                            friend_id: id
                        },
                        callback: function(success, result) {
                            clearMask();
                            if (result.items) {
                                var status = result.items[0].response;
                                if (status == 'Success') {
                                    var remove_oncl = document.getElementsByClassName('follows_2');
                                        remove_oncl[0].innerHTML = "Follow";
                                        list_videos('User', id, 0);
                                        list_videos_obj.load();
                                } else {
                                    myvAlert("Error");
                                }
                            } 
                        }
                    });
                }
            }, 'User', 'Unblock,Cancel');
    } else {
        var msgA = new Ext.MessageBox({
            width: 300,
            floating: 1,
            margin: '80 0 0 0'
        });
        msgA.show({
            title: 'User',
            buttons: [{
                text: 'Unblock User?',
                itemId: '1'
            }, {
                text: 'Cancel',
                itemId: '0'
            }],
            fn: function(response) {
                if (response != 0) {
                    setMask('Loading...');

                    Ext.util.JSONP.request({
                        url: 'https://api.myvidster.com/mobile_json2.php',
                        callbackKey: 'callback',
                        params: {
                            user_id: account.get('user_id'),
                            pkey: account.get('pw'),
                            type: 'unblock',
                            token: glb_msg_token,
                            friend_id: id
                        },
                        callback: function(success, result) {
                            clearMask();
                            if (result.items) {
                                var status = result.items[0].response;
                                if (status == 'Success') {
                                    var remove_oncl = document.getElementsByClassName('follows_2');
                                        remove_oncl[0].innerHTML = "Follow";
                                        list_videos('User', id, 0);
                                        list_videos_obj.load();
                                } else {
                                    myvAlert("Error");
                                }
                            } 
                        }
                    });
                }
            }
        });
    }
}
var more_for_user = function(to, name) {
if (typeof device != 'undefined') {
if(Ext.os.is.iOS){
    navigator.notification.confirm('',
    function(buttonIndex) {
        if (buttonIndex == 2) {
            navigator.notification.confirm('',
                function(buttonIndex2) {
                    if (buttonIndex2 == 1) {
                        setMask('Loading...');
                        delete_chat_hist(to, '', '1');
                    }
                }, 'Are you sure?', 'Block,Cancel');
        }
        if (buttonIndex == 1) {
            msg_a_user(to, name, 0); 
        }
    }, 'Action', 'Send Message,Block,Cancel');
}else{
    navigator.notification.confirm('',
            function(buttonIndex) {
                if (buttonIndex == 3) {
                    navigator.notification.confirm('',
                        function(buttonIndex2) {
                            if (buttonIndex2 == 1) {
                                setMask('Loading...');
                                delete_chat_hist(to, '', '1');
                            }
                        }, 'Are you sure?', 'Block,Cancel');
                }
                if (buttonIndex == 2) {
                    setMask('Starting call...');
                    Ext.util.JSONP.request({
                        url: 'https://chat.myvidster.com/tixtok/web/api.php',  
                        callbackKey: 'callback',
                        params: {
                            type: 'start_videochat',
                            user_id: account.get('user_id'),
                            pkey: account.get('pw'),
                            uniquify: Math.random(),
                            to_id: to,
                            token: glb_msg_token
                        },
                        callback: function(success, result) {
                            if (result.items[0].response == "Success") {
                                clearMask();
                                var video_token = result.items[0]['token'];
                                var video_session = result.items[0]['sessionId'];
                                var video_apikey = result.items[0]['apiKey'];
                                initializeSession(to, video_apikey, video_session, video_token);
                            }
                            else {
                            	myvAlert(result.items[0].response);
                            	clearMask();
                            }
                    }
                    });
                }
                if (buttonIndex == 1) {
                    msg_a_user(to, name, 0); 
                }
            }, 'Action', 'Send Message,Video Call,Block,Cancel');
}
    } else {
        var msgA = new Ext.MessageBox({
            width: '80%',
            floating: 1,
            margin: '80 0 0 0'
        });
        msgA.show({
            title: 'User',
            buttons: [{
                text: 'Chat',
                itemId: '3'
            }, 
            /* {
                text: 'Video Call',
                itemId: '2'
            },*/
            {
                text: 'Block',
                itemId: '1'
            }, {
                text: 'Cancel',
                itemId: '0'
            }],
            fn: function(response) {
                if (response == 1) {
                    var msgA2 = new Ext.MessageBox({
                        width: 320,
                        floating: 1,
                        margin: '80 0 0 0'
                    });
                    msgA2.show({
                        title: 'Are you sure?',
                        buttons: [{
                            text: 'Block User',
                            itemId: '1'
                        }, {
                            text: 'Cancel',
                            itemId: '0'
                        }],
                        fn: function(response2) {
                            if (response2 == 1) {
                                setMask('Loading...');
                                delete_chat_hist(to, '', '1');
                            }
                        }
                    });
                }
                if (response == 2) {
                    setMask('Starting call...');
                    Ext.util.JSONP.request({
                        url: 'https://chat.myvidster.com/tixtok/web/api.php',  
                        callbackKey: 'callback',
                        params: {
                            type: 'start_videochat',
                            user_id: account.get('user_id'),
                            pkey: account.get('pw'),
                            uniquify: Math.random(),
                            to_id: to,
                            token: glb_msg_token
                        },
                        callback: function(success, result) {
                            if (result.items[0].response == "Success") {
                                clearMask();
                                var video_token = result.items[0]['token'];
                                var video_session = result.items[0]['sessionId'];
                                var video_apikey = result.items[0]['apiKey'];
                                initializeSession(to, video_apikey, video_session, video_token);
                            }
                            else {
                            	myvAlert(result.items[0].response);
                            	clearMask();
                            }
                    }
                    }); 
                }
                if (response == 3) {
                    msg_a_user(to, name, 0);
                }
            }
        });
    }
}
var more_for_user2 = function(to) {
    unblock_user(to);
}
function wait_chat(param1, cnt, nameV) {
    if (list_detailed_msgs_obj.isLoading() && cnt < 3800) {
        setTimeout(wait_chat, 100, param1, cnt + 100, nameV);
    } else {
        msg_user(param1, account.get('user_id'), nameV);
        clearMask();
        Ext.getCmp('list_msgs_var').deselectAll();
    }
}
function successFunction() {
}
function errorFunction(error) {
}
function supportedFunction(error) {
    glb_supportedFunction_var = true;
}
function supportedFunction2(error) {
    glb_supportedFunction2_var = true;
}
function exitHandler() {
	if (document.fullScreenElement !== undefined && document.fullscreenElement || document.webkitFullscreenElement !== undefined && document.webkitFullscreenElement || document.mozFullScreenElement !== undefined && document.mozFullScreenElement || document.msFullscreenElement !== undefined && document.msFullscreenElement) {
        if (typeof device != 'undefined') screen.unlockOrientation();
		if (glb_supportedFunction_var == true && glb_supportedFunction2_var == true) {
            AndroidFullScreen.immersiveMode(successFunction, errorFunction);
        }
    } else {
        if (glb_supportedFunction_var == true && glb_supportedFunction2_var == true) {
            AndroidFullScreen.showSystemUI(successFunction, errorFunction);
        }
        if (v_orientation_chk == 1) {
            if (typeof device != 'undefined') screen.lockOrientation('portrait');
        }
    }
}
function video_err(master_id, video_id, ip) {
    if (Ext.os.is.Android && default_html5_playback == 0) { // andriod and using legacy player
      if (glb_last_panel != 'Video' && glb_last_panel != 'Video2') { // is first video
        load_webpage(counter_url);
      } else {
        playVideo_handler('https://cdn2.myvidster.com/app/videoerror.mp4');
      }
      report_video(video_id, master_id, ip, 'mobile_video_fail', true);
    } else {
      glb_master_id = 'error';
      var video = document.getElementsByTagName("video")[0];
      video.pause();
      video.setAttribute('src', 'https://cdn2.myvidster.com/app/videoerror.mp4');
      video.load();
      video.play();
      if (glb_play_playlist == 1 && Ext.os.is.iOS) {
        setTimeout(function() {
          glb_myPlayer.exitFullscreen();
          doPlayListNativePlayer();
        }, 3000);
      }
      if (typeof device != 'undefined') {
          window.plugins.insomnia.allowSleepAgain();
      }
      report_video(video_id, master_id, ip, 'mobile_video_fail', true);  
    }
}
function no_sleep_player() {
    if (typeof device != 'undefined') {
        window.plugins.insomnia.keepAwake();
        setTimeout(function() {
            window.plugins.insomnia.allowSleepAgain();
        }, 8000);
    }
}
function myFunctionE(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode == 13) { // charcode 13 for "entry" keyboard
        evt.preventDefault();
        send_msg_btn2();
    }
}
function startBasicCountdown() {
  var next_vid = glb_next_video;
  var get_next_vid = String(next_vid);
  startVideoCountdown();
  masterCounter = setTimeout(function() {
    if (!countdownCancelled) {
      getVideo(get_next_vid, glb_video_type);
      if (glb_play_playlist && default_html5_playback == 0 && Ext.os.is('Android')) {
        setTimeout(function() {
          setTimeout(function() {
              playVideo_handler(glb_video_file || glb_source_playback);
              doPlayList(); 
            }, 1000);
        }, 4000);
      } else {
        setTimeout(function() {
          var video2 = document.getElementsByTagName("video")[0];
          setTimeout(function() {
              video2.play();
              doPlayList(); 
            }, 1000);
        }, 4000);        
      }
    }
  }, 10000); 
}
function doPlayListNativePlayer() {
    if (glb_play_playlist == 1 && glb_next_video != null && glb_next_video != '') {
      setTimeout(function() {
          var next_vid = glb_next_video;
          var get_next_vid = String(next_vid);
          if (glb_myPlayer) {
              // try/catch added by sterling dev
              try {
                  glb_myPlayer.dispose();
                  glb_myPlayer = false;
                  startVideoCountdown();
                  masterCounter = setTimeout(function() {
                    if (!countdownCancelled) {
                      getVideo(get_next_vid, glb_video_type);
                    }
                  }, 10000); 
              } catch (err) {
              }
          }
          clearMask();
          try {
              setTimeout(function() {
                  var checkLoad = function() {
                      var counter = 0;
                      var video2 = document.getElementsByTagName("video")[0];
                      if (typeof(video2) != 'undefined' && video2 != null) {
                          no_sleep_player();
                          var video2 = document.getElementsByTagName("video")[0];
                          if (glb_play_playlist == 1) {
                           if (!countdownCancelled) {
                           setTimeout(function() {
                              video2.play();
                              doPlayList(); 
                            }, 1000);
                          } 
                          }
                      } else {
                          if (counter < 210) {
                              setTimeout(checkLoad, 100);
                              counter = counter + 1;
                          }
                      }
                  };
                  checkLoad();
              }, 4000); 
          } catch (err) {}
      }, 1000);
    }
}
function startVideoCountdown() {
    counterNumber = 10;
    var timeChecked = false;
    document.getElementById('countdown').style.display = 'inline-flex';
    masterVideoCounter = setInterval(function() {
      if (!timeChecked) {
        if (counterNumber != 10) {
          counterNumber = 10;
        }
        timeChecked = true;
      }
      counterNumber = counterNumber - 1;
      document.getElementById('countdownText').innerText = 'Next Video Playing in '+ counterNumber +'...'; 
      //checks if video has been cancelled at every tick
      if (counterNumber == 0 || countdownCancelled) {
        clearInterval(masterVideoCounter); 
        document.getElementById('countdown').style.display = 'none';
      }
    },1000);
}
function doPlayList() {
    try {
        // try catch because this is a new function
        var video_d = document.getElementsByTagName("video")[0];
        video_d.onloadedmetadata = function() {
            if (glb_master_id != 'error' && glb_video_duration == null) {
                Ext.util.JSONP.request({
                    url: 'https://api.myvidster.com/mobile_json2.php',
                    callbackKey: 'callback',
                    params: {
                        type: 'video_duration_log',
                        id: glb_master_id,
                        token: glb_msg_token,
                        duration: (video_d.duration).toFixed(3)
                    },
                    callback: function(success, result) {
                    }
                });
            } 
        };
    } catch (err) {}
    setTimeout(function() {
        try { // try catched added by stering dev
            var checkLoad_vid = function() {
                var counter1 = 0;
                var video = document.getElementsByTagName("video")[0];
                if (typeof(video) != 'undefined' && video != null) {
                    video.addEventListener("click", function(e) {
                        no_sleep_player();
                    });
                    video.addEventListener("timeupdate", function(e) {
                        e.preventDefault();
                        if (video.currentTime >= video.duration && glb_play_playlist == 1 && glb_next_video != null && glb_next_video != '') {
                            /////////
                            // when video ends exit fullscreen immersion mode
                            if (typeof device != 'undefined') {
                                window.plugins.insomnia.allowSleepAgain();
                            }
                            try {
                                if (glb_supportedFunction_var == true && glb_supportedFunction2_var == true) {
                                    AndroidFullScreen.showSystemUI(successFunction, errorFunction);
                                }
                                video.webkitExitFullscreen();
                                video.exitFullscreen();
                                video.mozCancelFullscreen();
                            } catch (err) {}
                            var next_vid = glb_next_video;
                            var get_next_vid = String(next_vid);
                            setTimeout(function() {
                                if (glb_myPlayer) {
                                    try {
                                        glb_myPlayer.dispose();
                                        glb_myPlayer = false;
                                        startVideoCountdown(); 
                                        masterCounter = setTimeout(function() {
                                          if (!countdownCancelled) {
                                            getVideo(get_next_vid, glb_video_type);
                                          }
                                        },10000);
                                    } catch (err) {
                                    }
                                }
                                clearMask();
                                try {
                                    setTimeout(function() {
                                        var checkLoad = function() {
                                            var counter = 0;
                                            var video2 = document.getElementsByTagName("video")[0];
                                            if (typeof(video2) != 'undefined' && video2 != null) {
                                                no_sleep_player();
                                                try { // try catched needed in case of slow loading 
                                                    if (typeof device != 'undefined' && Ext.os.is('Android')) {
                                                        var play_btn = document.getElementById('andr_cover');
                                                        play_btn.setAttribute("z-index", "-10000");
                                                        play_btn.parentNode.removeChild(play_btn);
                                                    }
                                                } catch (err) {}
                                                var video2 = document.getElementsByTagName("video")[0];
                                                if (typeof device != 'undefined' && Ext.os.is('Android')) {
                                                    video2.setAttribute("controls", "controls"); // for android only
                                                }
                                                if (glb_play_playlist == 1) {
                                                    if (!countdownCancelled) {
                                                      video2.play();
                                                      doPlayList();
                                                    }
                                                }
                                            } else {
                                                if (counter < 210) {
                                                    setTimeout(checkLoad, 100);
                                                    counter = counter + 1;
                                                }
                                            }
                                        };
                                        checkLoad();
                                    }, 4000); // play new vid
                                } catch (err) {}
                            }, 1000);
                            //////////
                        }
                    });
                } else {
                    if (counter1 < 250) {
                        setTimeout(checkLoad_vid, 100);
                        counter1 = counter1 + 1;
                    }
                }
            };
            checkLoad_vid();
        } catch (err) {}
    }, 4000);
}
function push_alert_bug_fix() {
        glb_current_messaging = "";
        if (account.get('user_id')) {
            read_msg(account.get('user_id'), account.get('pw'), 0, 0);
        }
}
function premium_slideshow() {
    // try/catch added by sterling dev
    try {
        var i;
        var my_image = new Image();
        if (glb_thumbs_array != null) {
            var pics = glb_thumbs_array;
            for (pre_ld_i = 0; pre_ld_i < pics.length - 1; pre_ld_i++) {
                new Image().src = pics[pre_ld_i];
            }
            if (premium_slideshow_index > pics.length - 1) {
                premium_slideshow_index = 0;
            }
            document.getElementsByClassName("video-dev")[0].style.backgroundImage = "url('" + pics[premium_slideshow_index] + "')";
            premium_slideshow_index++;
            myVarTimeOut = setTimeout(premium_slideshow, 1000);
        }
    } catch (err) {}
}
// check if plugin exists 
function onSuccess_cts(contacts) {
    var contactsArr = [];
    var taskModel = null,
        taskStore = list_contacts_obj;
        //tmp = new Array();
        contactsWithNoAccounts.length = 0;
    taskStore.removeAll(true);
    for (var i = 0; i < contacts.length; i++) {
        if (contacts[i].phoneNumbers != null) {
            var cntnum2 = contacts[i].phoneNumbers[0].value;
            var lowerc_t = contacts[i].name.formatted;
            var removeStartParentheses = /[(]/g;
            var removeEndParentheses =  /[)]/g;
            //format phone number for native sms use
            var formattedPhoneNumber = cntnum2.replace(/[^0-9]/g, '');
            // try/catch added by sterling dev
            try {
                lowerc_t = lowerc_t.toLowerCase(lowerc_t);
            } catch (err) {}

            contactsWithNoAccounts.push({
                data: contacts[i].name.formatted,
                extra: cntnum2,
                lowerc: lowerc_t,
                phoneNum: formattedPhoneNumber
            });
            contactsArr.push({
                name: lowerc_t,
                phone_number: cntnum2
            });
        }
    };
    // if using contact phone number list to check if contacts have an account
    if (checkContacts) {
        checkIfContactsHaveAccount(contactsArr);
        checkContacts = false;
    }
    taskStore.add(contactsWithNoAccounts);
    taskStore.sync();
};
function onError_cts(contactError) {
  clearMask();
  myvAlert('Please allow access to contacts in your phone settings to use this feature!');
};
// sets up contact screen with contacts to invite
function invite_friends() {
        if (Ext.os.is('Android') || Ext.os.is('iOS')) {
            var phone_number = account.get('phone_number');
            if (!phone_number) { // No phone number stored
                Ext.Viewport.animateActiveItem("UserPhoneInput", {
                    type: 'fade',
                });
                searchviewbackhack();
                custom_views_arr.push("UserPhoneInput");
                glb_custom_views_arr_data.push([0]);
            } else {
                glb_last_panel = Ext.Viewport.getActiveItem().id;
                Ext.Viewport.animateActiveItem("inviteF", {
                    type: 'fade',
                });
                searchviewbackhack();
                custom_views_arr.push("inviteF");
                glb_custom_views_arr_data.push([0]);
                setTimeout(function() {
                    try { // try/catch added by sterling dev
                        var options = new ContactFindOptions();
                        options.multiple = true;
                        //options.hasPhoneNumber = true;
                        var fields = [navigator.contacts.fieldType.displayName, navigator.contacts.fieldType.name, navigator.contacts.fieldType.emails];
                        navigator.contacts.find(fields, onSuccess_cts, onError_cts, options);
                    } catch (err) {
                        myvAlert('Please update your app');
                    }
                }, 1300);
            }
        } else {
            myvAlert("Please install our native app for this feature.");
        }
    }

document.addEventListener("deviceready", function() { 
  if (typeof device != 'undefined') {
    if (device.manufacturer === "Google" || device.manufacturer === "google" || 
      device.manufacturer === "LGE") {
      isPixelPhone = true;
    }
  }
    window.addEventListener("statusTap", function() {
        try {
            // try/catch added to prevent app lock up is statusTap isn't supported
            if (Ext.Viewport.getActiveItem().id == "Home") {
                var scroller = Ext.getCmp('home_refresh_var').getScrollable().getScroller();
                scroller.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "Video") {
                var scroller20 = Ext.getCmp('video_holder').getScrollable().getScroller();
                scroller20.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "Video2") {
                var scroller21 = Ext.getCmp('video_holder2').getScrollable().getScroller();
                scroller21.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "Search") {
                var scroller1 = Ext.getCmp('search_var').getScrollable().getScroller();
                scroller1.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "Settings") {
                var scroller3 = Ext.getCmp('Settings').getScrollable().getScroller();
                scroller3.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "MsgList") {
                var scroller4 = Ext.getCmp('list_msgs_var').getScrollable().getScroller();
                scroller4.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "MsgUser") {
                var scroller6 = Ext.getCmp('msglistscroll').getScrollable().getScroller();
                scroller6.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "UserList") {
                // collections, bookmarks, etc
                var scroller7 = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                scroller7.scrollToTop(true);
            } else if (Ext.Viewport.getActiveItem().id == "Help") {
            } else if (Ext.Viewport.getActiveItem().id == "inviteF") {
                var scroller5 = Ext.getCmp('contact_list_var').getScrollable().getScroller();
                scroller5.scrollToTop(true);
            } else if (Ex.Viewport.getActiveItem().id === 'Discover') {
                var scroller8 = Ex.getCmp('discover_recommendations_var').getScrollable().getScroller();
                scroller8.scrollToTop(true);
            } else if (Ex.Viewport.getActiveItem().id === 'Tutorial') {
                var scroller9 = Ex.getCmp('tutorial_recommendations_var').getScrollable().getScroller();
                scroller9.scrollToTop(true);
            } else if (Ex.Viewport.getActiveItem().id === 'Contacts') {}
        } catch (err) {}
    });
}, false);

function send_prodata_ios(receipt, transactionId, signature, tier) {
    var http = new XMLHttpRequest();
    var url = "https://api.myvidster.com/mobile_json2.php";
    var params = "type=ios_upgrade&user_id=" + account.get('user_id') + "&pkey=" + account.get('pw') + "&receipt=" + encodeURIComponent(receipt) + "&transactionId=" + transactionId + "&tier=" + tier + "&signature=" + signature + "&token="+ glb_msg_token;
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    application.set("receipt", receipt);
    application.setDirty();
    app_store.sync();
    application = app_store.first();

    http.onreadystatechange = function() {
        if (http.status == 200 && http.readyState == 4) {
            var test_res = http.responseText;
            substring = "success"; // responseText
            if (test_res.indexOf(substring) !== -1) {
                // everything worked
            }
        } else if (http.readyState == 4) {
            // server has an error
                var temp_var = '';
                try{
                  temp_var = http.status;
                }catch(err){}
                var http2 = new XMLHttpRequest();
          var url2 = "https://api.myvidster.com/mobile_json2.php";
          var params2 = "type=ios_upgrade&user_id=" + account.get('user_id') + "&pkey=" + account.get('pw') + "&receipt=" + encodeURIComponent(receipt) + "&transactionId=" + transactionId + "&tier=" + tier + "&signature=" + signature + "&temp_var="+ temp_var + "&token="+ glb_msg_token;
          http2.open("POST", url2, true);
          http2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
          application.set("receipt", receipt);
          application.setDirty();
          app_store.sync();
          application = app_store.first();
          http2.send(params2);
              myvAlert("Sorry there was an issue with you in app purchase, please restart the app");
        }
    }
    http.send(params);
    Ext.Viewport.animateActiveItem("UserMenu", {
        type: 'fade'
    });
    searchviewbackhack();
    custom_views_arr.push("UserMenu");
    glb_custom_views_arr_data.push([0]);
}
// load iap products 
function ios_upgrade(tier) { 
    // try/catch added in case issues occur with apple in app purchase servers
    try {
        ///
        if (window.inAppPurchase !== undefined) {
            setMask('Loading...');
            inAppPurchase
                .getProducts(['silver.product4', 'gold.product5', 'platinum.product6'])
                .then(function(products) {
                    //clearMask();
                    setTimeout(clearMask(), 2000);
                    if (tier == 1) {
                        inAppPurchase
                            .subscribe('silver.product4')
                            .then(function(data) {
                                send_prodata_ios(data['receipt'], data['transactionId'], data['signature'], '1');
                            })
                            .catch(function(err) {
                                setTimeout(clearMask(), 1000);
                                //clearMask();
                            });                            
                    } else if (tier == 2) {
                        inAppPurchase
                            .subscribe('gold.product5')
                            .then(function(data) {
                                send_prodata_ios(data['receipt'], data['transactionId'], data['signature'], '2');
                            })
                            .catch(function(err) {
                                setTimeout(clearMask(), 1000);
                            });
                    } else if (tier == 3) {
                        inAppPurchase
                            .subscribe('platinum.product6')
                            .then(function(data) {
                                send_prodata_ios(data['receipt'], data['transactionId'], data['signature'], '3');
                            })
                            .catch(function(err) {
                                setTimeout(clearMask(), 1000);
                            });
                    }
                }).catch(function(err) {
                    setTimeout(clearMask(), 1000);
                });
        }
        ///
    } catch (err) {}
}

// displays payment policy for iap ios users
function iap_desc_alert() {

    var reveal_iap = document.getElementById('reveal_iap').style.display;
    if (reveal_iap == "none") {
        document.getElementById('reveal_iap').style.display = "block";
    } else if (reveal_iap == "block") {
        document.getElementById('reveal_iap').style.display = "none";
    }
}

// close the share ios button alert
function close_alert2() {
    Ext.getCmp('message-box-feature').hide();
    var options5 = {
        key: "alert_msg_check",
        value: "seen",
        suite: "group.com.myvidster"
    };
    window.AppGroupsUserDefaults.save(options5,
        function() {},
        function() {});
    // update user defaults. set alert_viewed to 1.
}

// this function changes the text of the ios share button feature alert, as you click "next"
function next_alert_image() {
    var current_img = document.getElementById("img_alert").src;
    if (current_img == "https://cdn2.myvidster.com/images/app/share_btn0.jpg") {
        document.getElementById("img_alert").src = "https://cdn2.myvidster.com/images/app/share_btn2.jpg";
        document.getElementById("matching_alert_text").innerText = "Enable MyVidster for sharing";

    } else if (current_img == "https://cdn2.myvidster.com/images/app/share_btn2.jpg") {
        document.getElementById("img_alert").src = "https://cdn2.myvidster.com/images/app/share_btn3.jpg";
        document.getElementById("matching_alert_text").innerText = "Post the video to your bookmarks";

    } else if (current_img == "https://cdn2.myvidster.com/images/app/share_btn3.jpg") {
        document.getElementById("img_alert").src = "https://cdn2.myvidster.com/images/app/share_btn0.jpg";
        document.getElementById("matching_alert_text").innerText = "Share by clicking the share icon";
    }
}
// restore iOS purchase
function restore_purchase(){
if (Ext.os.is('iOS') && window.inAppPurchase !== undefined) {
                    // try/catch needed in case of server issue with apple in app puchase
                   try {
                        setMask('Loading...');
                        window.inAppPurchase
                            .getReceipt()
                            .then(function(receipt) {
                                var http = new XMLHttpRequest();
                                var url = "https://api.myvidster.com/mobile_json2.php";
                                var params = "type=iap_receipt_check&user_id=" + account.get('user_id') + "&pkey=" + account.get('pw') + "&receipt=" + encodeURIComponent(receipt) + "&token="+ glb_msg_token;
                                http.open("POST", url, true);
                                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                application.set("receipt", receipt);
                                application.setDirty();
                                app_store.sync();
                                application = app_store.first();
                                http.send(params);
                                http.onreadystatechange = function() {
                                    clearMask(); 
                                    if (http.responseText) {
                                        if (JSON.parse(http.responseText).items[0].responseText === 'success') {
                                            displaySuccessRestoredMessage();
                                        }
                                    }
                                };
                            })
                            .catch(function(err) {
                                myvAlert('Error restoring purchases!');
                                clearMask();
                                if (application && application.get("receipt")) {
                                    recpt_data = application.get("receipt");

                                    var http2 = new XMLHttpRequest();
                                    var url2 = "https://api.myvidster.com/mobile_json2.php";
                                    var params2 = "type=iap_receipt_check&user_id=" + account.get('user_id') + "&pkey=" + account.get('pw') + "&receipt=" + encodeURIComponent(recpt_data)+"&token="+ glb_msg_token;
                                    http2.open("POST", url2, true);
                                    http2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                    http2.send(params2);
                                }
                            });
                    } catch (err) {}
                }
}
// this function pre loads the images for the new share button feature alert message
function preloadImages_alert(array) {
    if (!preloadImages_alert.list) {
        preloadImages_alert.list = [];
    }
    var list = preloadImages_alert.list;
    for (var i = 0; i < array.length; i++) {
        var img = new Image();
        img.onload = function() {
            var index = list.indexOf(this);
            if (index !== -1) {
                list.splice(index, 1);
            }
        }
        list.push(img);
        img.src = array[i];
    }
}
// ios share button alert
function feature_alert_check(alert_msg_data) {
    if (alert_msg_data == "unseen") {
        preloadImages_alert(["https://cdn2.myvidster.com/images/app/share_btn0.jpg", "https://cdn2.myvidster.com/images/app/share_btn2.jpg", "https://cdn2.myvidster.com/images/app/share_btn3.jpg"]);
        var new_feature = new Ext.MessageBox({
            width: 300,
            id: 'message-box-feature',
            floating: 1,
            margin: '80 0 0 0'

        });
        new_feature.show({
            //hideOnMaskTap: true,
            title: 'Bookmark videos from Safari',
            items: [{
                    html: '<img id="img_alert" src="https://cdn2.myvidster.com/images/app/share_btn0.jpg" style="width:260px;margin-bottom:4px;">  <br/>  <div id="matching_alert_text" style="text-align:center;margin:4px auto;color:#f5f5f5;">Share by clicking the share icon</div>   <a id="attach_onlick_here" onclick="next_alert_image();" style="color:white;margin:10px 40px;font-weight:bold;"> Next </a>  <a id="attach_onlick_here2" onclick="close_alert2();" style="color:white;margin:10px 40px;font-weight:bold;"> close </a>  <div style="height:8px;">&nbsp;</div>',
                    style: "text-align:center;"
                }
            ],
            buttons: [],
        });
    }
}

// Discover Feature
function discoverPeople() {
    getRecommendations();
    inDiscoverView = true;
    discoverLastPanel = 'Discover';
    glb_last_panel = Ext.Viewport.getActiveItem().id;
    glb_discover_tab = 'discover';
    Ext.Viewport.getActiveItem('disco')
    glb_discover_tab = 'contacts';
    recommendations_obj.load();
}
                                                                                                                                                                                                                      
function gotoRegistration(){
    Ext.Viewport.setActiveItem("UserRegistration");
    searchviewbackhack();
    custom_views_arr.push("UserRegistration");
    glb_custom_views_arr_data.push([0]);
}

function skipTutorial(){
    Ext.Viewport.setActiveItem("UserMenu");
    searchviewbackhack();
    custom_views_arr = [];
    custom_views_arr.push("Home");
    custom_views_arr.push("UserMenu");
    glb_custom_views_arr_data = [];
    glb_custom_views_arr_data.push([0]);
    glb_custom_views_arr_data.push([0]);
    if (v_orientation_chk === 0) {
      screen.unlockOrientation();
    }
}

function gotoHome(){
    setMask('Loading...');
}

function gotoUserScreen(){ 
    Ext.Viewport.setActiveItem("UserMenu");
    searchviewbackhack();
    custom_views_arr.push("UserMenu");
    glb_custom_views_arr_data.push([0]);
    if (v_orientation_chk === 0) {
      screen.unlockOrientation();
    }
}

function profileView(disp_name, collections, email_subscription, default_access, email_notification, website, location, gender, sexual_orientation, relationship_status, user_pro_member, userProMem){
    Ext.util.JSONP.request({
             url: 'https://api.myvidster.com/mobile_json2.php',
             callbackKey: 'callback',
             params: {
                 type: 'settings',
                 email: account.get('email'),
                 user_id: account.get('user_id'),
                 token: glb_msg_token,
                 pro_status: glb_is_pro_ckeck,
                 pkey: account.get('pw'),
             },
             callback: function(success, result) {
                  clearMask(); //clears mask after loading of options view
                 if (result.items) {
                     userSettings = result.items[0];
                     userEmail=account.get('email');
                     tempName=result.items[0].disp_name;
                     user_pro_member=result.items[0].user_pro_member;
                     userUrl=result.items[0].website;
                     userLocation=result.items[0].location;
                     userGender=result.items[0].gender;
                     sexualOrientation=result.items[0].sexual_orientation;
                     enable_disable_email_subscription=result.items[0].email_subscription;
                     enable_disable_default_access=result.items[0].default_access;
                     //enable_disable_safe_browsing=result.items[0].safe_browsing;
                     userVisibility=result.items[0].visibility;
                     profileForm.items[0].value = userEmail;
                     profileForm.items[1].value = tempName;
                     profileForm.items[2].value =userUrl;
                     profileForm.items[3].value =userLocation;
                     var userCollections = [];
                     var defaultSelectedColl = 0;
                     result.items[0].collections.map(function(coll, value) {
                      userCollections.push({text: coll.name, value:value });
                      if (coll.selected === 1) { //sets default selected collection
                         defaultSelectedColl = value;
                      }
                     });
                     profileForm.items[4].options = userCollections;
                     profileForm.items[4].value = defaultSelectedColl;
                     // setting gender
                     if (result.items[0].gender == 0) {
                        userSettings.gender = 0;
                        profileForm.items[6].items[0].checked = false;
                        profileForm.items[6].items[1].checked = false;
                     } else if (result.items[0].gender == 1 || result.items[0].gender === 'male') {
                        profileForm.items[6].items[0].checked = false;
                        profileForm.items[6].items[1].checked = true;
                        userSettings.gender = 1;
                     } else if (result.items[0].gender == 2 || result.items[0].gender === 'famale') {
                        profileForm.items[6].items[0].checked = true;
                        profileForm.items[6].items[1].checked = false;
                        userSettings.gender = 2;
                     }
                     // setting sexual orientation
                     if (result.items[0].sexual_orientation === 'straight' || result.items[0].sexual_orientation === 1) {
                        profileForm.items[7].items[0].checked = true;
                        profileForm.items[7].items[1].checked = false;
                        profileForm.items[7].items[2].checked = false;
                        userSettings.sexual_orientation= 1;
                     } else if (result.items[0].sexual_orientation === 'gay' || result.items[0].sexual_orientation === 2) {
                        profileForm.items[7].items[0].checked = false;
                        profileForm.items[7].items[1].checked = true;
                        profileForm.items[7].items[2].checked = false;
                        userSettings.sexual_orientation = 2;
                     } else if (result.items[0].sexual_orientation === 'bi' || result.items[0].sexual_orientation === 3) {
                        profileForm.items[7].items[0].checked = false;
                        profileForm.items[7].items[1].checked = false;
                        profileForm.items[7].items[2].checked = true;
                        userSettings.sexual_orientation = 3;
                     }
                     //setting email subscription
                     if (result.items[0].email_subscription === 0) {
                        profileForm.items[8].items[0].checked = false;
                        profileForm.items[8].items[1].checked = true;
                     } else if (result.items[0].email_subscription === 1) {
                        profileForm.items[8].items[0].checked = true;
                        profileForm.items[8].items[1].checked = false;
                     } 
                     //setting default access
                     if (result.items[0].default_access === 'public') {
                        profileForm.items[9].items[0].checked = true;
                        profileForm.items[9].items[1].checked = false;
                        profileForm.items[9].items[2].checked = false;
                     } else if (result.items[0].default_access === 'private') {
                        profileForm.items[9].items[0].checked = false
                        profileForm.items[9].items[1].checked = true;
                        profileForm.items[9].items[2].checked = false;
                     } else if (result.items[0].default_access === 'adult') {
                        profileForm.items[9].items[0].checked = false
                        profileForm.items[9].items[1].checked = false
                        profileForm.items[9].items[2].checked = true;
                     } 
                     //login_users or everyone
                     //setting visibility settings
                     if (result.items[0].visibility === '' ) {
                      profileForm.items[10].hidden = true; //hide visibility settting
                     }
                    else if (result.items[0].visibility === 'everyone' || result.items[0].visibility == 0) {
                        profileForm.items[10].items[0].checked = true;
                        profileForm.items[10].items[1].checked = false;
                     } else if (result.items[0].visibility === 'login_users' || result.items[0].visibility == 1) {
                        profileForm.items[10].items[0].checked = false;
                        profileForm.items[10].items[1].checked = true;
                     } 
                     //setting email notifications
                    if (result.items[0].email_notification.email_conn == '1') {
                        profileForm.items[11].items[0].checked = true;
                        userSettings.email_notification.email_conn = 1;
                     }  else if (result.items[0].email_notification.email_conn == '0') {
                        profileForm.items[11].items[0].checked = false;
                        userSettings.email_notification.email_conn = 0;
                     }
                    if (result.items[0].email_notification.email_video== '1') {
                        profileForm.items[11].items[1].checked = true;
                        userSettings.email_notification.email_video = 1;
                     }  else if (result.items[0].email_notification.email_video == '0') {
                        profileForm.items[11].items[1].checked = false;
                        userSettings.email_notification.email_video = 0;
                     }
                     var items=result.items;
                     var disp_name=items[0].disp_name;
                     var userProCheck = function userProMem(){
                        if (user_pro_member === 0)
                           return true
                        else
                           return false
                       };
                     userProMem = userProCheck();
                     var relationCheck = function(){
                          var relationshipStatus = result.items[0].relationship_status.toString(); 
                         if (relationshipStatus.toLowerCase() === 'single') {
                          return 1;
                         } else if (relationshipStatus.toLowerCase() === 'taken') {
                          return 2;
                         } else if (relationshipStatus.toLowerCase() === 'open_relationship') {
                          return 3;
                         } else if (relationshipStatus === '0') {
                          return 4;
                        }  
                     };
                     // possible fix for iPad selectfield error
                    relationshipOptions =  [
                      {text: 'Single', value: 1 },
                      {text: 'Taken', value: 2 },
                      {text: 'Open Relationship', value: 3 },
                      {text: 'Do Not Show', value: 4 },
                    ];
                     profileForm.items[5].options = relationshipOptions;
                     profileForm.items[5].value = relationCheck();
                     // profileForm.items[1].disabled = userProMem; //temporary disable check
                     profileForm.items[1].disabled = true;
                     Ext.getCmp('UserProfile').setItems([userProfileToolbar, profileForm]);
                     Ext.Viewport.setActiveItem('UserProfile');
                     searchviewbackhack();
                     custom_views_arr.push("UserProfile");
                     glb_custom_views_arr_data.push([0]);
                 } else {
                     myvAlert("Error!");
                 }
             }
         });
}

var disco_func = function() {
    inDiscoverView = true;
    discoverLastPanel = 'Disco';
    discoverForUser = [{}]; //do not comment out- helps to clear out list
        Ext.util.JSONP.request({
         url: 'https://api.myvidster.com/mobile_json2.php',
         callbackKey: 'callback',
         params: {
             type: 'discover_discover',
             user_id: account.get('user_id'),
             token: glb_msg_token,
             pkey: account.get('pw'),
         },
         callback: function(success, result) {
              clearMask(); //clears mask for discover tab in discover view
             if (result) {
                result.items.map(function(contact) {
                    discoverForUser.push({
                        user_id: contact.dis_user_id,
                        disp_name: contact.dis_disp_name,
                        profile_photo: contact.dis_profile_photo,
                        followers: contact.dis_follower_cnt.toString(),
                        followStatus: contact.dis_status === 'follow'?'0':'1',
                        followButtonText: contact.dis_status === 'follow'?'follow':'unfollow',
                    }); 
                });
                
                discover_obj.getData().clear();
                discoverForUser.shift();
                discover_obj.setData(discoverForUser);
                Ext.Viewport.add(discoverView); //creates new view and overwrites previous Contacts
                Ext.Viewport.setActiveItem('Disco'); //routes to new view*/
                searchviewbackhack();
                custom_views_arr.push("Disco");
                glb_custom_views_arr_data.push([0]);
                document.getElementById('discover_contacts_btn01').style.background = 'transparent';
                document.getElementById('discover_recommended_btn01').style.background = 'transparent';
                document.getElementById('discover_discover_btn01').style.background = 'green';
             } else {
                 myvAlert("Error!");
            }
         }
       });
    };

//retrieves list of contacts from user phone
function getContactsList() {
     if (Ext.os.is('Android') || Ext.os.is('iOS')) {
        setMask('Please wait...');
        inDiscoverView = true;
        discoverLastPanel = 'Contacts';

        checkContacts = true; 
        // added by sterling dev      
            try {
                var options = new ContactFindOptions();
                options.multiple = true;
                var fields = [navigator.contacts.fieldType.displayName, navigator.contacts.fieldType.name, navigator.contacts.fieldType.emails];
                navigator.contacts.find(fields, onSuccess_cts, onError_cts, options);
            } catch (err) {
                myvAlert('Please update your app');
                clearMask();
            }
    } else {
        myvAlert("Please install our native app for this feature.");
    }
}

function parseContactsFromPhone(contacts) {
    var formattedContacts = [];
    for (var i = 0; i < contacts.length; i++) {
        if (contacts[i].phoneNumbers != null) {
            var cntnum2 = contacts[i].phoneNumbers[0].value;
            var lowerc_t = contacts[i].name.formatted;
            // added by sterling dev
            try {
                lowerc_t = lowerc_t.toLowerCase(lowerc_t);
            } catch (err) {}
            formattedContacts.push({
                name: lowerc_t,
                phone_number: cntnum2
            });
        }
    }
    return formattedContacts;
}    
// grabs recommendations for user based on friends 
function getRecommendations() {
    recommendationsForUser = [{}]; //do not comment out- helps to clear out list
    recommendations_obj.getData().clear(); //clear out previous list of contacts
    Ext.util.JSONP.request({
     url: 'https://api.myvidster.com/mobile_json2.php',
     callbackKey: 'callback',
     params: {
         type: 'discover_recommendations',
         user_id: account.get('user_id'),
         token: glb_msg_token,
         pkey: account.get('pw'),
     },
     callback: function(success, result) { 
        clearMask(); //clears mask for recommendations in discover view
         if (result) {
            result.items.map(function(contact) {
                recommendationsForUser.push({
                    user_id: contact.dis_user_id,
                    disp_name: contact.dis_disp_name,
                    profile_photo: contact.dis_profile_photo,
                    followers: contact.dis_follower_cnt.toString(),
                    followStatus: contact.dis_status === 'follow'?'0':'1',
                    followButtonText: contact.dis_status === 'follow'?'follow':'unfollow',
                }); 
            });
            recommendations_obj.getData().clear();
            recommendationsForUser.shift();
            recommendations_obj.setData(recommendationsForUser);
            Ext.Viewport.add(recommendationsView); //creates new view and overwrites previous Contacts
            Ext.Viewport.setActiveItem('Discover'); //routes to new view*/
            searchviewbackhack();
            custom_views_arr.push("Discover");
            glb_custom_views_arr_data.push([0]);
                document.getElementById('discover_contacts_btn02').style.background = 'transparent';
                document.getElementById('discover_recommended_btn02').style.background = 'green';
                document.getElementById('discover_discover_btn02').style.background = 'transparent';
         } else {
              Ext.Viewport.setActiveItem('EmptyRecommendations'); //routes to new view
              searchviewbackhack();
              custom_views_arr.push("EmptyRecommendations");
              glb_custom_views_arr_data.push([0]);
              document.getElementById('discover_contacts_btn05').style.background = 'transparent';
              document.getElementById('discover_recommended_btn05').style.background = 'green';
              document.getElementById('discover_discover_btn05').style.background = 'transparent';
        }
     }
   });
}
// checks to see if contact from user phone already has an account with myVidster
function checkIfContactsHaveAccount(contacts) {
    contactsWithAccounts = [{}]; //do not comment out- helps to clear out list
    list_contactAccounts_obj.getData().clear(); //clear out previous list of contacts
    var cappedContacts = contacts.slice(0, 1000); //cap contact length to 1000
    Ext.util.JSONP.request({
     url: 'https://api.myvidster.com/mobile_json2.php',
     callbackKey: 'callback',
     params: {
         type: 'discover_contacts',
         user_id: account.get('user_id'),
         pkey: account.get('pw'),
         token: glb_msg_token,
         contacts: Ext.encode(cappedContacts),
     },
     callback: function(success, result) { 
        clearMask(); //clears mask for contacts tab of discover view
         if (result) { 
            result.items.map(function(contact) { 
              if (contact.dis_user_id) {
                 contactsWithAccounts.push({
                    user_id: contact.dis_user_id,
                    disp_name: contact.dis_disp_name,
                    profile_photo: contact.dis_profile_photo,
                    followers: contact.dis_follower_cnt.toString(),
                    followStatus: contact.dis_status === 'follow'?'0':'1',
                    followButtonText: contact.dis_status === 'follow'?'follow':'unfollow',
                });                
              }
            });
              if (contactsWithAccounts.length === 1) { // if no contacts have a myVidster account
                Ext.Viewport.setActiveItem('EmptyContacts'); //routes to new view
                searchviewbackhack();
                custom_views_arr.push("EmptyContacts");
                glb_custom_views_arr_data.push([0]);
                document.getElementById('discover_contacts_btn04').style.background = 'green';
                document.getElementById('discover_recommended_btn04').style.background = 'transparent';
                document.getElementById('discover_discover_btn04').style.background = 'transparent';  
              } else {
                list_contactAccounts_obj.getData().clear();
                contactsWithAccounts.shift();
                list_contactAccounts_obj.setData(contactsWithAccounts);
                Ext.Viewport.add(contactsView); //creates new view and overwrites previous Contacts
                Ext.Viewport.setActiveItem('Contacts'); //routes to new view 
                searchviewbackhack(); 
                custom_views_arr.push("Contacts");  
                glb_custom_views_arr_data.push([0]);
                document.getElementById('discover_contacts_btn03').style.background = 'green';
                document.getElementById('discover_recommended_btn03').style.background = 'transparent';
                document.getElementById('discover_discover_btn03').style.background = 'transparent';         
              }
         } else {
              Ext.Viewport.setActiveItem('EmptyContacts'); //routes to 'no accounts found' view
              searchviewbackhack();
              custom_views_arr.push("EmptyContacts");  
              glb_custom_views_arr_data.push([0]);
              document.getElementById('discover_contacts_btn04').style.background = 'green';
              document.getElementById('discover_recommended_btn04').style.background = 'transparent';
              document.getElementById('discover_discover_btn04').style.background = 'transparent';
        }
     }
   });
}
// follow a contact from user phone that already has an account
function followContactWithAccount(id) {
    //isFollow should be '0' or '1' (0: not following, 1: already following)
    uniquify = Math.random();
    var pkey = account.get('pw'); //get user credentials
    var user_id = account.get('user_id'); //get user credentials
    var isFollow;
    //retrieve followStatus from contact - Contacts
    contactsWithAccounts.map(function(contact) {
        if (contact.user_id == id) {
            isFollow = contact.followStatus;
        }
    });
    //retrieve followStatus from contact - Recommendations
    recommendationsForUser.map(function(contact) {
        if (contact.user_id == id) {
            isFollow = contact.followStatus;
        }
    });   
    // discover view
    discoverForUser.map(function(contact) {
        if (contact.user_id == id) {
            isFollow = contact.followStatus;
        }
    }); 
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'follow',
            user_id: user_id,
            pkey: pkey,
            follow_type: 'User',
            id: id,
            token: glb_msg_token,
            isFollow: isFollow, //needs to be '0' or '1'
            uniquify: uniquify
        },
        callback: function(success, result) {
            var response = result.items[0]['response'];

            myvAlert(response);
            var userButton = document.getElementById(id);
            var userFollowing = document.getElementById('userFollowingCount');

            if (isFollow == '0') { //0 = follow user
                    userButton.innerHTML = 'unfollow'; //change button text
                    userFollowingCount++;
                    contactsWithAccounts.map(function(contact) {   //update contact status after user action                   
                        if (contact.user_id == id) {
                            contact.followStatus = '1';
                            contact.followButtonText = 'unfollow';

                        }
                    });
                    recommendationsForUser.map(function(contact) {   //update contact status after user action                   
                        if (contact.user_id == id) {
                            contact.followStatus = '1';
                            contact.followButtonText = 'unfollow';

                        }
                    });
            } else { // to unfollow
                    userButton.innerHTML = 'follow'; //change button text
                    userFollowingCount--;
                    contactsWithAccounts.map(function(contact) { //update contact status after user action
                        if (contact.user_id == id) {
                            contact.followStatus = '0';
                            contact.followButtonText = 'follow';
                        }
                    });  
                    recommendationsForUser.map(function(contact) { //update contact status after user action
                        if (contact.user_id == id) {
                            contact.followStatus = '0';
                            contact.followButtonText = 'follow';
                        }
                    });                      
            }
            userFollowing.innerHTML = userFollowingCount;
        }
    });
}

function retrievePhoneContacts() {
    var options = new ContactFindOptions();
    options.multiple = true;
    var fields = [navigator.contacts.fieldType.displayName, navigator.contacts.fieldType.name, navigator.contacts.fieldType.emails];
    navigator.contacts.find(fields, getRecommendations, onError_cts, options);

}

function sendSMSInviteToContact(phoneNum) {
    var numberToSend = '';
    var contactName = '';
    contactsWithNoAccounts.map(function(contact) {
        if (contact.phoneNum === phoneNum.toString()) {
            numberToSend = contact.extra;
            contactName = contact.data;
        }
    });
    //send invite to server for tracking
    Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'save_invite',
            user_id: account.get('user_id'),
            pkey: account.get('pw'),
            token: glb_msg_token,
            contact: Ext.encode([{contact_name: contactName, contact_number: numberToSend}])
        },
        callback: function(success, result) {
        }
    });    

    if (typeof device != 'undefined' && Ext.os.is('Android')) {
        window.location = "sms:" + numberToSend + "?body=I would like you to check out MyVidster https://www.myvidster.com/app/share";
    } else if (typeof device != 'undefined' && Ext.os.is('iOS')) {
        window.location = "sms:" + numberToSend + "&body=I would like you to check out MyVidster https://www.myvidster.com/app/share";
    }
}

var pro_sz = 'font-size:1.35em; margin-top: 20vh';
var restore_purchase_btn = '<div id="restore_btn" style="width:80%;margin: 50px auto 20px;background-color:#12D12D;'+ 
'border-radius:5px;padding:12px 0px;font-size:1.1em;color:white;font-weight:bold;font-family:helvetica; text-align:center"' +
' onclick="restore_purchase()"> Restore Purchases </div>';
var page = {
   html: '<div style="' + pro_sz + '"><div style="text-align:center;margin:18px 20px;"> <p><b>' + 
   'Your membership has expired. If you did not cancel your membership, please click restore purchases.' 
   + ' <br> <div style="margin:20px;"> (iTunes account login required)</div></b></p></div>' + restore_purchase_btn,
};
var successMessage = {
   html: '<div style="' + pro_sz + '"><div style="font-size:1.20em;text-align:center;margin:23vh 20px;"> <p><b>' + 
   'Your purchases have been restored. If you are still having issues with your membership, please contact us.' 
   + '</b></p></div>',
};
function displaySuccessRestoredMessage() {
    Ext.getCmp('Resubscribe').removeAll();
    Ext.getCmp('Resubscribe').add(successMessage);
}

function checkReceipt(receipt) {
    var http = new XMLHttpRequest();
    var url = "https://api.myvidster.com/mobile_json2.php";
    var params = "type=iap_receipt_check&user_id=" + account.get('user_id') + "&pkey=" + account.get('pw') + "&receipt=" + encodeURIComponent(receipt)+"&token="+ glb_msg_token;
   
    http.onreadystatechange = function() {
        // if returns 1, it means user's subscription has expired
        if (http.responseText) {
            if (JSON.parse(http.responseText).items[0].expired === 1) {
                Ext.getCmp('Resubscribe').add(page);
                Ext.Viewport.setActiveItem('Resubscribe'); // send to resubscribe screen
                searchviewbackhack();
                custom_views_arr.push("Resubscribe");  
                glb_custom_views_arr_data.push([0]);
            }
          
        } 
    };

    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    application.set("receipt", receipt);
    application.setDirty();
    app_store.sync();
    application = app_store.first();
    http.send(params);
} 

function packAndSendUserSettings(newSettings) {
  Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
          type: 'update_settings',
          user_id: account.get('user_id'),
          pkey: account.get('pw'),
          token: glb_msg_token,
          settings: Ext.encode(newSettings)
        },
        callback: function(success) {
          onBackKeyDown();
          clearMask();
      }
  });    
}    

function promptTouchID() {
  touchid.authenticate(
  function() { // if successful authentication
    glb_back_button = true;  
   
        custom_views_arr = [];
        custom_views_arr.push('Home');
        var array_data = [0];
        glb_custom_views_arr_data = [];
        glb_custom_views_arr_data.push(array_data);
        Ext.Viewport.setActiveItem('Home');
        load_homepage(0, 0);

  },
  function() {
  },
  'Scan your fingerprint to unlock');
}

function startFingerprintPrompt() {
  if (enabledFingerprintAuth) {
    if (Ext.os.is('iOS')) {
      if (typeof window.touchid != 'undefined') {
        touchid.checkSupport(function() {
          promptTouchID();
        }, function() {
    
        });         
      } 
    }
    if (Ext.os.is('Android') && !isPixelPhone) {
      if (typeof window.FingerprintAuth != 'undefined') {
        FingerprintAuth.isAvailable(function(result) {
          if (result.isAvailable) {
              promptAndroidTouchID(); 
          }
        }, function() {});          
      }
    }    
  }
}

function checkFingerprintSettings() {
  if (localStorage.getItem('fingerprintAuth')) {
    if (localStorage.getItem('fingerprintAuth') === 'true') {
      enabledFingerprintAuth = true;
      Ext.getCmp('enable_fingerprint').items.items[0].check();      
    } else {
      enabledFingerprintAuth = false;
    }
  } else {
    enabledFingerprintAuth = false;
  }
}

function promptAndroidTouchID() {
   var encryptConfig = { clientId: 'myVidster' }; // See config object for required parameters
  FingerprintAuth.encrypt(encryptConfig,  
    function() {
      glb_back_button = true;  

        custom_views_arr = [];
        custom_views_arr.push('Home');
        var array_data = [0];
        glb_custom_views_arr_data = [];
        glb_custom_views_arr_data.push(array_data);
        Ext.Viewport.setActiveItem('Home');
        load_homepage(0, 0);
    

    }, 
    function() {

    });
}

function getAccountName() {
  return account.get('disp_name');
}

function redirectToFollowingScreen() {
  setMask('Please wait...');
  //goToFollowingScreen = true;
  list_videos('subscription_list', account.get('user_id'), 0);

}

function adjustLandscapeForIPhoneX() {
  Ext.Viewport.doSetWidth('95%');
  Ext.getBody().setLeft(35);
}

function revertFromLandscapeIPhoneX() {
  Ext.select(".x-toolbar-light").applyStyles("padding-top: 30px");
  Ext.Viewport.doSetWidth('100%');
  Ext.getBody().setLeft(0);
}

function adjustLandscapeForIPhone() {
   Ext.select(".x-toolbar-light").applyStyles("min-height: 3.0em; padding-top: 0px");
   Ext.select(".x-tabbar-light").applyStyles("min-height: 2.8em");    
}

function revertFromLandscapeIPhone() {
  Ext.select(".x-toolbar-light").applyStyles("min-height: 4.4em");
  Ext.select(".x-tabbar-light").applyStyles("min-height: 4.2em");
}
function populateSendVideoList(type, searchTerm) {
  var limit = 9;

  if (glb_device_type == 'tablet') {
      limit = 12;
  }

  var query = '';

  if (searchTerm) {
    query = searchTerm;
  } else {
    query = type +':'+ account.get('disp_name');
  }

  glb_page_number = 1;
  setMask();
  Ext.util.JSONP.request({
    url: 'https://api.myvidster.com/moblie_search.php',
    callbackKey: 'callback',
    params: {
      q: query, // q is for query
      filter_by: 'profiles',
      scope: 'profiles',
      cfilter_by: glb_filter_by,
      family_filter: family_filter,
      id: account.get('user_id'),
      user_id: account.get('user_id'),
      pkey: account.get('pw'),
      start: 0,
      page: 1, // pagination
      uniquify: Math.random(),
      html5: glb_HTML5_only,
      token: glb_msg_token,
      limit: limit,
      sortby: 'relevant',
      device_type: glb_device_type,
    },
    callback: function(success,payload) {
      var results = [];

      if (payload && payload.items) {
        payload.items.map(function(item) {
          results.push({
            user_id: item.user_id,
            disp_name: item.disp_name,
            user_thumbnail: item.user_thumbnail,
          });
        });          
      }

      if (!results.length) {
       if (type === 'following') {
          myvAlert("You are not following anyone.");
        } else if (type === 'followers') {
          myvAlert("You have no followers.");
        }
      } 

      glb_page_number = 2;

      send_video_obj.setParams({
        q: query, // q is for query
        filter_by: 'profiles',
        scope: 'profiles',
        cfilter_by: glb_filter_by,
        family_filter: family_filter,
        id: account.get('user_id'),
        user_id: account.get('user_id'),
        pkey: account.get('pw'),
        token: glb_msg_token,
        start: 0,
        page: glb_page_number, // pagination
        uniquify: Math.random(),
        html5: glb_HTML5_only,
        limit: limit,
        sortby: 'relevant',
        device_type: glb_device_type,
      });       

      send_video_obj.getData().clear();
      send_video_obj.setData(results);

      Ext.Viewport.setActiveItem('SendVideo');

      // only selected button is green
      if (type === 'followers') {
        document.getElementById('send_following_video_btn01').style.background = 'transparent';
        document.getElementById('send_follower_video_btn01').style.background = 'green';
      } else if (type === 'following') {
        document.getElementById('send_follower_video_btn01').style.background = 'transparent';
        document.getElementById('send_following_video_btn01').style.background = 'green';
      } else {
        document.getElementById('send_follower_video_btn01').style.background = 'transparent';
        document.getElementById('send_following_video_btn01').style.background = 'transparent';    
      }
      // resets page so refreshing will work after 1st time refreshing list
      send_video_var.store.currentPage = 1; 
      clearMask();       
    }
  });
}

// api to call to send video to chat history
function sendVideoViaChat(user) {
  var name = account.get('disp_name');
  var message = name + ' would like to share a video with you: \n\n' + 
  Ext.String.htmlDecode(selectedVideoTitle) + " https://www.myvidster.com/video/"+selectedVideoId+"/";
  
  //setTimeout(Ext.getCmp('send_video_var').deselectAll(), 2000);
  pusher_test(account.get('user_id'), user, message, selectedVideoId);

}

// when user clicks on chat message with video, sent by another user
function redirectToVideo(video_id) {
  getVideo(video_id,'videobyid');
}

function selectVideoSendMethod(video_title_encoded, video_id) {
    if (account.get('user_id')) {
        if (typeof device != 'undefined' && Ext.os.is('Android')) {
            navigator.notification.confirm('Select an option below.',
                function(buttonIndex) {
                    if (buttonIndex == 2) {
                      // send via web
                      share_video(video_title_encoded, video_id);
                    } else if (buttonIndex == 1) {
                        populateSendVideoList('followers', null);
                        Ext.Viewport.animateActiveItem('SendVideo', {
                            type: 'fade',
                        });
                        searchviewbackhack();
                        custom_views_arr.push("SendVideo");
                        glb_custom_views_arr_data.push([video_title_encoded, video_id]);
                        selectedVideoTitle = video_title_encoded;
                        selectedVideoId = video_id;
                    }
                }, 'Send To Friend', 'MyVidster User, Everyone else');
        } else {
            var msg = new Ext.MessageBox({
                width: 300,
                floating: 1,
                margin: '80 0 0 0'
            });

            msg.show({
                title: 'Send Video',
                msg: 'Select an option below.',
                buttons: [{
                    text: 'Send Via Web',
                    itemId: '0'
                }, {
                    text: 'Send Via Chat',
                    itemId: '1'
                }],
                fn: function(response) {
                    // response is string value '1' or '0'
                    if (response == 1) {
                        
                        populateSendVideoList('followers', null);
                        Ext.Viewport.animateActiveItem('SendVideo', {
                            type: 'fade',
                        });
                        searchviewbackhack();
                        custom_views_arr.push("SendVideo");
                        glb_custom_views_arr_data.push([video_title_encoded, video_id]);
                        selectedVideoTitle = video_title_encoded;
                        selectedVideoId = video_id;

                    } else if (response == 0) {
                      share_video(video_title_encoded, video_id);
                      // send via web
                    }
                }
            });
        }
    } else {
      // if user is not logged in, redirect to user login
      Ext.Viewport.setActiveItem('UserLogin');
      searchviewbackhack();
      custom_views_arr.push("UserLogin");
      glb_custom_views_arr_data.push([0]);
    }
}

function cancelCountdown() {
  countdownCancelled = true;
  setTimeout(function() {
    getVideo(glb_current_video, glb_video_type);
  },1000);

  setTimeout(function() {
    countdownCancelled = false;
    clearInterval(masterCounter);
  },4000);
}

function cancelCountdownForBrowserVideo() {
  // no reload for current video page
  // opens browser for video 

  countdownCancelled = true;
  setTimeout(function() {
    countdownCancelled = false;
    clearInterval(masterCounter);
  },4000);
}


function cancelCountdownForBrowserVideoAndOpenBrowser() {
  load_webpage(counter_url);
  cancelCountdownForBrowserVideo();
  document.getElementById('countdownClick').onclick = cancelCountdownForBrowserVideo;
}

function checkAndroidLegacyVideoForError(url, first) {
  setMask('Loading...');
  Ext.Ajax.request({
    url: url, 
    method: 'HEAD', // only request headers
    success: function(response) {
      AndroidLegacyPlay(url);
      clearMask();
    },
    failure: function(response) {
      if (first) { // if first video in sequence, try opening child browser
        load_webpage(counter_url);
      } else { // if not first, report error and play video error

        video_err(error_obj.master_id, error_obj.video_id, error_obj.ip);
        AndroidLegacyPlay('https://cdn2.myvidster.com/app/videoerror.mp4');
      }
      clearMask();
    }
  });
}

function ios_fallback_player(counter_url,video_id,master_id,ip) {
	var source = glb_myPlayer.currentSrc();
	
	if(glb_vid_err_handle) {
		glb_myPlayer.src('https://cdn2.myvidster.com/vids/videoplayback_sm.mp4');
		glb_myPlayer.load();
		glb_myPlayer.play();

		cordova.plugin.http.sendRequest(source, { method: 'head' }, 
			function(response) {
				var options = {
					successCallback: function() {
						//getVideo(glb_current_video, glb_video_type, true);
						report_video(video_id, master_id, ip, "ios_fallback_player", true);
						  if(glb_play_playlist) {
							myvAlert('Sorry, play next is not supported by this video.');
						  }
					},
					errorCallback: function(errMsg) {
					  
					},
					shouldAutoClose: true,  // true(default)/false
					controls: true // true(default)/false. Used to hide controls on fullscreen
				};
				window.plugins.streamingMedia.playVideo(source,options);
				
			}, function(error) {
				load_webpage(counter_url);
				getVideo(glb_current_video, glb_video_type, true);
		});
	}
	else {
		getVideo(glb_current_video, glb_video_type, true);
		glb_vid_err_handle = true;
	}
}


function clearCountdown() {
  if (masterCounter) {
    clearInterval(masterCounter);
    clearInterval(masterVideoCounter);
  }
}

// for Android only and legacy player
function resumePlayListLegacyPlayer() {
  setTimeout(function() {
    startBasicCountdown();
  },1000);
}

function promptForTouchIDEnable() {
  var authType = isIphoneX? 'FaceID' : 'TouchID';
  navigator.notification.confirm('Would you like to enable '+authType+'?',
    function(buttonIndex) {
        if (buttonIndex == 1) { //yes to disable
          localStorage.setItem('fingerprintAuth', true); 
          enabledFingerprintAuth = true;
          Ext.getCmp('enable_fingerprint').getItems().items[0].check();
        }
        if (buttonIndex == 2) {//no to disable
          localStorage.setItem('fingerprintAuth', false); 
          enabledFingerprintAuth = false;
          Ext.getCmp('enable_fingerprint').getItems().items[1].check();
        }
    }, 'Enable ' + authType +'?', 'yes,no'); 
}

function clearAndroidPlaySprite() {}

function checkHeightForError() {
  if (screen.availHeight != screen.height) {
    if (screen.availHeight == 375 && screen.availWidth == 812) {
      revertFromLandscapeIPhoneX();
      Ext.Viewport.setHeight('98%');            
    } else {
      Ext.Viewport.setHeight('100%'); 
    }
  }
}

function isFullscreenNow() {
	var isFullscreenNow;
	if(Ext.os.is('iOS')) {
		isFullscreenNow = glb_myPlayer.isFullscreen();
	}
	else {
		if (
			document.fullscreenElement || /* Standard syntax */
			document.webkitFullscreenElement || /* Chrome, Safari and Opera syntax */
			document.mozFullScreenElement ||/* Firefox syntax */
			document.msFullscreenElement /* IE/Edge syntax */
		) {
			isFullscreenNow = true;
		}
		else {
			isFullscreenNow = false;
		}
	}
	return isFullscreenNow;
}

function fullscreen_handler() {
	AndroidFullScreen.immersiveMode(Ext.emptyFn, Ext.emptyFn);
}

function makeImmersive() {
	glb_immersiveMode = true;
}

function init_pip() {
	glb_pip_support = true;	

	PictureInPicture.onPipModeChanged(function(success){
		PictureInPicture.isPip(function(success){
			if(success == 'false' && glb_is_pip) {
				glb_myPlayer.exitFullscreen();
				glb_is_pip = false;
			}
			else {
				glb_is_pip = true;
			}
			}, function(error){
					//code to execute if pip mode check fails
		});
		}, function(error){
	});
}

var glb_mass_edits = '';
function save_edits(id){
      Ext.getCmp('list_videos_var').deselectAll();
      if(glb_mass_edits.match(id.toString())){
        glb_mass_edits = glb_mass_edits.replace(id.toString(), '');
      }else{
        glb_mass_edits = glb_mass_edits + ',' + id.toString();
      }
        glb_mass_edits = glb_mass_edits.replace(',,', ',');
        var c = document.getElementsByClassName('massedit');
        var j;
        for(j = 0; j < c.length; j++){
          if(glb_mass_edits.match( (c[j].id).toString()) ){
              c[j].checked = true;
          }
          else{
            c[j].checked = false;
          }
        }
}

function native_vid_download(uri) {		//mrg
	if(uri.toLowerCase().indexOf(".mp4") >= 0) {
		if(!Ext.os.is('iOS')) {
			var r = confirm("Download to app has been retired, you can now download videos to your phone's native storage. This video will now open in your native web browser then click Download from the more button!  You can also close the native web browser whille the video is downloading, you can monitor your download in your notifications, enjoy.");
		}
		else {
			var r = confirm("Download to app has been retired, you can now download videos to your phone's native storage. This video will now open in your native web browser. Then swipe down to pause the video. Now click the share button in the bottom bar, scroll down the list of share option and select Save to files and then save!  If you close the native web browser before the video is downloading, it may break the download.  To play your videos go the Files app on your phone, enjoy.");
		}
		
		if(r) {
			glb_myPlayer.pause();
			
			if (typeof device != 'undefined') {
				cordova.InAppBrowser.open(uri, '_system', 'location=yes');
			}
			else {
				window.location.assign(uri);
			}
		}
	}
	else {
		myvAlert("File type is not supported for downloading to native storage, use the app's storage.");
	}
 }


