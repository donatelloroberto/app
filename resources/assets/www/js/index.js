//global
var glb_seb_back = 0;
var custom_views_arr = [];
var glb_custom_views_arr_data = [];
var search_back_preserve_hack = 0;
var glb_crypto_plan = '';
var glb_btc_address = '';
var glb_is_pro_ckeck = '';
var glb_logincheck_done = false;
var glb_homecheck_done = false;
var glb_already_loggedin = false;
var glb_login_cloud_vids = '';
var glb_login_video_count = '';
var glb_login_follower_count = '';
var glb_login_following_count = '';
var glb_login_family_filter = '';
var glb_login_disp_name = '';
var glb_login_zz = '';
var glb_lockscreen_shown = 0;
var glb_lock_check_done = '';

var glb_set_family_filter = true;

var video_chat_session = '';
var video_chat_publisher = '';
var glb_cancel_videocall = '';

var glb_msg_token = false;
var glb_pusher_login_hack = 0;

var glb_gallery_id = '';
var glb_addbtn_fix = 0;
var glb_addbtn_fix2 = 0;
var glb_top_channelcollection_btn = '';
var glb_html_chrome_seeker = '';
var glb_seek_carousel_h = '';
var glb_topPaddingThumbnails = '';
var glb_bottomPaddingThumbnails = '';
var glb_seeker_duration = '';
var youtubeInstalled = false;
var flashInstalled = false;
var glb_loadingStatus = 0;
var glb_home_count = 9;
var glb_version = "7.00";
var glb_unlock = 0;
var glb_more_id;
var glb_search_page = 1;
var glb_search_more_id;
var glb_user_count = 9;
var glb_user_more_id;
var family_filter = 1;
var glb_ad_resume = '';
var glb_video_duration = '';
var glb_thumbs_array = '';
var premium_slideshow_index = 0;
var glb_chrome_cast_setting = '';
var userEmail = 'help@myvidster.com';
var tempName = 'dylan';
var userUrl = 'https://www.myvidster.com';
var userLocation = "Everywhere, USA";
var collectionName = "My Collection";
var collectionSelected = 1;
var relationStatus = "Single";
var userGender = 1;
var sexualOrientation = "Straight";
var enable_disable_email_subscription = 1;
var enable_disable_safe_browsing = 1;
var userVisibility = "Everyone";
var emailNotifications = 1;
var iap_receipt = '';
var iap_tier = '';
var iap_transID = '';
var iap_sig = '';
var isIphoneX = '';
var isPixelPhone = false;
var user_pro_member = '';
var userProMem;
var inDiscoverView = false;
var discoverLastPanel = '';
var currentUserId = '';
var highlightbutton = 'recommendations';
var isLandscape = false;
var selectedVideoTitle = '';
var selectedVideoId = '';
var HKVideoPlayer;
var countdownMessage = 'Next Video Playing in 10...';
var countdownCancelled = false;
var icon_size;
var icon_font_size;
var iconMargin;
var masterCounter;
var masterVideoCounter;
var counter_url;
var counterNumber = 10;
var enabledFingerprintAuth = false;
var glb_source_playback;
var playingLegacyPlayer = false;
var glb_video_file;
var error_obj = {};
var glb_cloud_volume = 1;
var glb_pip_support = false;
var glb_is_pip = false;
var glb_immersiveMode = false;

var widthPortrait = window.innerWidth;
var heightPortrait = window.innerHeight;

var glb_profile_check = 2;

var widthLandscape = window.innerHeight;
var heightLandscape  = window.innerWidth;
// used to detect if device is iPhoneX
if ((window.innerWidth == 375 && window.innerHeight == 734) ||
  (window.innerWidth == 724 && window.innerHeight == 354)) {
  isIphoneX = true;
}

var glb_ios_pro_help = '';
var glb_ios_pro_help2 = '';
var glb_video_call_display = false;

if (Ext.os.is('iOS')) {
    glb_video_call_display = true;
    glb_videoCall = 1;
    glb_ios_pro_help =
                    '<div class="div_borderer"> <label for="showblock20"><b>'+
                    ' <div style="padding-top:10px;padding-bottom:10px;"> I paid for Pro but did not get it </div>'+
                    ' </b></label> <input type="checkbox" id="showblock20" class="ch_hd"/> <div id="block20">'+
                    ' <div class="div_styler"> Please open the app, select Pro Membership in'+
                    ' the User screen then click on the Restore Purchases button'+
                    ' (provide your app store login and you will not be double charged). </div> </div>'+
                    ' </div> ';

    glb_ios_pro_help2 =
                    '<div class="div_borderer"> <label for="showblock21"><b>'+
                    ' <div style="padding-top:10px;padding-bottom:10px;"> My PRO failed to auto renew </div>'+
                    ' </b></label> <input type="checkbox" id="showblock21" class="ch_hd"/> <div id="block21">'+
                    ' <div class="div_styler"> Please open the app, select Pro Membership in the User screen then click on the <b>Restore Purchases</b> button (provide your app store login and you will not be double charged). </div> </div>'+
                    ' </div> ';

  if (typeof window.indexedDB != 'undefined') {
    if ((window.innerWidth == 375 && window.innerHeight == 812) ||
      (window.innerWidth == 812 && window.innerHeight == 375)) {
      isIphoneX = true;
      window.innerHeight = window.innerHeight;

    }
  }
}

var myVarTimeOut;

var followMode = '';
var followTitle = 'Following';
var filterMode = '';
var filterValue = '';
var globalStore;
var globalStoreSearchable;
var globalStoreSet = false;
var checkContacts = false;
var contactsWithAccounts = [{}];
var recommendationsForUser = [{}];
var discoverForUser = [{}];
var contactsView;
var discoverView;
var sendVideoView;
var recommendationsView;
var emptyContactsView;
var emptyRecommendationsView;
var contactsWithNoAccounts = [];
var userSettings = {};
var searchType = 'myVidster';
var userFollowerCount = null;
var userFollowingCount = null;
var stored_user_pic_header_html;
var glb_page_number = 1;
var temp_test = false;
var sendingMessage = false;


var default_html5_playback = 1; //glb_html5_playback
var glb_play_playlist = 0;

var v_orientation_chk = 1;

var glb_device_type = '';
var agent_mobile = (navigator.userAgent).toLowerCase().indexOf('mobile') > -1;
var agent_mobile2 = (navigator.userAgent).toLowerCase().indexOf('ipad') > -1;
if (Ext.os.is.Desktop) {
    glb_device_type = 'desktop';
    glb_videoCall = 1;
    glb_video_call_display = true;
} else if (agent_mobile && !agent_mobile2) {
    glb_device_type = 'phone';
} else {
    glb_device_type = 'tablet';
    v_orientation_chk = 0;
}

var glb_supportedFunction_var = false;
var glb_supportedFunction2_var = false;


var glb_current_video;
var glb_video_history = [];
var glb_video_history_limit = 60;
var glb_video_history_chk = 1;
var glb_next_video;
var glb_prev_video;
var glb_video_type;
var glb_channel_id;
var glb_last_panel = 'Home';
var glb_video_panel = 'Video';
var glb_sortby = 'Recent';
var account;
var items;
var result;
var application;
var glb_last_list = [];
var glb_current_list = [];
var glb_back_data = [];
var glb_panel_w;
var glb_panel_h;
var glb_file_name;
var glb_upload_video_title;
var glb_upload_channel;
var glb_upload_video_tags;
var glb_upload_access;
var glb_nofity_cnt = 1;
var glb_HTML5_only = 0;
var glb_myv_videos_dir;
var glb_disable_ori_change = 0;
var glb_downloading = 0;
var glb_dl_title;
var glb_dataDir;
var glb_is_acquired = 0;
var glb_ad_counter = 0;
var glb_fileTransfer;
var glb_master_id;

var glb_myPlayer;
var glb_scope = 'myVidster';
var glb_search_sort = 'relevant';
var glb_scope_id = 0;
var glb_filter_by = "all";
var images = [];
var glb_ver = 3;
var glb_source_dl;
var is_yt_playing = false;
var glb_back_button = true;
var glb_lock_screen = 0;
var glb_lock_screen_override = false;
var glb_list_name;
var glb_cur_last_id;



var glb_alert = 1;
var glb_push = 1;

var glb_scroll_home = true;
var glb_scroll_video = true;
var glb_scroll_search = true;

// for mobile messaging
//var pusher = new Pusher('99d751f02388a7370cc3');

Ext.Ajax.timeout = 15000;

//chromecast
var glb_session;
var glb_currentMedia;
var glb_currentVolume = 1;
var glb_chromecast_listeners = true;
var glb_chromecast_update = true;
var glb_chromecast_init_check = false;

var glb_chromecast_status;
var chrome_actionSheet = new Ext.ActionSheet();
var glb_ios_glb_session = false;
var glb_ios_currentMedia = false;
var glb_ios_playerState;
var glb_chromecast_receiver;
//chromecast

var glb_bottom_panel = "Home";
var sub_carousel_fix = 0;

var glb_alert_active = 0;

var glb_current_messaging;
var glb_last_chat_id;
var glb_first_chat_id = '';
var pictureSource; // picture source
var destinationType; // sets the format of returned value

//var glb_session_start_time = new Date();
var glb_session_start_time; //not setting the time will show an add on app startup :)
var glb_ad_fix = Math.floor(Math.random() * 2) + 0;
var savedAdObject = null;
var msg_list_fix = 0;
var glb_push_coldstart = 1;
var glb_chat_send_btn = 0;
var chat_scroll_check = 0;

var push = '';
var glb_comment_hidden = 0;
var glb_videoCall = 0;

var slider_pressed = '1';
var glb_back_data_channel_bug = '';
var glb_back_data_channel_bug_2 = '';
var glb_darkTheme = 0;
var glb_defaultAccess = 0;
var glb_chat_block = '';

// For redirect after phone number verification - to tell if it's an existing user attempting to access contacts or new user
var glb_existing_user_accessing_contacts = false;

// Discover
var glb_discover_tab = '';

var devd2 = {
    xtype: 'panel',
    id: 'devd_c2',
    items: [{
        html: '<br/><div align="center" onclick="window.open(\'https://itunes.apple.com/us/app/myvidster/id611470289?mt=8\');" style="margin-top:20px;font-weight:bold;">Rate us on the App Store!</div>'
    }]
};


var settings_sz = '';
var settings_sz2 = 'padding-top:18px;';
var user_profile_settings = '';
var settings_sz3 = 'font-size: .75em;';
var settings_sz4 = '25px';
var toolbar_sz = '';
var tabbar_sz = '';
var dark_tabbar_sz = 'background-color: #1a1a1a; border-color: black;';
var search_ht = '50px';
var search_ht2 = '50px';
var chat_bbl = '60px';
var chat_bbl_text = '.75em';
var chat_bbl_time = '.53em';
var chat_send_btn = '1em';
var chat_pic1 = '45px';
var chat_pic2 = '35px';
var settings_sz9 = '5px';

if (isIphoneX) {
  tabbar_sz = 'min-height: 3.9em;padding-bottom:12px;';
  settings_sz2 =  'min-height:4.4em;';
  user_profile_settings = "min-height:4.4em; padding-top:35px";
}

var chat_font_sz = '.99em';
var ipad_search = true;
var ifix_search_bar = 1;

if (glb_device_type == 'tablet') {
    search_ht = '60px';
    search_ht2 = '52px';
    settings_sz = 'font-size: 1.28em;';
    settings_sz2 = 'font-size: 1.2em;min-height:3.2em;';
    user_profile_settings = 'font-size: 1.2em;min-height:3.2em;';
    settings_sz3 = 'font-size:.99em;';
    settings_sz4 = '32px';
    toolbar_sz = 'min-height:3.2em;';
    tabbar_sz = 'height:3.8em;';
    dark_tabbar_sz = 'background-color: #1a1a1a; border-color: black; height: 3.8em;';
    chat_bbl = '75px';
    chat_bbl_text = '.79em';
    chat_bbl_time = '.58em';
    chat_send_btn = '1.6em';
    settings_sz9 = '3px';
    chat_pic1 = '55px';
    chat_pic2 = '35px';
    chat_font_sz = '1.5em';
    ipad_search = false;
}

//start fieldsearch // searchfield
var search_bar = {
    xtype: 'toolbar',
    style: toolbar_sz,
    items: [{
        xtype: 'searchfield',
        id: 'search_bar',
        flex: ifix_search_bar,
        height: search_ht2,
        listeners: {
            action: function() {
                'use strict';
                var q = Ext.getCmp('search_bar').getValue();
                video_search(q, 1, glb_scope, glb_scope_id, glb_list_name);
            }
        }
    },
    {
        xtype: 'button',
        text: 'Search',
        hidden: ipad_search,
        pressedCls: '',
        handler: function() {
            'use strict';
            var q = Ext.getCmp('search_bar').getValue();
            video_search(q, 1, glb_scope, glb_scope_id, glb_list_name);
        }

    }]
};

var search_bar_contact = {
    xtype: 'toolbar',
    style: toolbar_sz,
    items: [{
        xtype: 'searchfield',
        id: 'search_bar_contact',
        width: '100%',
        height: search_ht2,
        listeners: {
            clearicontap: function() {
                'use strict';
                list_contacts_obj.clearFilter();
            },
            action: function() {
                'use strict';
                var q = Ext.getCmp('search_bar_contact').getValue();
                q = q.toLowerCase(q);

                list_contacts_obj.clearFilter();
                list_contacts_obj.filter('lowerc', q);

            }
        }
    }]
};


function checkFingerprintSupport() {
  if (typeof window.touchid != 'undefined') {
        touchid.checkSupport(function() {
        setTimeout(function() {
          Ext.getCmp('enable_fingerprint').show();
        },1000);
        }, function() {
    
        });         
  }

  if (Ext.os.is('Android') && !isPixelPhone) {
    if (typeof window.FingerprintAuth != 'undefined') {
        FingerprintAuth.isAvailable(function(_result) {
          setTimeout(function() {
            Ext.getCmp('enable_fingerprint').show();
          },1000);
        }, function() {

        });          
    }
  }
}




var search_form = {
    id: 'search_form',
    scrollable: null,
    xtype: 'formpanel',
    width: '100%',
    height: search_ht,
    items: search_bar
};

//start fieldset
var settingsForm = {
    id: 'settingsForm',
    scrollable: null,
    xtype: 'formpanel',
    items: [
        
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'App Theme',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'darkTheme',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'darkTheme',
                label: 'Normal',
                value: 0,
                listeners: {
                    check: {
                        fn: function() {
                            glb_darkTheme = 0;
                            application.set("darkTheme", glb_darkTheme);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();

                            try{ // try/catch needed when link isn't present 
                                document.querySelector("link[id='dark_theme']").href = "";
                            }catch(err){}
                        }
                    }
                }
            }, {
                name: 'darkTheme',
                label: 'Dark Mode',
                value: 1,
                listeners: {
                    check: {
                        fn: function() {
                            glb_darkTheme = 1;
                            application.set("darkTheme", glb_darkTheme);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();
                            var cssId = 'dark_theme'; 
                            var cachebuster2 = Math.round(new Date().getTime() / 1000); 
                            //var theme_url = 'css/darktheme.css?cb='+cachebuster2;
                            var theme_url = 'https://m.myvidster.com/css/darktheme.css?cb='+cachebuster2;
                            if (!document.getElementById(cssId))
                            {
                                load_remote_css(cssId,theme_url);
                            }else{
                                document.querySelector("link[id='dark_theme']").href = theme_url;
                            }
                            

                        }
                    }
                }
            }]
        },



        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Family Filter',
            style: settings_sz,
            margin: '0',
            cls: 'myv_form',
            hidden: false,
            id: 'family_filter',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'family_filter1',
                id: 'family_filter1',
                label: 'on',
                value: '1',
                checked: true,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            family_filter = 1;
                            account.set("family_filter", family_filter);
                            localStorage.setItem('family_filter', family_filter);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().family_filter = family_filter;
                            list_videos_obj.getProxy().getExtraParams().family_filter = family_filter;
                            search_obj.getProxy().getExtraParams().family_filter = family_filter;
                            if(glb_set_family_filter == true){
                                home_obj.load();
                            }
                            glb_set_family_filter = true;
                            Ext.getCmp('family_filter2').setChecked(false); 
                            
                        }
                    }
                }
            }, {
                name: 'family_filter2',
                id: 'family_filter2',
                label: 'off',
                value: '0',
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            family_filter = 0;
                            account.set("family_filter", family_filter);
                            localStorage.setItem('family_filter', family_filter);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().family_filter = family_filter;
                            list_videos_obj.getProxy().getExtraParams().family_filter = family_filter;
                            search_obj.getProxy().getExtraParams().family_filter = family_filter;
                            if(glb_set_family_filter == true){
                                home_obj.load();
                            }
                            glb_set_family_filter = true;
                            Ext.getCmp('family_filter1').setChecked(false); 
                        }
                    }
                }
            }]
        },
        //////
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Video Playback Options',
            style: settings_sz,
            margin: '0',
            cls: 'myv_form',
            hidden: false,
            id: 'default_html5_playback',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'default_html5_playback',
                label: 'HTML5',
                value: '1',
                checked: true,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict'; 
                            default_html5_playback = 1;
                            //account.set("default_html5_playback", default_html5_playback);
                            localStorage.setItem('default_html5_playback', default_html5_playback);
                            application.set("default_html5_playback", 1);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();

                        }
                    }
                }
            }, {
                name: 'default_html5_playback',
                label: 'Legacy Player',
                value: '0',
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            default_html5_playback = 0;
                            //account.set("default_html5_playback", default_html5_playback);
                            application.set("default_html5_playback", 0);
                            localStorage.setItem('default_html5_playback', default_html5_playback);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Only show mobile friendly videos',
            style: settings_sz,
            margin: '0',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'HTML5_only',
                label: 'enable',
                value: '1',
                checked: true,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_HTML5_only = 1;
                            account.set("HTML5_only", glb_HTML5_only);
                            localStorage.setItem('HTML5_only', glb_HTML5_only);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().html5 = glb_HTML5_only;
                            list_videos_obj.getProxy().getExtraParams().html5 = glb_HTML5_only;
                            search_obj.getProxy().getExtraParams().html5 = glb_HTML5_only;
                        }
                    }
                }
            }, {
                name: 'HTML5_only',
                label: 'disable',
                value: '0',
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_HTML5_only = 0;
                            account.set("HTML5_only", glb_HTML5_only);
                            localStorage.setItem('HTML5_only', glb_HTML5_only);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().html5 = glb_HTML5_only;
                            list_videos_obj.getProxy().getExtraParams().html5 = glb_HTML5_only;
                            search_obj.getProxy().getExtraParams().html5 = glb_HTML5_only;
                        }
                    }
                }
            }]
        }, {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Lock App',
            id: 'lock_app',
            style: settings_sz,
            hidden: false,
            margin: '0',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'lock_screen',
                label: 'enable',
                value: 1,
                listeners: {
                    check: {
                        fn: function() {
                          'use strict';
                          if (!glb_lock_screen) {show_lockscreen();}
                        }
                    }
                }
            }, {
                name: 'lock_screen',
                label: 'disable',
                value: 0,
                listeners: {
                    check: {
                        fn: function() {
                            var authType = isIphoneX? 'FaceID' : 'TouchID';
                            'use strict';
                            if (enabledFingerprintAuth) {
                              enabledFingerprintAuth = false;
                              navigator.notification.confirm('Are you sure you want to '+
                                'disable '+authType+'? By disabling the lock screen you will'+
                                ' no longer be able to use ' + authType,
                                function(buttonIndex) {
                                    if (buttonIndex == 1) { //yes to disable
                                        glb_lock_screen = 0;
                                        application.set("lock_screen", glb_lock_screen);
                                        application.set("pin_code1", "");
                                        application.set("pin_code2", "");
                                        application.setDirty();
                                        app_store.sync();
                                        application = app_store.first();
                                        localStorage.setItem('fingerprintAuth', false); 
                                        Ext.getCmp('enable_fingerprint').getItems().items[1].check();
                                        enabledFingerprintAuth = false;

                                    } else if (buttonIndex == 2) { //no
                                        Ext.getCmp('lock_app').getItems().items[0].check();
                                        enabledFingerprintAuth = true;
                                    }
                                }, 'Disable Lock Screen And ' + authType +'?', 'yes,no');                              
                            } else {
                              glb_lock_screen = 0;
                              application.set("lock_screen", glb_lock_screen);
                              //localStorage.setItem('lock_screen', glb_lock_screen);
                              application.set("pin_code1", "");
                              application.set("pin_code2", "");
                              application.setDirty();
                              app_store.sync();
                              application = app_store.first();
                            }
                        }
                    }
                }
            }]
        }, {
            xtype: 'fieldset',
            pack: 'center',
            title: isIphoneX? 'FaceId Authentication' : 'Fingerprint Authentication',
            id: 'enable_fingerprint',
            style: settings_sz,
            hidden: true,
            margin: '0',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'enable_fingerprint',
                label: 'enable',
                id: 'enable_fingerprint_on',
                value: 1,
                listeners: {
                    check: {
                        fn: function() {
                          'use strict';
                          localStorage.setItem('fingerprintAuth', true); 
                          enabledFingerprintAuth = true;

                          //enable lockscreen
                          if (!glb_lock_screen) {show_lockscreen();}
                        }
                    }
                }
            }, {
                name: 'enable_fingerprint',
                label: 'disable',
                value: 0,
                id: 'enable_fingerprint_off',
                checked: true,
                listeners: {
                    check: {
                        fn: function() {
                          'use strict';
                          var authType = isIphoneX? 'FaceID' : 'TouchID';
                          if (enabledFingerprintAuth) {
                            navigator.notification.confirm('Are you sure you want to disable '+authType+'?',
                              function(buttonIndex) {
                                  if (buttonIndex == 1) { //yes to disable
                                    localStorage.setItem('fingerprintAuth', false); 
                                    enabledFingerprintAuth = false;
                                  }
                                  if (buttonIndex == 2) {//no to disable
                                    Ext.getCmp('enable_fingerprint').getItems().items[0].check();
                                  }
                              }, 'Disable ' + authType +'?', 'yes,no');                             
                          }
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Orientation lock',
            style: settings_sz,
            id: 'v_orientation_chk',
            hidden: false,
            margin: '0',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'v_orientation_chk',
                label: 'enable',
                value: '1',
                checked: true,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            v_orientation_chk = 1;
                            if (typeof device != 'undefined') {
                              screen.lockOrientation('portrait');
                            }
                            account.set("v_orientation_chk", v_orientation_chk);
                            localStorage.setItem('v_orientation_chk', v_orientation_chk);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                        }
                    }
                }
            }, {
                name: 'v_orientation_chk',
                label: 'disable',
                value: '0',
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            v_orientation_chk = 0;
                            if (typeof device != 'undefined'){
                             screen.unlockOrientation();
                            }
                            account.set("v_orientation_chk", v_orientation_chk);
                            localStorage.setItem('v_orientation_chk', v_orientation_chk);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                        }
                    }
                }
            }]
        }, {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Video History',
            style: settings_sz,
            margin: '0',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'video_history_chk',
                label: 'enable',
                value: '1',
                checked: true,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_video_history_chk = 1;
                            account.set("video_history_chk", glb_video_history_chk);
                            account.setDirty();
                            store.sync();
                            account = store.first();

                        }
                    }
                }
            }, {
                name: 'video_history_chk',
                label: 'disable',
                value: '0',
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_video_history_chk = 0;
                            account.set("video_history_chk", glb_video_history_chk);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Push Notification',
            style: settings_sz,
            hidden: false,
            margin: '0',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'push_set',
                label: 'enable',
                value: 1,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_push = 1;
                            application.set("push_set", glb_push);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();


                            ////////////////
                            push_func_start();
                            //////////////
                        }
                    }
                }
            }, {
                name: 'push_set',
                label: 'disable',
                value: 0,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_push = 0;
                            application.set("push_set", glb_push);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();

                            push.unregister(function() {
                             
                                //
                            }, function() {
                             
                                //
                            });
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Display Video Comments',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'show_hide_comments',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'show_hide_comments',
                label: 'show',
                value: 0,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_comment_hidden = 0;
                            application.set("show_hide_comments", glb_comment_hidden);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();
                        }
                    }
                }
            }, {
                name: 'show_hide_comments',
                label: 'hide',
                value: 1,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_comment_hidden = 1;
                            application.set("show_hide_comments", glb_comment_hidden);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();
                        }
                    }
                }
            }]
        },

        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Allow Video Calling',
            style: settings_sz,
            hidden: glb_video_call_display,
            margin: '0',
            id: 'videoCall',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'videoCall',
                label: 'enable',
                value: 0,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_videoCall = 0;
                            application.set("videoCall", glb_videoCall);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();
                        }
                    }
                }
            }, {
                name: 'videoCall',
                label: 'disable',
                value: 1,
                listeners: {
                    check: {
                        fn: function() {
                            'use strict';
                            glb_videoCall = 1;
                            application.set("videoCall", glb_videoCall);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();
                        }
                    }
                }
            }]
        },























        
        {
            layout: 'hbox',
            items: {
                xtype: 'spacer'
            }
        }
    ]
};

var login_sz = '';
var signup_sz = '';
var button_sp_sz = '15px';
var reset_sp_sz = '20px';
var register_btn_sz = '100px';
var btn_margin = '';
var reg_label_width = '130px';
if (glb_device_type == 'tablet') {
    login_sz = 'font-size: 1.34em;';
    signup_sz = 'font-size: 1.21em;';
    button_sp_sz = '60px';
    reset_sp_sz = '80px';
    register_btn_sz = '120px';
    btn_margin = 'margin-top:40px';
    reg_label_width = '160px';
}

var userloginForm = {
    xtype: 'formpanel',
    id: 'userloginForm',
    scrollable: null,
    items: [{
        pack: 'center',
        margin: '0',
        xtype: 'fieldset',
        style: login_sz,
        items: [{
            xtype: 'emailfield',
            name: 'email',
            id: 'email',
            label: 'Email'
        }, {
            xtype: 'passwordfield',
            name: 'password',
            id: 'pw',
            label: 'Password'
        }]
    }, {
        layout: {
            type: 'hbox',
            pack: 'center'
        },
        items: [{
            xtype: 'button',
            width: register_btn_sz,
            margin: '0',
            scope: this,
            text: 'Login',
            handler: function() {
                'use strict';
                var email = Ext.getCmp('email').getValue();
                var pw = Ext.getCmp('pw').getValue();
                loginUser(email, pw, 'UserMenu');
            }
        }, {
            xtype: 'spacer',
            width: button_sp_sz,
        }, {
            xtype: 'button',
            scope: this,
            margin: '0',
            width: register_btn_sz,
            text: 'Register',
            handler: function() {
                Ext.Viewport.setActiveItem("UserRegistration");
                searchviewbackhack();
                custom_views_arr.push("UserRegistration");
                var array_data = [0];
                glb_custom_views_arr_data.push(array_data);
            }
        }]

    }, {
        html: '<div align="center" onclick="reset_pw();"' + reset_sp_sz + ';">Reset Password</div>'
    }]
};

var user_regisiterForm = {
    xtype: 'formpanel',
    id: 'user_regisiterForm',
    scrollable: null,
    items: [{
        xtype: 'fieldset',
        style: signup_sz,
        pack: 'center',
        margin: '10',
        defaults: {
            labelWidth: reg_label_width,
        },
        items: [{
                xtype: 'textfield',
                name: 'dis_name',
                id: 'dis_name',
                label: 'Display Name'
            }, {
                xtype: 'emailfield',
                name: 'reg_email',
                id: 'reg_email',
                label: 'Email'
            }, {
                xtype: 'emailfield',
                name: 'reg_email2',
                id: 'reg_email2',
                label: 'Retype Email'
            },{
                xtype: 'textfield',
                name: 'phone_number',
                id: 'phone_number',
                label: 'Phone Number'
            }, {
                xtype: 'passwordfield',
                name: 'reg_password',
                id: 'reg_password',
                label: 'Password'
            }, {
                xtype: 'passwordfield',
                name: 'check_pass',
                id: 'check_pass',
                label: 'Retype<br>Password'
            }, {
                xtype: 'textfield',
                name: 'coll_name',
                id: 'coll_name',
                label: 'Collection<br>Name'
            }, {
                xtype: 'textfield',
                name: 'coll_url',
                id: 'coll_url',
                label: 'Collection URL'
            }, {
                xtype: 'panel',
                name: 'ueal',
                id: 'ueal',
                items: [{
                    html: '<div style="padding:10px 2px;text-align:center;"> By using MyVidster you agree to our <a onclick="load_webpage(\'' + 'https://www.myvidster.com/docs/tos' + '\', false);" style="color:blue;">terms</a> &nbsp; <input id="user_agree" type=checkbox> </div>'
                }]
            }
        ]
    }, {
        layout: 'hbox',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            flex: 0,
            scope: this,
            style: btn_margin,
            text: 'Sign Up',
            handler: function() {
                var user_agree = document.getElementById("user_agree");

                if (user_agree.checked) {
                    var dis_name = Ext.getCmp('dis_name').getValue();
                    var email = Ext.getCmp('reg_email').getValue();
                    var email2 = Ext.getCmp('reg_email2').getValue();
                    var phone_number = Ext.getCmp('phone_number').getValue();
                    var reg_pass = Ext.getCmp('reg_password').getValue();
                    var check_pass = Ext.getCmp('check_pass').getValue();
                    var coll_name = Ext.getCmp('coll_name').getValue();
                    var coll_url = Ext.getCmp('coll_url').getValue();

                    if (email && reg_pass && dis_name) {
                      if (reg_pass === check_pass) { 
                        if (email == email2) {
                          if (phone_number) {
                            validatePhoneNumber(dis_name, email, phone_number, reg_pass, check_pass, coll_name, coll_url);
                          } else {
                            RegisterUser(dis_name, email, phone_number, reg_pass, check_pass, coll_name, coll_url);               
                          }                        
                        } else {
                          myvAlert("Emails must match.");
                        }

                      } else {
                        myvAlert("Passwords must match.");
                      }
                    } else {
                      myvAlert("One or more fields are blank.");
                    }

                } else {
                    myvAlert("Please agree to our user agreement.");
                }
            }
        }, {
            xtype: 'spacer'
        }]
    }]
};

var user_inputPhoneForm = {
    xtype: 'formpanel',
    id: 'user_inputPhoneForm',
    scrollable: null,
    items: [{
        xtype: 'panel',
        margin: 10,
        html: 'Please enter and verify your phone number in order to see your contacts.'
    }, {
        xtype: 'fieldset',
        style: signup_sz,
        pack: 'center',
        margin: 10,
        defaults: {
            labelWidth: 160,
        },
        items: [{
            xtype: 'numberfield',
            name: 'phone_number',
            id: 'phone_inputForm',
            label: 'Phone Number'
        }]
    }, {
        layout: 'hbox',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            flex: 0,
            scope: this,
            style: btn_margin,
            text: 'Continue',
            handler: function() {
                var phone_number = Ext.getCmp('phone_inputForm').getValue();
                getVerificationCode(account.get('user_id'), phone_number);
            }
        }, {
            xtype: 'spacer'
        }],
    },]
};

var user_verifyPhoneForm = {
    xtype: 'formpanel',
    id: 'user_verifyPhoneForm',
    scrollable: null,
    items: [{
        xtype: 'fieldset',
        style: signup_sz,
        pack: 'center',
        margin: '10',
        defaults: {
            labelWidth: 160,
        },
        items: [{
            xtype: 'numberfield',
            name: 'verification_code',
            id: 'verification_code',
            label: 'Verification Code'
        }]
    }, {
        layout: 'hbox',
		padding: '10',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            flex: 0,
            scope: this,
            style: btn_margin,
            text: 'Confirm',
            handler: function() {
                var verification_code = Ext.getCmp('verification_code').getValue();
				var phone_number = Ext.getCmp('phone_inputForm').getValue();
                verifyPhoneNumber(account.get('user_id'), verification_code, phone_number);
            }
        }, {
            xtype: 'spacer'
        }],
    }, {
        layout: 'hbox',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            flex: 0,
            scope: this,
            style: btn_margin,
            text: 'Continue Without Saving Phone Number',
            handler: function() {
                var verification_code = Ext.getCmp('verification_code').getValue();
                var phone_number = Ext.getCmp('phone_number').getValue();
                verifyPhoneNumber();
            }
        }, {
            xtype: 'spacer'
        }]
    }]
};

var videoCollectForm = {
    xtype: 'formpanel',
    id: 'videoCollectForm',
    scrollable: null,
    items: [{
        xtype: 'fieldset',
        pack: 'center',
        title: 'Video Details',
        style: settings_sz,
        margin: '10',
        defaults: {
            labelWidth: '35%'
        },
        items: [{
            xtype: 'textfield',
            name: 'title',
            id: 'collect_video_title',
            label: 'Title'
        }, {
            xtype: 'textfield',
            name: 'video_tags',
            id: 'collect_video_tags',
            label: 'Tags'
        }, {
            xtype: 'hiddenfield',
            name: 'video_id',
            id: 'collect_video_id'
        }, {
            xtype: 'selectfield',
            name: 'channel',
            id: 'collect_channel',
            label: 'Channel'
        }]
    }, {
        xtype: 'fieldset',
        pack: 'center',
        title: 'Video Access',
        style: settings_sz,
        margin: '10',
        defaults: {
            labelWidth: '35%'
        },
        items: [{
            xtype: 'radiofield',
            name: 'access',
            label: 'Public',
            value: 0,
            hidden: false,
            id: 'private_fix'
        }, {
            xtype: 'radiofield',
            name: 'access',
            label: 'Adult',
            value: 2
        }, {
            xtype: 'radiofield',
            name: 'access',
            label: 'Private',
            value: 1
        }]
    }, {
        layout: 'hbox',
        items: [{
            xtype: 'spacer'
        }, collect_btn, {
            xtype: 'spacer'
        }]
    }]
};

var videoCommentForm = {
    xtype: 'formpanel',
    id: 'videoCommentForm',
    scrollable: null,
    items: [{
        xtype: 'fieldset',
        pack: 'center',
        title: 'Post a comment',
        margin: '10',
        defaults: {
            labelWidth: '30%'
        },
        items: [{
            xtype: 'textfield',
            name: 'comment_name',
            label: 'Name',
            hidden: true
        }, {
            xtype: 'textfield',
            name: 'comment_email',
            label: 'Email',
            hidden: true
        }, {
            xtype: 'textareafield',
            id: 'comment_post_id',
            name: 'comment_post',
            label: 'Comment',
            height: '120px'
        }, {
            xtype: 'hiddenfield',
            name: 'comment_master_id'
        }, {
            xtype: 'hiddenfield',
            name: 'comment_ip'
        }, {
            xtype: 'hiddenfield',
            name: 'comment_title'
        }]
    }, {
        layout: 'hbox',
        items: [{
            xtype: 'spacer'
        }, comment_btn, {
            xtype: 'spacer'
        }]
    }]
};

var videoEditForm = {
    xtype: 'formpanel',
    id: 'videoEditForm',
    scrollable: null,
    items: [{
        xtype: 'fieldset',
        pack: 'center',
        // title: 'Edit Video',
        margin: '10',
        defaults: {
            labelWidth: '25%'
        },
        items: [{
                xtype: 'textfield',
                id: 'edit_video_title',
                name: 'video_title',
                clearIcon: false,
                label: 'Title'
            }, {
                xtype: 'textfield',
                id: 'edit_video_tags',
                name: 'video_tags',
                clearIcon: false,
                label: 'Tags'
            }, {
                xtype: 'textareafield',
                id: 'edit_desc_text',
                name: 'desc_text',
                label: 'Desc.',
                clearIcon: false,
                //height: '100px'
            }, {
                xtype: 'textfield',
                id: 'edit_video_id',
                hidden: true
            },
            {
                xtype: 'fieldset',
                title: 'Access',
                id: 'access_check',
                margin: '10',
                defaults: {
                    labelWidth: '25%'
                },
                items: [
                    {
                        xtype: 'radiofield',
                        name: 'access_opt',
                        id: 'access_opt1',
                        label: 'Public',
                        value: 0,
                        checked: true,
                        listeners: {
                            check: {
                                fn: function() {
                                    //cycle_text2(0);
                                }
                            }
                        }
                    },
                    {
                        xtype: 'radiofield',
                        name: 'access_opt',
                        id: 'access_opt2',
                        label: 'Private',
                        value: 1,
                        listeners: {
                            check: {
                                fn: function() {
                                    //cycle_text2(1);
                                }
                            }
                        }
                    },
                    {
                        xtype: 'radiofield',
                        name: 'access_opt',
                        id: 'access_opt3',
                        label: 'Adult',
                        value: 2,
                        listeners: {
                            check: {
                                fn: function() {
                                    //cycle_text2(2);
                                }
                            }
                        }
                    }
                ]
            },
            {
                layout: 'vbox',
                margin: "10",
                defaults: {
                    labelWidth: '25%'
                },
                items: [{
                    xtype: 'selectfield',
                    name: 'myv_item_editv',
                    id: 'myv_item_editv',
                    label: 'Channel'
                }]
            }
        ]
    }, {
        layout: 'hbox',
        items: [{
                xtype: 'spacer'
            },
            video_edit_btn, {
                xtype: 'spacer'
            },
            video_delete_edit_btn, {
                xtype: 'spacer'
            }
        ]
    }]
};

var EditCollectionForm = {
    xtype: 'formpanel',
    id: 'EditCollectionForm',
    scrollable: null,
    items: [{
        xtype: 'fieldset',
        pack: 'center',
        // title: 'Edit Video',
        margin: '10',
        defaults: {
            labelWidth: '25%'
        },
        items: [{
                xtype: 'textfield',
                id: 'edit_col_title',
                name: 'video_title',
                clearIcon: false,
                label: 'Title'
            }, {
                xtype: 'textareafield',
                id: 'edit_desc_col_text',
                name: 'desc_text',
                label: 'Desc.',
                clearIcon: false,
                //height: '100px'
            }, {
                xtype: 'textfield',
                id: 'col_id',
                name: 'col_id',
                hidden: true
            }, {
                xtype: 'textfield',
                id: 'col_type_id',
                name: 'col_type_id',
                hidden: true
            }
        ]
    }, {
        layout: 'hbox',
        items: [{
                xtype: 'spacer'
            },
            col_btn, {
                xtype: 'spacer'
            }
        ]
    }]
};

var msg_crypto = new Ext.MessageBox({
    width: 320,
    floating: 1,
    style: 'background-color:black;',
    margin: '80 0 0 0'
});



function execCopy() {
	console.log('hello');
}

function copyToClipboard() {
    var textArea;
    var textToCopy = glb_btc_address;
    function isOS() {
      return navigator.userAgent.match(/ipad|iphone/i);
    }
    function createTextArea(text) {
      textArea = document.createElement('textArea');
      textArea.readOnly = true;
      textArea.contentEditable = true;
      textArea.value = text;
      document.body.appendChild(textArea);
    }
    function selectText() {
      var range, selection;
      if (isOS()) {
        range = document.createRange();
        range.selectNodeContents(textArea);
        selection = window.getSelection();
        selection.removeAllRanges();
        selection.addRange(range);
        textArea.setSelectionRange(0, 999999);
      } else {
        textArea.select();
      }
    }
    function copyTo() {
      document.execCommand('copy');
      document.body.removeChild(textArea);
    }
    createTextArea(textToCopy);
    selectText();
    copyTo();
  }


var PaymentForm = {
    xtype: 'formpanel',
    id: 'PaymentForm',
    scrollable: null,
    items: [{
        xtype: 'fieldset',
        style: settings_sz,
        pack: 'center',
        title: 'Payment Information',
        margin: "10",
        defaults: {
            labelWidth: '100px'
        },
        items: [{
                xtype: 'hiddenfield',
                name: 'tiers',
                id: 'tiers'
            }, {
                name: 'card_num',
                label: 'Card #',
                xtype: 'textfield',
                component: {
                    xtype: 'input',
                    type: 'tel'
                }
            }, {
                name: 'exp_date',
                label: 'Exp Date',
                placeHolder: '(mmyy)',
                xtype: 'textfield',
                component: {
                    xtype: 'input',
                    type: 'tel'
                }
            },
            {
                name: 'card_code',
                label: 'Card Code',
                id: 'card_code_alert',
                xtype: 'textfield',
                component: {
                    xtype: 'input',
                    type: 'tel'
                }
            },
            card_scan_btn, {
                xtype: 'hiddenfield',
                name: 'cust_id',
                id: 'cust_id'
            },
            { 
                layout: {
                    type: 'hbox',
                    pack: 'center'
                },
                items: [{
                    xtype: 'button',
                    text: 'Pay With Crypto!',
                    style: pro_btn_font,
                    width: 'pro_btn_sz_w1', /* 98% */
                    height: pro_btn_sz_h1,
                    margin: pro_btn_m1,
                    hidden: true,
                    handler: function() {

                        setMask('Loading...');
                        var plan = '';
                        if(glb_crypto_plan == 'silver'){
                            plan = 'myv_pro_silver2';
                        }else if(glb_crypto_plan == 'gold'){
                            plan = 'myv_pro_gold2';
                        }else{
                            plan = 'myv_pro_plat2';
                        }
                        Ext.util.JSONP.request({
                            url: 'https://api.myvidster.com/mobile_json2.php',
                            callbackKey: 'callback',
                            params: {
                                type: 'btc_pay',
                                user_id: account.get('user_id'),
                                pkey: account.get('pw'),
                                token: glb_msg_token,
                                myv_item: plan
                            },
                            callback: function(success, result) {
                                if (result) {
                                    var amount = result.items[0]['amount'];
                                    var address = result.items[0]['address'];
                                    var qr = result.items[0]['qr'];
                                    glb_btc_address = address;

                                    var response_code = "<div style='text-align:center;color:white;'> <img src='"+qr+"' style='width:40%;padding-top:5px;' onclick='execCopy();' /> <br/> <span style='font-size:0.8em;'> Send "+amount+" Bitcoin ($BTC) to: "+address+" <br/> Please allow up to an hour for the payment to be confirmed and your membership to be activated! </span> <textarea style='-webkit-appearance:none;border:0px;resize:none;font-size:0.2em;background-color:black;color:black;' id='btc_copy'>"+address+"</textarea>  </div>";       

                    
            msg_crypto.show({
                title: 'Pay with crypto',
                style: 'font-size:1.1em;',
                items: [            
                    {
                       html: response_code              
                     }
                   ],
                buttons: [
                {
                    html: '<a style="color:white !important;"> Done </a> ',
                    itemId: 'done'
                },
                {
                    html:   '<a style="color:orange !important;" onclick="copyToClipboard();execCopy();"> Copy </a> ', 
                    itemId: 'copy'
                }],
                fn: function(response) {
                    if(response == 'copy'){
                        myvAlert('Address Copied!');
                    }
                }
            });

            clearMask();

      
                                }else{
                                    clearMask();
                                    myvAlert('Error, please try again.');
                                }
                            }
                        });
                        
                        
                    }
                }]
            }
        ]
    }, {
        xtype: 'fieldset',
        style: settings_sz,
        pack: 'center',
        title: 'Billing Information',
        margin: "10",
        defaults: {
            labelWidth: '100px'
        },
        items: [{
            xtype: 'textfield',
            name: 'first_name',
            label: 'First Name'
        }, {
            xtype: 'textfield',
            name: 'last_name',
            label: 'Last Name'
        }]
    }, {
        layout: 'vbox',
        margin: "10",
        defaults: {
            labelWidth: '100px'
        },
        items: [{
                xtype: 'selectfield',
                style: settings_sz,
                name: 'myv_item',
                id: 'myv_item',
                width: '100%',
                label: 'Options',
                disabled: true
            }, {
                xtype: 'fieldset',
                //pack: 'center',
                style: settings_sz,
                title: 'Auto Bill',
                id: 'auto_bill_label',
                margin: '10',
                defaults: {
                    labelWidth: '75%'
                },
                items: [{
                        xtype: 'radiofield',
                        name: 'auto_bill',
                        id: 'auto_bill_1',
                        label: 'Charge me one time',
                        value: 0,
                        checked: true,
                        listeners: {
                            check: {
                                fn: function() {
                                    cycle_text(0);
                                }
                            }
                        }
                    },
                    {
                        xtype: 'radiofield',
                        name: 'auto_bill',
                        id: 'auto_bill_2',
                        label: 'Charge me every month',
                        hidden: false,
                        value: 1,
                        listeners: {
                            check: {
                                fn: function() {
                                    cycle_text(1);
                                }
                            }
                        }
                    }
                ]
            }, {
                html: '<div align="center" style="line-height: 1em;"><span style="font-size:9pt;"><span id="auto_bill_text" style="display:none;"></span><span id="cycle_text">You will only be charged <b>1 time</b> and will have to manually renew when your membership expires.</span><br><b>SALSAINDY LLC</b> will be in the charge description.</span></div>',
                width: '100%',
                margin: '5 0 0 0',
            }
        ]
    }, {
        layout: 'hbox',
        items: [{
            xtype: 'spacer'
        }, payment_btn, {
            xtype: 'spacer'
        }]
    }]
};

//payment_btn
//start toolbar

var homeToolbar = {
    xtype: 'toolbar',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: home_top_buttons,


    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {
            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("home_refresh_var");
                if (this_list.rendered) {
                    this_list.scrollToRecord(this_list.getStore().getAt(0), true);
                }
            }
        }
    }
};




var collectToolbar = {
    xtype: 'toolbar',
    title: 'Collect',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: collect_top_buttons
};

var commentsToolbar = {
    xtype: 'toolbar',
    title: 'Comment',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: collect_top_buttons
};

var editVideoToolbar = {
    xtype: 'toolbar',
    title: 'Edit Video',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: collect_top_buttons
};

var EditCollectionToolbar = {
    xtype: 'toolbar',
    id: 'EditCollectionToolbar',
    title: 'Edit',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: edit_collect_top_buttons
};

var paymentToolbar = {
    xtype: 'toolbar',
    title: 'Upgrade to PRO',
    id: 'upgradetopro',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: payment_top_buttons
};

//keep breadcrumbs to ext.create until you resolve the display issue
var video_breadcrumb = Ext.create('Ext.Toolbar', {
    xtype: 'toolbar',
    id: 'video_breadcrumb',
    fullscreen: true,
    cls: 'video_breadcrumb',
    docked: 'top',
    width: '100%',
    items: [{
        ui: 'plain',
        disabled: true
    }]
});

var video_breadcrumb2 = Ext.create('Ext.Toolbar', {
    xtype: 'toolbar',
    id: 'video_breadcrumb2',
    fullscreen: true,
    cls: 'video_breadcrumb',
    docked: 'top',
    width: '100%',
    items: [{
        ui: 'plain',
        disabled: true
    }]
});
// RockyBalboa, hey this is where you need to fix the code!!!
//added underscores to function we will see what happens!
var searchToolbar = {
    xtype: 'toolbar',
    id: 'searchToolbar',
    title: 'Search',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: search_top_buttons,
    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {
            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("search_var");
                if (this_list.rendered) {
                    this_list.scrollToRecord(this_list.getStore().getAt(0), true);
                }
            }
        }
    }
};

var userListToolbar = {
    xtype: 'toolbar',
    id: 'userListToolbar',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: userlist_top_buttons,

    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {

            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("list_videos_var");
                if (this_list.rendered) {
                    this_list.scrollToRecord(this_list.getStore().getAt(0), true);
                }
            }

        }
    }

};

var userListToolbar2 = {
    xtype: 'toolbar',
    id: 'userListToolbar2',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: userlist_top_buttons2

};

var userListToolbar3 = {
    xtype: 'toolbar',
    id: 'userListToolbar3',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: userlist_top_buttons3

};

var userListToolbar4 = {
    xtype: 'toolbar',
    id: 'userListToolbar4',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: userlist_top_buttons4

};

// userListToolbar4
var userListToolbarMessage = {
    xtype: 'toolbar',
    id: 'userListToolbarMessage',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: msg_top_buttons,

    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {

            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("msglistscroll");
                if (this_list.rendered) {
                    this_list.scrollToRecord(this_list.getStore().getAt(0), true);
                }
            }
        }
    }
};

// userListToolbar5
var userListToolbarMessages = {
    xtype: 'toolbar',
    id: 'userListToolbarMessages',
    title: 'Chat',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: msgList_top_buttons,

    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {

            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("list_msgs_var");
                if (this_list.rendered) {
                    this_list.scrollToRecord(this_list.getStore().getAt(0), true);
                }
            }
        }
    }
};

var MsgLog = {
    xtype: 'list',
    id: 'MsgLog',
    tite: 'title25',
    //store: home_obj,
    data: [{
        message: 'Heyyyy from Marques!!!',
        user_id: '2',
        user_name: 'Marques',
        profile_photo: 'https://img.crx4chrome.com/fd/04/89/hkpgpmmooejhfhojndincjeonokodggj-icon.png'
    }, {
        message: 'Where are you from?',
        user_id: '1988813',
        user_name: 'johnny',
        profile_photo: 'https://img.crx4chrome.com/fd/04/89/hkpgpmmooejhfhojndincjeonokodggj-icon.png'
    }, ],
    itemTpl: '<div class="text"><img src="{profile_photo}" style="vertical-align:middle;">{message}</div>',
    scrollable: {
        direction: 'vertical',
        directionLock: true,
        scroller: {
            listeners: {
                scroll: function(_scroller, _x, _y, _eOpts) {},
                scrollend: function(_scroller, _x, _y, _eOpts) {}
            }
        }
    },
    plugins: [{
        xclass: 'Ext.plugin.ListPaging',
        id: 'home_p',
        loadMoreText: '',
        autoPaging: true,
        noMoreRecordsText: 'No More Results',
    }, {
        xclass: 'Ext.plugin.PullRefresh',
        pullText: 'Pull to refresh!',
        loadingText: 'Refreshing...',
        autoSnapBack: true,
        scrollerAutoRefresh: true,
    }],
    width: '100%',
    flex: 1,
    listeners: {
        itemtap: {
            fn: function(_view, _index, _target, _record, _event) {
            }
        }
    }
};

var MsgLog2 = {
    xtype: 'panel',
    id: 'MsgLog2',
    items: [{
        scrollable: true,
        html: '<div align="center" style="height:100px;">Testing text!</div>'
    }],
};

var videoToolbar = {
    xtype: 'toolbar',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    handler: function() {
        Ext.Viewport.setActiveItem("Home");
        searchviewbackhack();
        custom_views_arr.push("Home");
        var array_data = [0];
        glb_custom_views_arr_data.push(array_data);
    },
    items: video_top_buttons1,
    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {

            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("video_holder");
                if (this_list.rendered) {
                    var scroller206 = Ext.getCmp('video_holder').getScrollable().getScroller();
                    scroller206.scrollToTop(true);
                }
            }
        }
    }
};

var videoToolbar2 = {
    xtype: 'toolbar',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    handler: function() {
        Ext.Viewport.setActiveItem("Home");
        searchviewbackhack();
        custom_views_arr.push("Home");
        var array_data = [0];
        glb_custom_views_arr_data.push(array_data);
    },
    items: video_top_buttons2,

    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {

            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("video_holder2");
                if (this_list.rendered) {
                    var scroller216 = Ext.getCmp('video_holder2').getScrollable().getScroller();
                    scroller216.scrollToTop(true);
                }
            }

        }



    }
};

// returns copy of bottom tab bar
function createBottomTabBar(id) {
  return {
    xtype: 'tabbar',
    ui: 'light',
    docked: 'bottom',
    id: id,
    style: tabbar_sz,
    items: all_bottom_buttons,
  };
}

var search_search_opt = {
    xtype: 'selectfield',
    style: settings_sz,
    id: 'search_opt',
    width: '200px',
    height: '50px',
    value: 'myVidster',
    listeners: {
        change: function() {
            glb_scope = Ext.getCmp('search_opt').getValue();
            var q = Ext.getCmp('search_bar').getValue();

            if (typeof device != 'undefined' && q.search('following:') < 0 && q.search('followers:') < 0) {
                cordova.plugins.Keyboard.show();
                setTimeout(function() {
                    Ext.getCmp('search_bar').focus();
                }, 500);
            }
        }
    },
    options: [{
        text: 'Users', // temp
        value: 'profiles'
    }, {
        text: 'MyVidster',
        value: 'myVidster'
    }, {
        text: 'Web',
        value: 'web'
    }]
};

var gen_5px_spacer = {
    xtype: 'spacer',
    height: '5px',
    width: '100%'
}

var gen_10px_spacer = {
    xtype: 'spacer',
    height: '10px',
    width: '100%'
}

var line_ht = '';
if (glb_device_type == 'tablet') {
    line_ht = 'line-height:50px;font-size: 1.41em;';
}

var grid ='<table style="width:100%">' +
  '<tr>'+
    '<td>Jill</td>'+
    '<td>Smith</td>'+
  '</tr>'+
'</table>';



var dataStyle = 'float: left; padding: 8px; margin: 8px 8px 4px 8px;';

var userMenuToolbar = {
    xtype: 'toolbar',
    title: 'User Menu',
    style: settings_sz2,
    id: 'userMenuToolbar',
    ui: 'light',
    docked: 'top',
    items: user_menu_top_buttons
};

var user_pic_header = {
    xtype: 'panel',
    id: 'user_pic_header',
    name: 'user_pic_header',
    html: 'User'
};

// Initial user_menu_list icon rendering
if (Ext.os.is.iOS) {
    if (glb_device_type == 'tablet') {
        icon_font_size = window.innerWidth/30;
    } else {
        icon_font_size = window.innerWidth/20;
    }
    icon_size = window.innerWidth/8;
    iconMargin = window.innerWidth/18.75;
} else {
    if (glb_device_type == 'tablet') {
        icon_size = '90';
        icon_font_size = '30';
        iconMargin = '30';
    } else {
        icon_size = '65';
        icon_font_size = '20';
        iconMargin = '25';
    }
}

var userMenuStyle = 
'<div class="iconButton" style="display:flex; align-items:center; flex-direction:column; width: ' + icon_size + 'px; float:left; padding:0px; text-align:center; margin-top:20px; margin-bottom:20px; margin-left:' + iconMargin + 'px; margin-right:' + iconMargin + 'px;">' +
'<div><img src={img} style="width:100%; text-align:center;" class="img_theme"/></div>' +
'<div><strong style="font-size:' + icon_font_size + 'px; text-align:center;">{user_options}</strong></div>' +
'</div>';


// re-rendering view.UserMenu for iOS from updateOrientation();
function reloadUserMenu() {
    if (typeof window.indexedDB != 'undefined' && glb_device_type == 'tablet') {
        Ext.Viewport.setWidth('100%');
        Ext.Viewport.setHeight('100%');
      }

    if (Ext.Viewport.determineOrientation() === 'landscape') {
        icon_size = window.innerWidth/15;
        iconMargin = window.innerWidth/15;
        icon_font_size = window.innerWidth/36;
    } else {
        if (glb_device_type == 'tablet') {
            icon_font_size = window.innerWidth/30;
        } else {
            icon_font_size = window.innerWidth/20;
        }

        icon_size = window.innerWidth/8;
        iconMargin = window.innerWidth/18.75;
    }

    var userMenuStyle = 
    '<div class="iconButton" style="display:flex; align-items:center; flex-direction:column; width: ' + icon_size + 'px; float:left; padding:0px; text-align:center; margin-top:20px; margin-bottom:20px; margin-left:' + iconMargin + 'px; margin-right:' + iconMargin + 'px;">' +
    '<div><img src={img} style="width:100%; text-align:center;" class="img_theme"/></div>' +
    '<div><strong style="font-size:' + icon_font_size + 'px; text-align:center;">{user_options}</strong></div>' +
    '</div>';


      user_options = new Ext.data.Store({
        model: 'User',
        data: [{
                user_options: 'Bookmarks',
                value: 'Profile',
                img: iconImages[0].src + cachebusterParameters,
            }, {
                user_options: 'Collections',
                value: 'User',
                img: iconImages[1].src + cachebusterParameters,
            }, {
                user_options: 'Queue',
                img: iconImages[2].src + cachebusterParameters,
                value: 'Queue',
            }, {
                user_options: 'Subscription',
                value: 'subscriptions',
                img: iconImages[3].src + cachebusterParameters,
            }, {
                user_options: 'Following',
                value: 'subscription_list',
                //img: './resources/images/Icon_Following.png' + cachebusterParameters,
                img: iconImages[10].src + cachebusterParameters,
            }, {
                user_options: 'Discover',
                value: 'Discover',
                img: iconImages[4].src + cachebusterParameters,
            }, {
                user_options: 'Cloud',
                value: 'Downloads',
                img: iconImages[5].src + cachebusterParameters,
            }, {
                user_options: 'History',
                value: 'History_V2',
                img: iconImages[6].src + cachebusterParameters,
            }, {
                user_options: 'PRO',
                value: 'pro_member',
                img: iconImages[7].src + cachebusterParameters,
            }, {
                user_options: 'Tutorial',
                value: 'Tutorial',
                img: iconImages[8].src + cachebusterParameters,
            },
            {
                user_options: 'Options',
                value: 'Options',
                img: iconImages[9].src + cachebusterParameters,
            },
            {
                user_options: '',
                value: 'n/a',
                img: iconImages[11] ? iconImages[11].src : '',
            },
        ]
        });
    
  
    if (Ext.os.is.iOS) {
      Ext.getCmp('user_menu_list').setStore(user_options);
      Ext.getCmp('user_menu_list').setItemTpl(userMenuStyle);
      Ext.getCmp('user_menu_list').refresh();
    } else {
      user_menu_list.store = user_options;
      user_menu_list.itemTpl = userMenuStyle;
    }
  }

  


//start list
var user_menu_list = {
    id: 'user_menu_list',
    xtype: 'dataview',
    store: user_options,
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    itemTpl: userMenuStyle,
    listeners: {
        refresh: function() {
        },
        initialize: function(_view) {
          Ext.Viewport.on('orientationchange', function() {
            
            function updateOrientation (getAccountName) {
              
               stored_user_pic_header_html = Ext.getCmp('user_pic_header').getHtml();
               Ext.getCmp('user_pic_header').setHtml(stored_user_pic_header_html);

               setTimeout(function() {
                reloadUserMenu();
              }, 100);

              Ext.getCmp('userMenuToolbar').setTitle(getAccountName());

              if (Ext.os.is.Android) {
                Ext.getCmp('UserMenu').setItems([Ext.getCmp('userMenuToolbar'), gen_5px_spacer, user_pic_header, gen_10px_spacer, user_menu_list, createBottomTabBar('icon_menu_tabbar')]);
              }
              
              Ext.getCmp('user_pic_header').setHtml(stored_user_pic_header_html);
            }

            if (v_orientation_chk === 0 || v_orientation_chk === 3) {
              if (isIphoneX) {
                if (Ext.Viewport.determineOrientation() === 'landscape') {     
                  adjustLandscapeForIPhoneX();
                } else {
                  revertFromLandscapeIPhoneX();
                }
              }

              if (Ext.os.is.iOS && Ext.Viewport.determineOrientation() === 'landscape') {
                adjustLandscapeForIPhone();
              } else if (Ext.os.is.iOS) {
                revertFromLandscapeIPhone();
              }

              if (Ext.os.is.iOS) {
                updateOrientation(getAccountName);
              }

              if (typeof window.indexedDB != 'undefined') {
                if (!isIphoneX) {
                  Ext.Viewport.setWidth('100%');
                }
                
                if (glb_device_type == 'tablet')  {
                  Ext.Viewport.setHeight('100%');
                }
              }
            }
           }, this, {buffer: 50 });
        
        },
        itemtap: {
            fn: function(_view, _index, _target, record, _event) {

                var value = record.get('value');
                setMask('Loading...'); // setMask for all icon selections
                if (value == 'pro_member') {
                    //setMask('Loading...');
                    pro_func();
                } else if (value == 'notifications4') {
                    invite_friends();
                } else if (value == 'Discover') {
                    //setMask('Loading...');
                    
                    discoverPeople();
                    glb_profile_check = 2;
                    document.getElementById('user_top_seach_btn').style.visibility = 'hidden';
                } else if (value == 'Tutorial') {

                    if (typeof device != 'undefined') {
                    setTutorialScreens(0);
                    //setMask('Loading...');
                    Ext.getCmp('tutorialCarousel').setActiveItem(0); // reset tutorial to start screen

                    if (Ext.Viewport.determineOrientation() === 'landscape') {
                      tutorialCarousel.direction = 'vertical';
                    } else {
                      tutorialCarousel.direction = 'horizontal';
                    }

                    tutorialCarousel.direction = 'vertical';
                    screen.lockOrientation('portrait');
                    Ext.getCmp('Tutorial').setItems([tutorialCarousel]);

                    Ext.Viewport.setActiveItem('Tutorial');
                    screen.lockOrientation(Ext.Viewport.determineOrientation());
                   setTimeout(function() {
                        clearMask();
                    }, 500);

                }else{
                    myvAlert('Tutorial is not supported on the web app');
                    clearMask();
                }
                
                }else if (value == 'Onboarding') {
                }
                else if (value == 'Options') {
                    profileView();
                } else if (value == 'n/a') {
                    clearMask();
                }
                else {
                  glb_current_list['type'] = ''; // forces reloading of lists by emptying out type
                  list_videos(value, account.get('user_id'), 0);
                }
            }
        }
    }
};


var sort_sz = '';
if (glb_device_type == 'tablet') {
    sort_sz = 'font-size: 1.5em;';
}

var home_refresh_var = {
    xtype: 'list',
    id: 'home_refresh_var',
    tite: 'title',
    style: settings_sz2,
    //store: home_obj,
    scrollable: {
        direction: 'vertical'
    },
    plugins: [{
        xclass: 'Ext.plugin.ListPaging',
        id: 'home_p',
        loadMoreText: '',
        autoPaging: true,
        noMoreRecordsText: 'No More Results',
    },{
        xclass: 'Ext.plugin.PullRefresh',
        pullText: 'Pull to refresh!',
        loadingText: 'Refreshing...',
        autoSnapBack: true,
        scrollerAutoRefresh: true,
    }],
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    itemTpl: recent_listTpl,
    listeners: {
        itemtap: {
            fn: function(_view, _index, _target, record, event) {
                var prof = record.get('profile_photo'); 
                var pressed = window.innerWidth - event.pageX;

                if (prof) {

                    if (glb_sortby == 'Recent' && pressed > 75) {
                        var video_id = record.get('video_id');
                        getVideo(video_id, 'videobyid');
                    } else if (pressed > 75) {
                        var master_id = record.get('master_id');
                        getVideo(master_id, 'videobymaster');
                    } else {
                        setTimeout(function() {
                            Ext.getCmp('home_refresh_var').deselectAll();
                        }, 5);
                    }
                } else if (glb_sortby == 'Popular') {
                    var master_id = record.get('master_id');
                    getVideo(master_id, 'videobymaster');
                } else {
                    setTimeout(function() {
                        Ext.getCmp('home_refresh_var').deselectAll();
                    }, 5);
                }

            }

        }
    }
};

var myUserList = {
    xtype: 'panel',
    scrollDock: 'top',
    docked: 'top',
    id: 'myUserList',
    items: [{
        html: ""
    }, ]
};

//////////

var swipe_btn_sz = 160;
if (glb_device_type == 'tablet') {
    swipe_btn_sz = 190;
}


Ext.define('PLabs.plugin.SlideActions', {
    extend: 'Ext.Component',
    alias: 'plugin.slideactions',
    requires: ['Ext.Anim'],

    config: {
        list: null,
        buttons: [],
        minDrag: 80,
        openPosition: swipe_btn_sz,
        animation: {
            duration: 250,
            easing: {
                type: 'ease-out'
            }
        },
        actionsBackground: "#006bb6",
        itemBackground: '#ffffff',
        // boxShadow: '5px 0px 5px 0px #aaa'
        boxShadow: 'none'
    },

    init: function(list) {
        this.setList(list);

        list.getScrollable().getScroller().on({
            scroll: this.onScrollStart,
            scrollend: this.onScrollEnd,
            scope: this
        });

        list.on({
            itemtap: this.onTap,
            select: this.onTap,
            updatedata: this.updateData,
            itemtouchmove: this.onTouchMove,
            hide: this.removeButtons,
            refresh: this.removeButtons,
            load: this.removeButtons,
            slide: this.removeButtons,
            scope: this
        }); 
        list.setBubbleEvents(true);
    },

    actualItem: null,
    actualActions: null,

    scrolling: false,

    onScrollEnd: function() {
        this.scrolling = false;
    },
    onScrollStart: function(_scroller, _x, _y) {
        this.scrolling = true;
        if (this.actualItem) {
            this.removeButtons(false);
        }
    },
    updateData: function() {
        if (this.actualItem) {
            this.removeButtons();
        }
    },
    onTap: function(_list, _index, target, _record, _e, _eOpts) {
        var stop = this.actualItem != null && target.element.down('.x-innerhtml') == this.actualItem.getElement();

        this.removeButtons();

        if (stop) {
            return false;
        }
    },
    onTouchMove: function(list, _index, target, record, _e, _eOpts) {
        /// need to only sub's will scroll
        this.scrolling = false;

        if (glb_current_list['type'] == "Profile" || glb_current_list['type'] == "subscription_list" || Ext.Viewport.getActiveItem().id == "MsgList" || glb_current_list['type'] == "Queue" || glb_current_list['type'] == "User" || glb_current_list['type'] == "Collection" || glb_current_list['type'] == "Channel" || glb_current_list['type'] == "Downloads") {

            if(glb_current_list['type'] == "Queue" || glb_current_list['type'] == "Profile" || glb_current_list['type'] == "Downloads" || Ext.Viewport.getActiveItem().id == "MsgList" ){
                glb_addbtn_fix = 1;
            }

            if(glb_addbtn_fix == 0){
                return;
            }

            if (this.scrolling) return false;

            var me = this;

            var element = target.element.down('.x-innerhtml');

            var initialOffset = {
                x: 0,
                y: 0
            };

            if (me.actualItem && me.actualItem.getElement() != element) {

                me.removeButtons();
            }

            if (!me.actualItem) {
                me.actualRecord = record;
                me.actualActions = me.createButtonsDiv(target.element);
                element.setStyle('background', me.config.itemBackground);

                me.actualItem = new Ext.util.Draggable({
                    element: element,
                    constraint: false,
                    direction: 'horizontal',
                    listeners: {



                        dragstart: function(self, _e, _startX, _startY) {


                            if (self.getOffset().x == -1 * me.config.openPosition) {
                                initialOffset = {
                                    x: -1 * me.config.openPosition,
                                    y: 0
                                };
                            }
                        },
                        drag: function(self, _e, newX, _newY) {

                            if (self.getOffset().x > 0) {
                                self.setOffset(0, 0);
                            } else if (Math.min(initialOffset.x, newX) - Math.max(initialOffset.x, newX) > -1 * me.config.minDrag) {
                                self.setOffset(0, -30);
                            } else {
                                //list.getScrollable().getScroller().fireEvent('scrollend');
                                list.setScrollable(false);
                            }

                        },
                        dragend: function(self, _e, _endX, _endY) {
                            if (self.getOffset().x < -1 * (me.config.openPosition / 2)) {
                                self.setOffset(-1 * me.config.openPosition, 0, me.config.animation);
                            } else {
                                self.setOffset(0, 0, me.config.animation);
                                Ext.destroy(me.actualActions);
                                Ext.destroy(me.actualItem);
                                me.actualItem = null;
                                me.actualActions = null;
                                me.actualRecord = null;
                                element.setStyle('box-shadow', null);
                            }
                            setTimeout(function() {
                                list.setScrollable(true);
                            }, 250);
                            // list.setScrollable(true);
                        }
                    }
                });
            }
        }
    },

    removeButtons: function(timeout) {
        if (typeof timeout == 'undefined') {
            timeout = true;
        }

        if (this.actualItem) {
            if (typeof this.actualItem.getElement() == 'undefined') {
                this.actualItem = null;
                this.actualRecord = null;
                this.actualActions = null;
            } else {
                var actualItem = this.actualItem;
                actualItem.setOffset(0, 0, this.config.animation);
                var actualActions = this.actualActions;
                if (actualItem.getElement() && actualItem.getElement().dom) {
                    actualItem.getElement().setStyle('box-shadow', null);
                }
                //To close actual item with animation
                setTimeout(function() {
                    Ext.destroy(actualActions);
                    Ext.destroy(actualItem);
                }, timeout ? 500 : 0);
                this.actualItem = null;
                this.actualRecord = null;
                this.actualActions = null;
            }
        }
        if (this.list && !this.list.getScrollable()) {
            this.list.getScroller().fireEvent('scrollend');
            this.list.setScrollable(true);
        }
    },

    createButtonsDiv: function(element) {

        if (Ext.Viewport.getActiveItem().id == "MsgList") {

            var me = this;
            var outer = '';


            outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background:#c70505; position:absolute; width: 100%; height: 100%"></div>', true);


            Ext.Array.each(me.config.buttons, function(button) {
                button['flex'] = 1;
                button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                button['record'] = me.actualRecord;
            });

            var panel = Ext.create('Ext.Panel', {
                layout: 'hbox',
                width: me.config.openPosition,
                cls: 'x-slide-action-buttons-outer',
                renderTo: outer,
                style: 'margin-left: auto;height: 100%;',
                items: me.config.buttons //[me.config.buttons[8], me.config.buttons[9]]
            });

            return outer;

        } else if (glb_current_list['type'] == "Queue") {

            var me = this;
            var outer = '';

            outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background:#c70505; position:absolute; width: 100%; height: 100%"></div>', true);

            Ext.Array.each(me.config.buttons, function(button) {
                button['flex'] = 1;
                button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                button['record'] = me.actualRecord;
            });

            var panel = Ext.create('Ext.Panel', {
                layout: 'hbox',
                width: me.config.openPosition,
                cls: 'x-slide-action-buttons-outer',
                renderTo: outer,
                style: 'margin-left: auto;height: 100%;',
                items: [me.config.buttons[2], me.config.buttons[3]]
            });

            return outer;



        } else if (glb_current_list['type'] == "subscription_list") {

            // following
            var me = this;
            var outer = '';


            outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background: ' + me.config.actionsBackground + '; position:absolute; width: 100%; height: 100%"></div>', true);

            Ext.Array.each(me.config.buttons, function(button) {
                button['flex'] = 1;
                button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                button['record'] = me.actualRecord;

                if (me.actualRecord.get('status') == "1") {
                    button['text'] = 'Mute';
                } else {
                    button['text'] = 'Unmute';
                }
            });

            var panel = Ext.create('Ext.Panel', {
                layout: 'hbox',
                width: me.config.openPosition,
                cls: 'x-slide-action-buttons-outer',
                renderTo: outer,
                style: 'margin-left: auto;height: 100%;',
                items: [me.config.buttons[0], me.config.buttons[1]]
            });

            return outer;

        } else if (glb_current_list['type'] == "User") {

            // user
            var me = this;
            var outer = '';

            if (me.actualRecord.data['bc_owner'] == account.get('user_id')) {


                outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background: ' + me.config.actionsBackground + '; position:absolute; width: 100%; height: 100%"></div>', true);

                Ext.Array.each(me.config.buttons, function(button) {
                    button['flex'] = 1;
                    button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                    button['record'] = me.actualRecord;
                });
    
    
    
                var panel = Ext.create('Ext.Panel', {
                    layout: 'hbox',
                    width: me.config.openPosition,
                    cls: 'x-slide-action-buttons-outer',
                    renderTo: outer,
                    style: 'margin-left: auto;height: 100%;',
                    items: [me.config.buttons[4], me.config.buttons[9]]
                });
    
                return outer;



            }else{
                return outer;
            }



        } else if (glb_current_list['type'] == "Collection") {

            // user
            var me = this;
            var outer = '';
            
            
            if (me.actualRecord.data['bc_owner'] == account.get('user_id')) {


            outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background: ' + me.config.actionsBackground + '; position:absolute; width: 100%; height: 100%"></div>', true);

            Ext.Array.each(me.config.buttons, function(button) {
                button['flex'] = 1;
                button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                button['record'] = me.actualRecord;


            });

            var panel = Ext.create('Ext.Panel', {
                layout: 'hbox',
                width: me.config.openPosition,
                cls: 'x-slide-action-buttons-outer',
                renderTo: outer,
                style: 'margin-left: auto;height: 100%;',
                items: [me.config.buttons[5], me.config.buttons[8]]
            });

            return outer;

        }else{
            return outer;
        }

        } else if (glb_current_list['type'] == "Channel") {

            // user
            var me = this;
            var outer = '';


            if (me.actualRecord.data['bc_owner'] == account.get('user_id')) {
            outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background: ' + me.config.actionsBackground + '; position:absolute; width: 100%; height: 100%"></div>', true);

            Ext.Array.each(me.config.buttons, function(button) {
                button['flex'] = 1;
                button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                button['record'] = me.actualRecord;


            });

            var panel = Ext.create('Ext.Panel', {
                layout: 'hbox',
                width: me.config.openPosition,
                cls: 'x-slide-action-buttons-outer',
                renderTo: outer,
                style: 'margin-left: auto;height: 100%;',
                items: [me.config.buttons[6]]
            });

            return outer;
        }else{
            return outer;
        }

        } else if (glb_current_list['type'] == "Downloads") {

            // user
            var me = this;
            var outer = '';


            outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background:#c70505; position:absolute; width: 100%; height: 100%"></div>', true);

            Ext.Array.each(me.config.buttons, function(button) {
                button['flex'] = 1;
                button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                button['record'] = me.actualRecord;


            });

            var panel = Ext.create('Ext.Panel', {
                layout: 'hbox',
                width: me.config.openPosition,
                cls: 'x-slide-action-buttons-outer',
                renderTo: outer,
                style: 'margin-left: auto;height: 100%;',
                items: [me.config.buttons[7]]
            });

            return outer;
        }else if(glb_current_list['type'] == "Profile"){
            // bookmark

            var me = this;
            var outer = '';


            outer = Ext.DomHelper.insertFirst(element, '<div class="x-slide-action-outer" style="background:#c70505; position:absolute; width: 100%; height: 100%"></div>', true);

            Ext.Array.each(me.config.buttons, function(button) {
                button['flex'] = 1;
                button['style'] = 'height:100%; border:none; box-shadow:none; z-index:auto; border-radius:0px; background-image:none; font-size:.8em;';
                button['record'] = me.actualRecord;


            });

            var panel = Ext.create('Ext.Panel', {
                layout: 'hbox',
                width: me.config.openPosition,
                cls: 'x-slide-action-buttons-outer',
                renderTo: outer,
                style: 'margin-left: auto;height: 100%;',
                items: [me.config.buttons[10], me.config.buttons[11]]
            });

            return outer;
        }
    },
})

var list_videos_var = {
    xtype: 'list',
    id: 'list_videos_var',
    tite: 'title',
    style: settings_sz2,
    store: list_videos_obj,
    items: [myUserList],
    requires: ['PLabs.plugin.SlideActions'],
    scrollable: {
        direction: 'vertical',
        directionLock: true,
        scroller: {
            listeners: {
                refresh: function(){
                  if(Ext.getCmp('user_top_seach_btn').getText() == 'action'){
                         var x = document.getElementsByClassName('masseditbox');
        

                          var i;
                          for (i = 0; i < x.length; i++) {
                          x[i].style.display = "block";
                          }


                           var c = document.getElementsByClassName('massedit');
                           var j;
                           for(j = 0; j < c.length; j++){
                            
                             if(glb_mass_edits.match(  (c[j].id).toString()  ) ){
                                 c[j].checked = true;
                             }

                           }


                  }
                },
                scroll: function(_scroller, _x, _y, _eOpts) {},
                scrollend: function(_scroller, _x, y, _eOpts) {
                    if (glb_current_list['type'] == "subscriptions" && y > 140) {
                        Ext.getCmp('sub_carousel').hide({
                            type: 'slide',
                            direction: 'up',
                            out: true,
                            easing: 'ease-in-out'
                        });
                    }
                }
            }
        }
    },
    plugins: [{
            xclass: 'Ext.plugin.ListPaging',
            id: 'video_p',
            loadMoreText: '',
            autoPaging: true,
            noMoreRecordsText: '',
        }, {
            xclass: 'Ext.plugin.PullRefresh',
            pullText: 'Pull to refresh!',
            loadingText: 'Refreshing...',
            autoSnapBack: true,
            scrollerAutoRefresh: true,
        }
        , {


            xclass: 'PLabs.plugin.SlideActions',
            buttons: [{
                    xtype: 'button',
                    ui: 'action',
                    hidden: false,
                    listeners: {
                        tap: function(button, e) {

                            setMask('Loading...');

                            var s_id = button.getRecord().get('sid');
                            var muted = button.getRecord().get('status');
                            var muted_set = '';
                            if (muted == '1') {
                                muted_set = '2';
                            } else {
                                muted_set = '1';
                            }

                            mute_subscription(s_id, muted_set);
                            e.stopPropagation();

                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Un<br/>follow</div>",
                    ui: 'decline',
                    hidden: false,
                    listeners: {
                        tap: function(button, e) {

                            setMask('Loading...');

                            var s_id = button.getRecord().get('sid');
                            mute_subscription(s_id, '0');
                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                },

                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Remove</div>",
                    hidden: false,
                    ui: 'decline',
                    listeners: {
                        tap: function(button, e) {
                            setMask('Loading...');
                            // queue
                            var v_id = button.getRecord().get('video_id');
                            var rowIndex = list_videos_obj.indexOf(button.getRecord());
                            delete_queue(v_id, 0, rowIndex);
                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Watched</div>",
                    hidden: false,
                    ui: 'normal',
                    listeners: {
                        tap: function(button, e) {
                            // queue
                            setMask('Loading...');
                            var v_id = button.getRecord().get('video_id');
                            var rowIndex = list_videos_obj.indexOf(button.getRecord());
                            delete_queue(v_id, 1, rowIndex);
                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Edit</div>",
                    hidden: false,
                    ui: 'action',
                    listeners: {
                        tap: function(button, e) {

                            setMask('Loading...');

                            var c_id = button.getRecord().get('id');
                            edit_collection(c_id, 'collection');
                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                },

                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Edit</div>",
                    hidden: false,
                    ui: 'action',
                    listeners: {
                        tap: function(button, e) {

                            setMask('Loading...');

                            var c_id = button.getRecord().get('id');
                            edit_collection(c_id, 'channel');
                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Edit</div>",
                    hidden: false,
                    ui: 'action',
                    listeners: {
                        tap: function(button, e) {

                            setMask('Loading...');

                            var video_id = button.getRecord().get('video_id');
                            edit_video(video_id, 1);
                            e.stopPropagation();

                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Delete</div>",
                    hidden: false,
                    ui: 'decline',
                    listeners: {
                        tap: function(button, e) {

                            var master_id = button.getRecord().get('master_id');
                            var video_file = button.getRecord().get('video_file');
                            var rowIndex = list_videos_obj.indexOf(button.getRecord());

                            delete_download_handler(master_id, encodeURIComponent(video_file), rowIndex);

                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Delete</div>",
                    hidden: false,
                    ui: 'decline',
                    listeners: {
                        tap: function(button, e) {

                            var c_id = button.getRecord().get('id');
                            delete_channelcollection_handler('channel', c_id);

                            e.stopPropagation();
            
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Delete</div>",
                    hidden: false,
                    ui: 'decline',
                    listeners: {
                        tap: function(button, e) {

                            var c_id = button.getRecord().get('id');
                            delete_channelcollection_handler('collection', c_id);

                            e.stopPropagation();
               
                        },
                        scope: this
                    }
                },
                {
                    // bookmark page
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Delete</div>",
                    hidden: false,
                    ui: 'decline',
                    listeners: {
                        tap: function(button, e) {

                            var c_id = button.getRecord().get('video_id');
                            var rowIndex = list_videos_obj.indexOf(button.getRecord());
                            delete_video_book(c_id, rowIndex);

                            e.stopPropagation();
               
                        },
                        scope: this
                    }
                },
                {
                    // bookmark page
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Edit</div>",
                    hidden: false,
                    ui: 'normal',
                    listeners: {
                        tap: function(button, e) {

                            setMask('Loading...');
                            var c_id = button.getRecord().get('video_id');
                            edit_video(c_id, 1);

                            e.stopPropagation();
               
                        },
                        scope: this
                    }
                }
            ]
        }
    ],
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    listeners: {

        itemtap: {
            fn: function(_view, _index, _target, record, event) {

                  if(Ext.getCmp('user_top_seach_btn').getText() == 'action'){
                         Ext.getCmp('list_videos_var').deselectAll();
                      return;

                   }

                var video_id = record.get('video_id');
                var c_user_id = record.get('c_user_id');
                var listof = record.get('listof');
                var id = record.get('id');
                var type = glb_current_list['type'];

                var prof = record.get('profile_photo');
                var pressed = window.innerWidth - event.pageX;


                if (type == 'Collection' || type == 'User' || type == 'subscription_list') {

                    glb_back_data_channel_bug = id;

                    if (listof == 'Channel') {
                        glb_back_data_channel_bug = id;
                    }

                    if (prof && pressed < 75) {
                        setTimeout(function() {
                            Ext.getCmp('list_videos_var').deselectAll();
                        }, 5);
                    } else {
                        list_videos(listof, id, 0);
                    }


                } else if (type == 'Downloads') {
                    getVideo(video_id, 'videobydownload');
                } else if (type == 'collectors_v2') {
                    list_videos('User', c_user_id, 0, 'nocoll2');
                } else {
                    if (prof && pressed < 75) {
                        setTimeout(function() {
                            Ext.getCmp('list_videos_var').deselectAll();
                        }, 5);
                    } else { 
                        getVideo(video_id, 'videobyid');                     
                    }
                }
            }



        }
    }
};

function handleScroll(_scrollerObject, _offsetObject) {
    //
}

// chat func
var search_video_chat_bar = {
    xtype: 'toolbar',
    style: toolbar_sz,
    items: [{
        xtype: 'searchfield',
        id: 'search_video_chat_bar',
        width: '100%',
        height: search_ht2,
        listeners: {
            action: function() {
                'use strict';
               var q = Ext.getCmp('search_video_chat_bar').getValue();
               populateSendVideoList(null, q);
            }
        }
    }]
};

var search_video_chat_form = {
    id: 'search_form_chat',
    scrollable: null,
    xtype: 'formpanel',
    width: '100%',
    height: search_ht,
    items: search_video_chat_bar
};

var send_video_var = {
    xtype: 'list',
    id: 'send_video_var',
    style: settings_sz2,
    store: send_video_obj,
    scrollable: {
     direction:'vertical',
    },
    pressedCls: '',
    selectedCls: '',
    plugins: [{ 
        xclass: 'Ext.plugin.ListPaging',
        id:'send_video_v',
        loadMoreText: 'More...',
        autoPaging: true,
        noMoreRecordsText: '',
    }],
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    itemTpl: send_video_listTpl,
    listeners: {
        itemtap: {
            fn: function(_view, _index, _target, _record, _event) {
            }
        }
    }
};

var search_var = {
    xtype: 'list',
    id: 'search_var',
    tite: 'title',
    style: settings_sz2,
    store: home_obj, //search_obj,
    scrollable: {
        direction: 'vertical',
        directionLock: true
    },
    plugins: [{
        xclass: 'Ext.plugin.ListPaging',
		id: 'search_p',
        loadMoreText: '',
        autoPaging: true,
        noMoreRecordsText: 'No More Results',
    }, {
        xclass: 'Ext.plugin.PullRefresh',
        pullText: 'Pull to refresh!',
        loadingText: 'Refreshing...',
        autoSnapBack: true,
        scrollerAutoRefresh: true,
    }],
    width: '100%',
    flex: 1,
    cls: 'myv_list',
	itemTpl: recent_listTpl,
    listeners: {
        itemtap: {
            fn: function(_view, _index, _target, record, event) {
                var video_id = record.get('video_id');
                var video_url = record.get('video_url');
                var listof = record.get('listof');
                var user_id = record.get('user_id');
                var id = record.get('id');
                var prof = record.get('profile_photo');
                var pressed = window.innerWidth - event.pageX;

                var type = glb_scope;

                if (type == 'web') {
                    getVideo(video_id, 'videobyurl');
                } else if (type == 'subscription_list') {
                    if (prof && pressed > 75)
                        list_videos(listof, id, 0);
                    else
                        setTimeout(function() {
                            Ext.getCmp('search_var').deselectAll();
                        }, 5);
                } else if (type == 'Downloads') {
                    getVideo(video_id, 'videobydownload');
                } else if (type == 'profiles') {
                    list_videos('User', user_id, 0);
                } else {
                    getVideo(video_id, 'videobyid');
                }

                search_back_preserve_hack = 0;
            }
        }
    }
};

var settingsToolbar = {
    xtype: 'toolbar',
    title: 'Settings',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons,

    listeners: {
        element: 'element',
        tap: function(view, _index, _target, _record, _event) {

            var pressed = window.innerWidth - view.pageX;
            if (pressed > 100 && pressed < (window.innerWidth - 100)) {
                var this_list = Ext.getCmp("Settings");
                if (this_list.rendered) {
                    var scroller36 = Ext.getCmp('Settings').getScrollable().getScroller();
                    scroller36.scrollToTop(true);
                }
            }
        }
    }
};

function createToolBar(title, items) {
  return {
    xtype: 'toolbar',
    title: title,
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: items,
  };
}

var inviteToolbar = createToolBar('Invite Friends', gen_top_buttons);
var helpToolbar = createToolBar('Help', gen_top_buttons);
var userLoginToolbar = createToolBar('Login', gen_top_buttons);
var userRegToolbar = createToolBar('Registration', gen_top_buttons);
var userSavePhoneToolbar = createToolBar('Save Phone Number', gen_top_buttons);
var generalToolbar = createToolBar('MyVidster', general_top_buttons);
var chromeToolbar = createToolBar('MyVidster', chrome_top_buttons);

var lockToolbar = {
    xtype: 'toolbar',
    title: 'MyVidster',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
};

//change here

var sub_carousel = {
    id: 'sub_carousel',
    xtype: 'carousel',
    items: [{
        html: '<div align="center" style="margin-top:' + settings_sz9 + ';margin-bottom:0px;' +
        settings_sz3 + '"> Discover </div>',
        docked: 'top'
    }, ],
    indicator: false,
    hidden: true,
    height: '75px',
    width: '100%'
}

var follower_advice = {
    id: 'follower_advice',
    xtype: 'panel',
    items: [{
        html: '<div align="center" style="margin-top:' + settings_sz9 + ';margin-bottom:5px;' +
        settings_sz3 + '"> Swipe left to mute </div>',
        docked: 'top'
    }, ],
    indicator: false,
    hidden: true,
    height: settings_sz4,
    width: '100%'
}

var myHome = {
    xtype: 'panel',
    id: 'myHome',
    docked: 'top',
    items: [{
        html: ""
    }]
};

//MADE CHANGES HERE
Ext.define('myVidster.view.Home', {
    extend: 'Ext.Panel',
    requires: ['Ext.Carousel'],
    id: 'Home',
    alias: 'widget.Home',
    config: {
        cls: 'myv_list',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(_e) {
                    }
                });
            }
        },
        autoScroll: true,
        items: [
            homeToolbar,
            myHome,
            home_refresh_var,
            createBottomTabBar('home_tabbar'),            
        ]
    }
});

function createView(id, alias, items) {
  return {
    extend: 'Ext.Panel',
    id: id,
    alias: alias,
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        items: items
    }
  };
}

Ext.define('myVidster.view.EditCollection',
  createView('EditCollection','widget.EditCollection', [
          EditCollectionToolbar,
          EditCollectionForm,
          createBottomTabBar('home_tabbar_EditCollection')
]));

Ext.define('myVidster.view.Comment',
  createView('Comment','widget.Comment', [
          commentsToolbar,
          videoCommentForm,
          createBottomTabBar('home_tabbar_Comment')
]));

Ext.define('myVidster.view.Collect',
  createView('Collect','widget.Collect', [
          collectToolbar,
          videoCollectForm,
          createBottomTabBar('home_tabbar_Collect')
]));

Ext.define('myVidster.view.Payment',
  createView('Payment','widget.Payment', [
          paymentToolbar,
          PaymentForm,
          createBottomTabBar('home_tabbar_Payment')
]));

Ext.define('myVidster.view.EditVideo',
  createView('EditVideo','widget.EditVideo', [
          editVideoToolbar,
          videoEditForm,
          createBottomTabBar('home_tabbar_EditVideo')
]));


function video_swipe(e) {
    try{
	var video_options_h2 = document.getElementsByClassName("video-dev")[0];
    var page_h = video_options_h2.offsetHeight;
    var distanceToTop;
	
	if (glb_myPlayer) {
		if (glb_myPlayer.paused()) {
			distanceToTop = video_options_h2.getBoundingClientRect().top + 30;  //for thumbnail skipping
		}
		else {
			distanceToTop = video_options_h2.getBoundingClientRect().top + page_h;
		}

	    if (e.pageY > distanceToTop && isFullscreenNow() == false) {
            onSwipeVideo(e.direction);
        }
	}
	else {
		onSwipeVideo(e.direction);
    }
    
  }catch(err){
      //alert('error!');
      return;
    }
}

var video_holder = {
    id: 'video_holder',
    xtype: 'panel',
    flex: 2,
    scrollable: {
        direction: 'vertical',
        scroller: {
            listeners: {
                scroll: function(_scroller, _x, _y, _eOpts) {

                    var today = new Date();
                    var date = today.getMonth()+'/'+(today.getDate()+1)+'/'+today.getFullYear();
                    var hours = today.getHours();
                    var minutes = today.getMinutes();
                    var ampm = hours >= 12 ? 'PM' : 'AM';
                    hours = hours % 12;
                    hours = hours ? hours : 12; 
                    minutes = minutes < 10 ? '0'+minutes : minutes;
                    var strTime = hours + ':' + minutes + ampm;
                    var dateTime = date+' '+strTime;

                    var arrow4 = '<div style="width: 20px; height: 0; border-left: 10px solid transparent; border-right:10px solid transparent; border-top: 20px solid gray;"></div>';
                    var arrow5 = '<div style="width: 20px; height: 0; border-left: 10px solid transparent; border-right:10px solid transparent; border-bottom: 20px solid gray;"></div>';

                    var ref = document.getElementById('vid_ref1');
                    ref.innerHTML = '<table border=0 style="margin:0px auto;"><td style="padding-right:18px;padding-top:18px;"> '+arrow4+' &nbsp;&nbsp;</td> <td>  <h3 style="font-weight: 700; font-size: 1em; text-align: center;">Pull to refresh!</h3><div style="font-size: .7em; text-align: center;">Last Updated:&nbsp;'+dateTime+'</div></td> </table>';
                    if(_y > 10){
                        ref.style.display = 'none';
                    }else{
                        ref.style.display = '';
                    }
                    if(_y < -70){
                        ref.innerHTML = '<table border=0 style="margin:0px auto;"><td style="padding-right:18px;padding-top:18px;">'+arrow5+' &nbsp;&nbsp;</td><td>  <h3 style="font-weight: 700; font-size: 1em; text-align: center;">Release to refresh...</h3><div style="font-size: .7em; text-align: center;">Last Updated:&nbsp;'+dateTime+'</div></td> </table>';
                    }else{
                        ref.innerHTML = '<table border=0 style="margin:0px auto;"><td style="padding-right:18px;padding-top:18px;">'+arrow4+' &nbsp;&nbsp;</td><td>  <h3 style="font-weight: 700; font-size: 1em; text-align: center;">Pull to refresh!</h3><div style="font-size: .7em; text-align: center;">Last Updated:&nbsp;'+dateTime+'</div></td> </table>';
                    }
                },
                scrollend: function(_scroller, _x, _y, _eOpts) {

                    var ref = document.getElementById('vid_ref1');
                    ref.innerHTML = "";
                    if(_scroller['flickStartPosition']['y'] < -70 && _scroller['isTouching'] == false && _scroller['isDragging'] == false){
                            getVideo(glb_current_video, glb_video_type, true);
                    }
                }
            }
        }
    },

    listeners: {
        initialize: function(panel, _eOpts) {
            panel.element.on({
                swipe: function(e, _view, _index, _target, _record) {
					video_swipe(e);
                }
            });
        }
    },
};

var video_holder2 = {
    id: 'video_holder2',
    xtype: 'panel',
    flex: 2,
    scrollable: {
        direction: 'vertical',
        scroller: {
            listeners: {
                scroll: function(_scroller, _x, _y, _eOpts) {

                    var today = new Date();
                    var date = today.getMonth()+'/'+(today.getDate()+1)+'/'+today.getFullYear();
                    var hours = today.getHours();
                    var minutes = today.getMinutes();
                    var ampm = hours >= 12 ? 'PM' : 'AM';
                    hours = hours % 12;
                    hours = hours ? hours : 12; 
                    minutes = minutes < 10 ? '0'+minutes : minutes;
                    var strTime = hours + ':' + minutes + ampm;
                    var dateTime = date+' '+strTime;

                    var arrow4 = '<div style="width: 20px; height: 0; border-left: 10px solid transparent; border-right:10px solid transparent; border-top: 20px solid gray;"></div>';
                    var arrow5 = '<div style="width: 20px; height: 0; border-left: 10px solid transparent; border-right:10px solid transparent; border-bottom: 20px solid gray;"></div>';


                    var ref = document.getElementById('vid_ref2');
                    ref.innerHTML = '<table border=0 style="margin:0px auto;"><td style="padding-right:18px;padding-top:18px;"> '+arrow4+' &nbsp;&nbsp;</td> <td>  <h3 style="font-weight: 700; font-size: 1em; text-align: center;">Pull to refresh!</h3><div style="font-size: .7em; text-align: center;">Last Updated:&nbsp;'+dateTime+'</div></td> </table>';
                    if(_y > 10){
                        ref.style.display = 'none';
                    }else{
                        ref.style.display = '';
                    }
                    if(_y < -70){
                        ref.innerHTML = '<table border=0 style="margin:0px auto;"><td style="padding-right:18px;padding-top:18px;">'+arrow5+' &nbsp;&nbsp;</td><td>  <h3 style="font-weight: 700; font-size: 1em; text-align: center;">Release to refresh...</h3><div style="font-size: .7em; text-align: center;">Last Updated:&nbsp;'+dateTime+'</div></td> </table>';
                    }else{
                        ref.innerHTML = '<table border=0 style="margin:0px auto;"><td style="padding-right:18px;padding-top:18px;">'+arrow4+' &nbsp;&nbsp;</td><td>  <h3 style="font-weight: 700; font-size: 1em; text-align: center;">Pull to refresh!</h3><div style="font-size: .7em; text-align: center;">Last Updated:&nbsp;'+dateTime+'</div></td> </table>';
                    }
                },
                scrollend: function(_scroller, _x, _y, _eOpts) {

                    var ref = document.getElementById('vid_ref2');
                    ref.innerHTML = "";
                    if(_scroller['flickStartPosition']['y'] < -70 && _scroller['isTouching'] == false && _scroller['isDragging'] == false){
                            getVideo(glb_current_video, glb_video_type, true);
                    }
                }
            }
        }
    },
    
    listeners: {
        initialize: function(panel, _eOpts) {
            panel.element.on({
                swipe: function(e, _view, _index, _target, _record) {
                   video_swipe(e);
                }
            });
        }
    }
};

var chrome_cast_volume = {
    xtype: 'sliderfield',
    id: 'chrome_cast_volume',
    label: '<div id="chomecast_mute"><a href="javascript:void(0);" onclick="chomecast_mute(\'mute\')"><span class="casticon-unmute"></span></a></div>',
    minValue: 0,
    maxValue: 10,
    cls: 'myv_slider2',
    labelWidth: '55px',
    listeners: {
        change: function(slider, _thumb, _newValue, _oldValue) {
            if (Ext.os.is('iOS')) {
                if (glb_ios_glb_session) {
                    glb_currentVolume = parseFloat(slider.getValue() / 10);
                    chromeCastChangeVolume(glb_currentVolume);
                }
            } else {
                if (glb_session) {
                    glb_currentVolume = parseFloat(slider.getValue() / 10);
                    glb_session.setReceiverVolumeLevel(glb_currentVolume, onSuccess, onFail);

                    output = '<a href="javascript:void(0);" onclick="chomecast_mute(\'mute\')"><span class="casticon-unmute" style="right:' + 5 + 'px;bottom:' + 5 + 'px;"></span></a>';
                    Ext.get('chomecast_mute').setHtml(output);
                }
            }
        }
    }
};

var chrome_cast_seeker = {
    xtype: 'sliderfield',
    id: 'chrome_cast_seeker',
    minValue: 0,
    maxValue: 100,
    cls: 'myv_slider',
    padding: 0,
    margin: 0,
    listeners: {
        change: function(slider, _thumb, _newValue, _oldValue) {
            if (Ext.os.is('iOS')) {
                if (glb_ios_currentMedia && glb_chromecast_listeners) {
                    chromeCastChangeStreamPosition(slider.getValue() / 100);
                }
            } else {
                if (glb_currentMedia && glb_chromecast_listeners) {
                    var request = new chrome.cast.media.SeekRequest();
                    request.currentTime = parseInt(slider.getValue() * glb_currentMedia.media.duration / 100);
                    glb_currentMedia.seek(request, onSuccess, onFail);
                }
            }
        }
    }
};

var chrome_holder = {
    scrollable: 'vertical',
    id: 'chromecasthold',
    items: [{
            html: '<div id="chrome_preview"></div>'
        }, {
            layout: 'hbox',
            items: [{
                html: '<div id="chrome_time" style="display:inline-block;margin-left:5px;">00:00:00</div>'
            }, {
                xtype: 'spacer'
            }, {
                html: '<div id="chrome_duration" style="display:inline-block;margin-right:5px;">00:00:00</div>'
            }]
        }, chrome_cast_seeker,
        chrome_cast_volume, {
            layout: 'hbox',
            items: [{
                xtype: 'spacer'
            }, {
                xtype: 'button',
                text: 'Stop',
                margin: '20',
                width: '100px',
                handler: function() {
                    if (glb_currentMedia || glb_ios_currentMedia) {
                        chomecast_stop();
                    }
                }
            }, {
                xtype: 'spacer'
            }]
        }
    ]
};

var lock_holder = {
    xtype: 'formpanel',
    id: 'lock_holder',
    scrollable: 'vertical',
    items: [{
        html: '<div id="lock_dialog" align="center" style="padding-top: 15%; padding-bottom:5%">lock_dialog</div>'
    }, {
        layout: 'hbox',
        height: '50px',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'passwordfield',
            name: 'lock_pw1',
            width: '50px',
            height: '50px',
            cls: 'myv_hack_lock',
            disabled: true
        }, {
            xtype: 'spacer',
            width: '10px'
        }, {
            xtype: 'passwordfield',
            name: 'lock_pw2',
            width: '50px',
            height: '50px',
            cls: 'myv_hack_lock',
            disabled: true
        }, {
            xtype: 'spacer',
            width: '10px'
        }, {
            xtype: 'passwordfield',
            name: 'lock_pw3',
            width: '50px',
            height: '50px',
            cls: 'myv_hack_lock',
            disabled: true
        }, {
            xtype: 'spacer',
            width: '10px'
        }, {
            xtype: 'passwordfield',
            name: 'lock_pw4',
            width: '50px',
            height: '50px',
            cls: 'myv_hack_lock',
            disabled: true
        }, {
            xtype: 'spacer'
        }]
    }, {
        xtype: 'spacer',
        height: '20px'
    }, {
        layout: 'hbox',
        height: '95px',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            text: '1',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(1);
            }
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: '2',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(2);
            }
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: '3',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(3);
            }
        }, {
            xtype: 'spacer'
        }]
    }, {
        layout: 'hbox',
        height: '95px',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            text: '4',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(4);
            }
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: '5',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(5);
            }
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: '6',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(6);
            }
        }, {
            xtype: 'spacer'
        }]
    }, {
        layout: 'hbox',
        height: '95px',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            text: '7',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(7);
            }
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: '8',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(8);
            }
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: '9',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(9);
            }
        }, {
            xtype: 'spacer'
        }]
    }, {

        layout: 'hbox',
        height: '95px',
        items: [{
            xtype: 'spacer'
        }, {
            xtype: 'button',
            text: 'Back',
            id: 'lockBTN',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            hidden: false,
            handler: function() {
                backLock();
            }
        }, {
            xtype: 'spacer',
            id: 'lockSpace',
            hidden: true,
            width: '75px'
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: '0',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                updateLock(0);
            }
        }, {
            xtype: 'spacer',
            width: '20px'
        }, {
            xtype: 'button',
            text: 'Clear',
            width: '75px',
            height: '75px',
            ui: 'confirm',
            handler: function() {
                clearLock(); // action, confirm, and decline
            }
        }, {
            xtype: 'spacer'
        }]

    }]
};

var seeker_time2 = '60px';
if (glb_device_type == 'tablet' || glb_device_type == 'desktop') {
    seeker_time2 = "70px";
}

Ext.define('myVidster.view.Video', {
    extend: 'Ext.Panel',
    id: 'Video',
    alias: 'widget.Video',
    config: {
        fullscreen: true,
        layout: 'card',
        ui: 'dark',
        items: [            
            videoToolbar,
            video_breadcrumb,  
            video_holder,
            createBottomTabBar('home_tabbar_video1'),
        ]
    }
});

Ext.define('myVidster.view.Video2', {
    extend: 'Ext.Panel',
    id: 'Video2',
    alias: 'widget.Video2',
    config: {
        fullscreen: true,
        layout: 'card',
        ui: 'dark',
        items: [
            videoToolbar2,
            video_breadcrumb2,
            video_holder2,
            createBottomTabBar('home_tabbar_video2'),
        ]
    }
});

var chrome_seek_holder = {
    id: 'chromecastseeker',
    docked: 'top',
    items: [{
            html: '<div id="chrome_seeker"></div>'
        }]
        
    };

Ext.define('myVidster.view.ChromeCast', {
    extend: 'Ext.Panel',
    id: 'ChromeCast',
    alias: 'widget.ChromeCast',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        layout: 'card',
        items: [chromeToolbar, chrome_holder, chrome_seek_holder, createBottomTabBar('home_tabbar_chromecast')]
    }
});

Ext.define('myVidster.view.LockScreen', {
    extend: 'Ext.Panel',
    id: 'LockScreen',
    alias: 'widget.LockScreen',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        layout: 'card',
        items: [lockToolbar, lock_holder]
    }
});


Ext.define('myVidster.view.Settings', {
    extend: 'Ext.Panel',
    id: 'Settings',
    alias: 'widget.Settings',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(_e) {
                        //onSwipe(e.direction, 4);
                    }
                });
            }
        },
        items: [settingsToolbar, settingsForm, createBottomTabBar('home_tabbar_settings')]
    }
});

var mySearch = Ext.create('Ext.Panel', {
    items: [{
        html: ""
    }]
});

var search_s_options = {
    //fullscreen: true,
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items: [{
        xtype: 'selectfield',
        style: settings_sz,
        id: 'search_opt',
        flex: 0.65,
        value: 'myVidster',
        listeners: {
            change: function() {

                glb_scope = Ext.getCmp('search_opt').getValue();
                var q = Ext.getCmp('search_bar').getValue();
                if (typeof device != 'undefined' && q.search('following:') < 0 && q.search('followers:') < 0) {
                    cordova.plugins.Keyboard.show();
                    setTimeout(function() {
                        Ext.getCmp('search_bar').focus();
                    }, 500);
                }

                if (glb_scope != 'profiles' && glb_scope != 'web' && glb_scope != 'subscription_list') {
                    Ext.getCmp('search_opt2').show();
                    Ext.getCmp('search_opt').setFlex('0.65');
                } else {
                    Ext.getCmp('search_opt2').hide();
                    Ext.getCmp('search_opt').setFlex('1');
                }

                Ext.getCmp('search_bar').focus();
                document.getElementById("search_bar").focus();

            }
        },
        options: [{
            text: 'Users',
            value: 'profiles'
        }, {
            text: 'MyVidster',
            value: 'myVidster'
        }, {
            text: 'Web',
            value: 'web'
        }]
    }, {
        xtype: 'selectfield',
        style: settings_sz,
        flex: 0.35,
        id: 'search_opt2',
        value: 'relevant',
        hidden: true,
        listeners: {
            change: function() {
                glb_search_sort = Ext.getCmp('search_opt2').getValue();
                var q = Ext.getCmp('search_bar').getValue();
                if (typeof device != 'undefined' && q.search('following:') < 0 && q.search('followers:') < 0) {
                    //
                }
                if (q != "") {
                    video_search(q, 1, glb_scope, glb_scope_id, glb_list_name);
                }

                Ext.getCmp('search_bar').focus();
                document.getElementById("search_bar").focus();
            }
           
        },
        options: [{
            text: 'Relevant',
            value: 'relevant'
        }, {
            text: 'Recent',
            value: 'utc_posted'
        }, ]
    }]
};




Ext.define('myVidster.view.Search', {
    extend: 'Ext.Panel',
    id: 'Search',
    alias: 'widget.Search',
    config: {
        cls: 'myv_list',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(e) {
                        onSwipe(e.direction, 3);
                    }
                });
            }
        },
        autoScroll: true,
        items: [searchToolbar,
        search_form,
        search_s_options,
        search_var,
        createBottomTabBar('search_tabbar')
        ]
    }
});

Ext.define('myVidster.view.Followers', {
    extend: 'Ext.Panel',
    id: 'Followers',
    alias: 'widget.Followers',
    config: {
        cls: 'myv_list',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(e) {
                        onSwipe(e.direction, 3);
                    }
                });
            }
        },
        autoScroll: true,


        items: []
    }
});

Ext.define('myVidster.view.UserLogin', {
    extend: 'Ext.Panel',
    id: 'UserLogin',
    alias: 'widget.UserLogin',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(e) {
                        onSwipe(e.direction, 2);
                    }
                });
            }
        },
        items: [{
                xtype: 'formpanel',
                scrollable: null,
                items: [{
                    xtype: 'fieldset',
                    style: login_sz,
                    items: [{
                        xtype: 'emailfield',
                        name: 'email',
                        id: 'email',
                        label: 'Email'
                    }, {
                        xtype: 'passwordfield',
                        name: 'password',
                        id: 'pw',
                        label: 'Password'
                    }]
                }, {
                    layout: {
                        type: 'hbox',
                        pack: 'center'
                    },
                    items: [{
                        xtype: 'button',
                        width: '100px',
                        margin: '0',
                        scope: this,
                        text: 'Login',
                        handler: function() {
                            var email = Ext.getCmp('email').getValue();
                            var pw = Ext.getCmp('pw').getValue();
                            loginUser(email, pw, 'UserMenu');
                        }
                    }, {
                        xtype: 'spacer',
                        width: button_sp_sz
                    }, {
                        xtype: 'button',
                        scope: this,
                        margin: '0',
                        width: '100px',
                        text: 'Register',
                        handler: function() {
                            Ext.Viewport.setActiveItem("UserRegistration");
                            searchviewbackhack();
                            custom_views_arr.push("UserRegistration");
                            var array_data = [0];
                            glb_custom_views_arr_data.push(array_data);
                        }
                    }]
                }, {
                  html: '<div align="center" onclick="reset_pw();" style="color:white;background-color:#00A300;padding:8px 0px;border-radius:8px;width:45vw; text-align:center; margin: auto; margin-top:20px;"' + reset_sp_sz + ';">Reset Password</div>'
                }]
            },
            userLoginToolbar, createBottomTabBar('home_tabbar_UserLogin')
        ]
    }
});

Ext.define('myVidster.view.UserRegistration', {
    extend: 'Ext.Panel',
    id: 'UserRegistration',
    alias: 'widget.UserRegistration',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(e) {
                        onSwipe(e.direction, 2);
                    }
                });
            }
        },
        items: [userRegToolbar, user_regisiterForm, createBottomTabBar('home_tabbar_UserRegistration')]
    }
});

Ext.define('myVidster.view.UserPhoneInput', {
    extend: 'Ext.Panel',
    id: 'UserPhoneInput',
    alias: 'widget.UserPhoneInput',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(e) {
                        onSwipe(e.direction, 2);
                    }
                });
            }
        },
        items: [
          userSavePhoneToolbar,
          user_inputPhoneForm,
          createBottomTabBar('home_tabbar_UserPhoneInput')
        ]
    }
});

Ext.define('myVidster.view.UserPhoneVerification', {
    extend: 'Ext.Panel',
    id: 'UserPhoneVerification',
    alias: 'widget.UserPhoneVerification',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(e) {
                        onSwipe(e.direction, 2);
                    }
                });
            }
        },
        items: [
          userRegToolbar,
          user_verifyPhoneForm,
          createBottomTabBar('home_tabbar_UserPhoneVerification')
        ]
    }
});

Ext.define('myVidster.view.UserMenu', {
    extend: 'Ext.Panel',
    id: 'UserMenu',
    alias: 'widget.UserMenu',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(_e) {

                    }
                });
            }
        },
        items: [userMenuToolbar,
          gen_5px_spacer,
          user_pic_header,
          gen_10px_spacer,
          user_menu_list,
          createBottomTabBar('icon_menu_tabbar'),
          //homeBottomBar_iconMenu,
        ]
    },
});

// chat func

// top part with followers and following tabs
var send_video_tabbar = {
    xtype: 'tabbar',
    id: 'send_video_tabbar',
    style: "background-color: #1C2F45",
    ui: 'light',
    docked: 'top',
    items: send_video_buttons,
};

var sendVideoToolbar = {
    xtype: 'toolbar',
    id: 'sendVideoToolbar',
    title: 'Send Video',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons,
};

Ext.define('myVidster.view.SendVideo', {
    extend: 'Ext.Panel',
    id: 'SendVideo',
    alias: 'widget.SendVideo',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [
          sendVideoToolbar, // top title with 'Send Video'
          send_video_tabbar, // tabs with Followers and Following
          search_video_chat_form,
          send_video_var,
          createBottomTabBar('user_tabbar_send_video'),
        ]
    }
});
// ***** chat func
// Recommendations

var userDiscoverToolbar = {
    xtype: 'toolbar',
    id: 'userDiscoverToolbar',
    title: 'Discover',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons
};

var discover_tabbar = {
    xtype: 'tabbar',
    id: 'discover_tabbar',
    style: "background-color: #1C2F45",
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons02
};

var discover_recommendations_var = {
    xtype: 'list',
    id: 'discover_recommendations_var',
    tite: 'title',
    style: settings_sz2,
    store: recommendations_obj,
    data: recommendationsForUser,
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    pressedCls: '',
    selectedCls: '',
    itemTpl: discover_recommendations_listTpl,
    listeners: {
       itemtap: {
            fn:  function(_element, index, _target, _record, event, _eventOptions) {
             if (event.target.className !== "follow_button_target") {

                setMask('Loading...');
                var id = recommendationsForUser[index].user_id;
                currentUserId = id;
                get_profile(id, Ext.Viewport.getActiveItem().id);
              }
            }
        },
    }
};

Ext.define('myVidster.view.Discover', {
    extend: 'Ext.Panel',
    id: 'Discover',
    alias: 'widget.Discover',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [userDiscoverToolbar,
        discover_tabbar,
        discover_recommendations_var,
        createBottomTabBar('user_tabbar_discover'),
        ]
    }
});

// Discover

var userDiscoToolbar = {
    xtype: 'toolbar',
    id: 'userDiscoToolbar',
    title: 'Discover',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons
};

var disco_tabbar = {
    xtype: 'tabbar',
    id: 'disco_tabbar',
    style: "background-color: #1C2F45",
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons01
};

var discover_discover_var = {
    xtype: 'list',
    id: 'discover_discover_var',
    tite: 'title',
    style: settings_sz2,
    store: discover_obj,
    data: discoverForUser,

    scrollable: {
        direction: 'vertical'
    },
    // plugins: [{
    //     xclass: 'Ext.plugin.PullRefresh',
    //     pullText: 'Pull to refresh!',
    //     loadingText: 'Refreshing...',
    //     autoSnapBack: true,
    //     scrollerAutoRefresh: true,
    // }],
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    pressedCls: '',
    selectedCls: '',
    itemTpl: discover_recommendations_listTpl,
    listeners: {
       itemtap: {
            fn:  function(_element, index, _target, _record, event, _eventOptions) {
             if (event.target.className !== "follow_button_target") {
                setMask('Loading...');
                var id = discoverForUser[index].user_id;
                currentUserId = id;
                get_profile(id, Ext.Viewport.getActiveItem().id);
              }

            }
        },

    }
};

Ext.define('myVidster.view.Disco', {
    extend: 'Ext.Panel',
    id: 'Disco',
    alias: 'widget.Disco',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [userDiscoToolbar,
        disco_tabbar,
        discover_discover_var,
        createBottomTabBar('user_tabbar_recommendations'),
        ]
    }
});

// Contacts

var userContactsToolbar = {
    xtype: 'toolbar',
    id: 'userContactsToolbar',
    title: 'Discover',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons
};

var userContactsToolbar_empty_contacts = {
    xtype: 'toolbar',
    id: 'userContactsToolbar_Empty_Contacts',
    title: 'Discover',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons
};

var userContactsToolbar_empty_recommendations = {
    xtype: 'toolbar',
    id: 'userContactsToolbar_empty_recommendations',
    title: 'Discover',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons
};

var contacts_tabbar = {
    xtype: 'tabbar',
    id: 'contacts_tabbar',
    style:"background-color: #1C2F45",
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons03
};
// set contacts list content here --
var contacts_var = {
    xtype: 'list',
    id: 'contacts_discover_var',
    tite: 'title',
    style: settings_sz2,
    store: list_contactAccounts_obj,
    data: contactsWithAccounts,
    scrollable: {
        direction: 'vertical'
    },
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    pressedCls: '',
    selectedCls: '',
    itemTpl: contactAccounts_listTpl,
    listeners: {
       itemtap: {
            fn:  function(_element, index, _target, _record, event, _eventOptions) {
             if (event.target.className !== "follow_button_target") {
                setMask('Loading...');
                var id = contactsWithAccounts[index].user_id;
                currentUserId = id;
                get_profile(id, Ext.Viewport.getActiveItem().id);
              }

            }
        },

    }
};

//define Contact view  - used to follow contacts from phone that already have an account
Ext.define('myVidster.view.Contacts', {
    extend: 'Ext.Panel',
    id: 'Contacts',
    alias: 'widget.Contacts',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [userContactsToolbar,
        contacts_tabbar,
        contacts_var,
        createBottomTabBar('user_tabbar_contacts'),
        ]
    }
});

// Empty Contacts
// set contacts list content here --
var empty_contacts_tabbar = {
    xtype: 'tabbar',
    id: 'empty_contacts_tabbar',
    style:"background-color: #1C2F45",
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons04
};

var empty_contacts_var = {
    xtype: 'list',
    id: 'empty_contacts_discover_var',
    tite: 'title',
    style: settings_sz2,
    data: contactsWithAccounts,
    width: '100%',
    flex: 1,
    pressedCls: '',
    selectedCls: '',
    itemTpl: emptyContacts_listTpl,
    listeners: { //when you tap a list object, the itemtap function fires
    }
};

//define Contact view  - used to follow contacts from phone that already have an account
Ext.define('myVidster.view.EmptyContacts', {
    extend: 'Ext.Panel',
    id: 'EmptyContacts',
    alias: 'widget.EmptyContacts',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [userContactsToolbar_empty_contacts,
        empty_contacts_tabbar,
        empty_contacts_var,
        createBottomTabBar('user_tabbar_empty_contacts')
        ]
    }
});

// Empty Recommendations
// set contacts list content here --

var empty_recommendations_tabbar = {
    xtype: 'tabbar',
    id: 'empty_recommendations_tabbar',
    style:"background-color: #1C2F45",
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons05
};

var empty_recommendations_var = {
    xtype: 'list',
    id: 'empty_recommendations_discover_var',
    tite: 'title',
    style: settings_sz2,
    data: contactsWithAccounts,
    width: '100%',
    flex: 1,
    pressedCls: '',
    selectedCls: '',
    itemTpl: emptyRecommendations_listTpl,
    listeners: { //when you tap a list object, the itemtap function fires
    }
};

//define Contact view  - used to follow contacts from phone that already have an account
Ext.define('myVidster.view.EmptyRecommendations', {
    extend: 'Ext.Panel',
    id: 'EmptyRecommendations',
    alias: 'widget.EmptyRecommendations',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [userContactsToolbar_empty_recommendations,
          empty_recommendations_tabbar,
          empty_recommendations_var,
          createBottomTabBar('user_tabbar_empty_recommendations')
        ]
    }
});

// Tutorial

var userTutorialToolbar = {
    xtype: 'toolbar',
    id: 'userTutorialToolbar',
    title: 'Tutorial',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons
};

var tutorial_tabbar = {
    xtype: 'tabbar',
    id: 'tutorial_tabbar',
    style: dark_tabbar_sz,
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons
};

var tutorial_recommendations_var = {
    xtype: 'list',
    id: 'tutorial_recommendations_var',
    tite: 'title',
    style: settings_sz2,
    // store: recommendations_obj,
    scrollable: {
        direction: 'vertical'
    },
    // plugins: [{
    //     xclass: 'Ext.plugin.PullRefresh',
    //     pullText: 'Pull to refresh!',
    //     loadingText: 'Refreshing...',
    //     autoSnapBack: true,
    //     scrollerAutoRefresh: true,
    // }],
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    itemTpl: tutorial_recommendations_listTpl,
    listeners: {
        itemtap: {
            fn: function(_view, _index, _target, record, _event) {
                var user_id = record.get('user_id');
                list_videos('User', user_id, 0);
                setTimeout(function() {
                    Ext.getCmp('tutorial_recommendations_var').deselectAll();
                }, 500);
            }
        }
    }
};

// for tutorial
var tutSkipButton = '<div><button style="position: absolute; padding:0px !important; text-align:center; width:80%; height:35px; color:white;background-color:#00A300; font-size: 18px; margin-top: 125%; margin-left: 10%;border-radius:12px" onclick="skipTutorial()" >Skip</button><img style="width: 100%" src="https://m.myvidster.com/resources/images/tutorialstart.png" /></div>';

var tutCompleteButton = '<div><button style="position: absolute; padding:0px !important; text-align:center; border-radius:12px; width:80%; height:35px; color:white;background-color:#00A300; font-size: 18px; margin-top: 125%; margin-left: 10%" onclick="gotoUserScreen()" >Complete</button><img style="width: 100%" src="https://m.myvidster.com/resources/images/discovertutorial.png" /></div>';

if (glb_device_type === 'tablet') {
    tutSkipButton = '<div><button style="position: absolute; padding:0px; bottom: 50px !important; text-align:center; width:80%; height:35px; color:white;background-color:#00A300; font-size: 18px; margin-top: 125%; margin-left: 10%; margin-bottom:100px;border-radius:12px" onclick="skipTutorial()" >Skip</button><img style="width: 100%" src="https://m.myvidster.com/resources/images/tutorialstart.png" /></div>';

    tutCompleteButton  = '<div><button style="position: absolute; padding:0px; bottom: 50px !important; text-align:center; border-radius:12px; width:80%; height:35px; color:white;background-color:#00A300; font-size: 18px; margin-top: 125%; margin-bottom:100px; margin-left: 10%" onclick="gotoUserScreen()" >Complete</button><img style="width: 100%" src="https://m.myvidster.com/resources/images/discovertutorial.png" /></div>';
}


//production versions

var tutScreens = [
  'Collect01', //0
  'Collect02', //1
  'Collect03', //2
  'Collect04', //3
  'Collect05', //4
  'Collect06',  //5
  'Collect07', //6
  'Collect08', //7
  'Collect09', //8
  'Collect10', //9
  'Collect11', //10
  'Share01', //11
  'Share02', //12
  'Share03', //13
  'Share04', //14
  'Share05', //15
  'Explore01', //16
  'Explore02', //17
  'Explore03', //18
  'Explore04', //19
  'Explore05', //20
];

var tutScreenCount = 0;


var imageTutExists = tutScreens.map(function() {
 return false
});

checkAllTutScreens(100);

// checks all tutorial screens
function checkAllTutScreens() {
  tutScreens.map(function(screen, index) {
    checkTutScreen(imageLocalPrefix, screen, index, 20);
  });
}

// checks if tutorial image exits locally
function checkTutScreen(localPath, imageName, index, _count) {
  var checkImage = new Image();

  if (glb_device_type === 'tablet') {
    checkImage.src = localPath + imageName + '_tablet.png' ;
  } else{
    if (isIphoneX) {
      checkImage.src = localPath + imageName + '_iPhoneX.png';
    } else {
      checkImage.src = localPath + imageName + '.png';
    }
  }

   checkImage.onload = function() {

    imageTutExists[index] = true;
    tutScreenCount++;

  }

  checkImage.onerror = function() {
    imageTutExists[index] = false;
    tutScreenCount++;

  }
}


function getTutScreen(image, index, _count) {
  var tutHtml;
  var sourceImage = '';
  // checks if image is stored locally. if not, uses remote source link
  if (glb_device_type === 'tablet') {
        sourceImage = imageTutExists[index] ?  './resources/images/' + image +'_tablet.png' :
        'https://m.myvidster.com/resources/images/' + image + '_tablet.png' +
        cachebusterParameters;
  } else {
        if (isIphoneX) { // load iPhoneX pngs
          sourceImage = imageTutExists[index] ?  './resources/images/' + image +'_iPhoneX.png' :
          'https://m.myvidster.com/resources/images/' + image +'_iPhoneX.png' +
          cachebusterParameters;
        } else {
          sourceImage = imageTutExists[index] ?  './resources/images/' + image +'.png' :
          'https://m.myvidster.com/resources/images/' + image +'.png' +
          cachebusterParameters;     
  
        }
  }

   if (glb_device_type === 'tablet') { // tablet vertical
      tutHtml = '<div><button style="position: absolute; padding:0px !important;'+
      ' text-align:center; ' +
    'width:34px; height:34px; color:white;background-color:#00A300; font-weight: bold; '+
    'font-size: 20px;' +
    ' margin-top: 4%; margin-left: 90%;border-radius:8px;" onclick="skipTutorial()" >X'+
    '</button>' +
    '<img style="width: 100%" src="' + sourceImage + '" /></div>';
   }   else { // phone vertical
    tutHtml = '<div><button style="position: absolute; padding:0px !important; text-align:center;' +
    ' width:45px; height:45px; color:white;background-color:#00A300; font-weight: bold; ' +
    ' font-size: 25px; margin-top: 9%; margin-left: 84%;border-radius:8px;" onclick="skipTutorial()" >'+
    'X</button><img style="width:100%;"' +
    'src="' + sourceImage + '" /></div>';
    }

    if (Ext.Viewport) {
      if (Ext.Viewport.determineOrientation() === 'landscape') {
          var height = Ext.Viewport.getWindowHeight();
          var width = Ext.Viewport.getWindowWidth();

          var translateValue = '-39%';
          var marginTop = '47%';
          var marginLeft = '12%';
          var marginTopTablet = '70%';
          var marginLeftTablet = '18%';
          var translateTopTablet = '-16.75%';
          var translateLeftTablet = '-12.5%';

          if (isIphoneX) {
            translateValue = '-52%';
            marginTop = '42%';
            marginLeft = '17%';
            width = Ext.Viewport.getWindowWidth() * 0.95;

            if (typeof window.indexedDB != 'undefined') { //used to determine if using WKWebView
              adjustLandscapeForIPhoneX();
              width = 771.4;
              translateValue = '-58%';
              height = 356.25;

            }

          } else if (typeof window.indexedDB != 'undefined') { 
            if (glb_device_type == 'tablet') {
              height = heightLandscape;
              width = widthLandscape;
              translateValue = '-44%';
            }
          }

          if (Ext.os.is('Android') && height <= 360) {
            translateValue = '-45%';
            marginTop = '43%';
          }

         if (glb_device_type === 'tablet') {  // tablet horizontal
          tutHtml ='<div>' +
          '<img style="transform: rotate(90deg) translate('+ translateTopTablet + ',' + translateLeftTablet +')"' +
          ' width=' + height + ' height=' + width +
          ' src="' + sourceImage + '" /> ' +
          '<button style="position: absolute; padding:0px !important; text-align:center; ' +
          'width:34px; height:34px; color:white;background-color:#00A300; font-weight: bold; '+
          'font-size: 20px; ' +
          'transform: rotate(90deg); ' +
          ' margin-top: '+ marginTopTablet +'; margin-left: '+marginLeftTablet+';border-radius:8px; " onclick="skipTutorial()" >X</button>' +
          '</div>';
         } else { // phone horizontal
           tutHtml = '<div style="text-align:center"><img style="transform: rotate(90deg) '+
           'translate('+ translateValue +',0%)"' +
          ' width=' + height + ' height=' + width + ' src="' +  sourceImage + '" />' +
          '<button style="position: absolute; padding:0px !important; text-align:center;' +
          ' width:45px; height:45px; color:white;background-color:#00A300; font-weight: bold; ' +
          'transform: rotate(90deg);' +
          ' font-size: 25px; margin-top: '+ marginTop +'; margin-left: '+ marginLeft +';border-radius:8px;" onclick="skipTutorial()" >'+
          'X</button>' +
          '</div>';
         }
      }
    }
    return tutHtml;
  }

var tutorialCarousel = {
      xtype: 'carousel',
      defaults: {
          layout: 'hbox'
      },
      id:'tutorialCarousel',
      activeItem: 0,
      flex: 1,
      items:[],
};


function setTutorialScreens(count) {
if (glb_device_type === 'tablet') {
  if (Ext.os.is('iOS')) {
    tutorialCarousel = {
      xtype: 'carousel',
      defaults: {
          layout: 'hbox'
      },
      id:'tutorialCarousel',
      activeItem: 0,
      flex: 1,
      items:[{
          html: getTutScreen('Collect01', 0, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect02', 1, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect03', 2, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect04', 3, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect05', 4, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect06', 5, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect07', 6, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect08', 7, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect09', 8, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect10', 9, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect11', 10, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share01', 11, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share02', 12, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share03', 13, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share05', 15, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore01', 16, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore02', 17, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore03', 18, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore04', 19, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore05', 20, count) || '',
          cls: 'card'
      }]
    };
  } else {
     tutorialCarousel = {
      xtype: 'carousel',
      defaults: {
          layout: 'hbox'
      },
      id:'tutorialCarousel',
      activeItem: 0,
      flex: 1,
      items:[{
          html: getTutScreen('Collect01', 0, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect02', 1, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect03', 2, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect04', 3, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect05', 4, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect06', 5, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share01', 11, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share02', 12, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share03', 13, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share05', 15, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore01', 16, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore02', 17, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore03', 18, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore04', 19, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore05', 20, count) || '',
          cls: 'card'
      }]
    };
  }
} else {

  if (Ext.os.is('iOS')) {
    tutorialCarousel = {
      xtype: 'carousel',
      defaults: {
          layout: 'hbox'
      },
      id:'tutorialCarousel',
      activeItem: 0,
      flex: 1,
      items:[{
          html: getTutScreen('Collect01', 0, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect02', 1, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect03', 2, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect04', 3, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect05', 4, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect06', 5, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect07', 6, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect08', 7, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect09', 8, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect10', 9, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share01', 11, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share02', 12, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share03', 13, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share04', 14, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share05', 15, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore01', 16, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore02', 17, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore03', 18, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore04', 19, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore05', 20, count) || '',
          cls: 'card'
      }]
    };

  } else {
    tutorialCarousel = {
      xtype: 'carousel',
      defaults: {
          layout: 'hbox'
      },
      id:'tutorialCarousel',
      activeItem: 0,
      flex: 1,
      items:[{
          html: getTutScreen('Collect01', 0, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect02', 1, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect03', 2, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect04', 3, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect05', 4, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Collect06', 5, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share01', 11, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share02', 12, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share03', 13, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share04', 14, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Share05', 15, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore01', 16, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore02', 17, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore03', 18, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore04', 19, count) || '',
          cls: 'card'
      },{
          html: getTutScreen('Explore05', 20, count) || '',
          cls: 'card'
      }]
    };
  }
}
}


Ext.define('myVidster.view.Tutorial', {
    extend: 'Ext.Panel',
    id: 'Tutorial',
    alias: 'widget.Tutorial',
    config: {
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        items: [ tutorialCarousel ]
    },
});

////////////////////////////////////////////////////////////////

// Onboarding

var userOnboardingToolbar = {
    xtype: 'toolbar',
    id: 'userOnboardingToolbar',
    title: 'Onboarding',
    style: settings_sz2,
    ui: 'light',
    docked: 'top',
    items: gen_top_buttons
};

var onboarding_tabbar = {
    xtype: 'tabbar',
    id: 'onboarding_tabbar',
    style: dark_tabbar_sz,
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons
};

var onboarding_recommendations_var = {
    xtype: 'list',
    id: 'onboarding_recommendations_var',
    tite: 'title',
    style: settings_sz2,
    // store: recommendations_obj,
    scrollable: {
        direction: 'vertical'
    },
   
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    itemTpl: tutorial_recommendations_listTpl,
    listeners: {
        itemtap: {
            fn: function(_view, _index, _target, record, _event) {
                var user_id = record.get('user_id');
                list_videos('User', user_id, 0);
                setTimeout(function() {
                    Ext.getCmp('onboarding_recommendations_var').deselectAll();
                }, 500);
            }
        }
    }
};


////////////////////////////////////////////////////////////////

// User Profile

var userProfileToolbar = {
    xtype: 'toolbar',
    id: 'userProfileToolbar',
    title: 'Account',
    style: user_profile_settings,
    ui: 'light',
    docked: 'top',
    items: account_top_buttons
};
var userprofile_tabbar = {
    xtype: 'tabbar',
    id: 'userprofile_tabbar',
    style: dark_tabbar_sz,
    ui: 'light',
    docked: 'top',
    items: all_discover_buttons
};


var relationshipOptions =  [
                {text: 'Single', value: 1 },
                {text: 'Taken', value: 2 },
                {text: 'Open Relationship', value: 3 },
                {text: 'Do Not Show', value: 4 },
            ];

var profileForm = {
    items: [
        {
            xtype: 'textfield',
            name : 'email',
            label: 'Email',
            labelWidth: '35%',
            value: userEmail,
            disabled: true,
            email: (''),
        },
        {
            xtype: 'textfield',
            name : 'disp_name',
            id:'disp_name',
            label: 'Display Name',
            labelWidth: '35%',
            dataIndex: 'disp_name',
            value: tempName,
            disabled: false,
            //disabled: userProMem,
            listeners: {
              change: {
                  fn: function() {
                      //userSettings.disp_name = Ext.getCmp('disp_name').getValue();
                  }
              }
            }
        },
        {
            xtype: 'urlfield',
            name : 'website',
            id:'website',
            label: 'Website',
            labelWidth: '35%',
            value: userUrl,
            url: (''),
            listeners: {
              change: {
                  fn: function() {
                      userSettings.website = Ext.getCmp('website').getValue();
                  }
              }
            }
        },
        {
            xtype: 'textfield',
            name : 'location',
            id:'location',
            label: 'Location',
            labelWidth: '35%',
            value: userLocation,
            listeners: {
              change: {
                  fn: function() {
                      userSettings.location = Ext.getCmp('location').getValue();
                  }
              }
            }
        },
        {
            xtype: 'selectfield',
            name : 'collections',
            label: 'Default Collection',
            id: 'collections',
            usePicker: true,
            labelWidth: '35%',
            labelWrap:true,
            options: [
                {text: collectionName, value: collectionSelected},
            ],
            listeners: {
              change: {
                  fn: function() {
                      userSettings.collections.map(function (coll) {
                        coll.selected = 0;
                      });
                      userSettings.collections[Ext.getCmp('collections').getValue()].selected = 1;
                  }
              }
            }
        },
        {
            xtype: 'selectfield',
            name : 'relationshipStatus',
            label: 'Relationship Status',
            id: 'relationshipStatus',
            labelWidth: '35%',
            usePicker: true,
            labelWrap:true,
            options: relationshipOptions,
            listeners: {
              change: {
                  fn: function() {
                    var currentValue = Ext.getCmp('relationshipStatus').getValue();

                    if (currentValue === 1) {
                      userSettings.relationship_status = 'single';
                    } else if (currentValue === 2) {
                      userSettings.relationship_status = 'taken';
                    } else if (currentValue === 3) {
                      userSettings.relationship_status = 'open_relationship';
                    } else if (currentValue === 4) {
                      userSettings.relationship_status = '';
                    }
                  }
              }
            }
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Gender',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'userGender',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'userGender',
                label: 'Female',
                //value: 'female',
                listeners: {
                    check: {
                        fn: function() {
                            userGender = 'female';
                            account.set("userGender", userGender);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().userGender = userGender;
                            list_videos_obj.getProxy().getExtraParams().userGender = userGender;
                            search_obj.getProxy().getExtraParams().userGender = userGender;
                            home_obj.load();

                            userSettings.gender = 2;
                        }
                    }
                }
            }, {
                name: 'userGender',
                label: 'Male',
                //value: 'male',
                listeners: {
                    check: {
                        fn: function() {
                            userGender = 'male';
                            account.set("userGender", userGender);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().userGender = userGender;
                            list_videos_obj.getProxy().getExtraParams().userGender = userGender;
                            search_obj.getProxy().getExtraParams().userGender = userGender;
                            home_obj.load();
                            userSettings.gender = 1;
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Sexual Orientation',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'sexual_orientation',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'sexualOrientation',
                label: 'Straight',
                value: 'straight',
                checked: true,
                listeners: {
                    check: {
                        fn: function() {
                            userGender = 'straight';
                            account.set("sexualOrientation", sexualOrientation);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            list_videos_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            search_obj.getProxy().getExtraParams().sexualOrientation= sexualOrientation;
                            home_obj.load();
                            userSettings.sexual_orientation = 1;
                        }
                    }
                }
            }, {
                name: 'sexualOrientation',
                label: 'Gay',
                value: 'gay',
                listeners: {
                    check: {
                        fn: function() {
                            sexualOrientation = 'gay';
                            account.set("sexualOrientation", sexualOrientation);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            list_videos_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            search_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            home_obj.load();
                            userSettings.sexual_orientation = 2;
                        }
                    }
                }
            }, {
                name: 'sexualOrientation',
                label: 'Bi',
                value: 'bi',
                listeners: {
                    check: {
                        fn: function() {
                            sexualOrientation = 'bi';
                            account.set("sexualOrientation", sexualOrientation);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            list_videos_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            search_obj.getProxy().getExtraParams().sexualOrientation = sexualOrientation;
                            home_obj.load();
                            userSettings.sexual_orientation = 3;
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Email Subscription',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'enable_disable_email_subscription',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'enable_disable_email_subscription',
                label: 'Enabled',
                value: 'enabled',
                listeners: {
                    check: {
                        fn: function() {
                            enable_disable_email_subscription = 1;
                            account.set("enable_disable_email_subscription", enable_disable_email_subscription);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().enable_disable_email_subscription = enable_disable_email_subscription;
                            list_videos_obj.getProxy().getExtraParams().enable_disable_email_subscription = enable_disable_email_subscription;
                            search_obj.getProxy().getExtraParams().enable_disable_email_subscription = enable_disable_email_subscription;
                            home_obj.load();
                            userSettings.email_subscription = 1;
                        }
                    }
                }
            }, {
                name: 'enable_disable_email_subscription',
                label: 'Disabled',
                value: 'disabled',
                listeners: {
                    check: {
                        fn: function() {
                            enable_disable_email_subscription = 2;
                            account.set("enable_disable_email_subscription", enable_disable_email_subscription);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().enable_disable_email_subscription = enable_disable_email_subscription;
                            list_videos_obj.getProxy().getExtraParams().enable_disable_email_subscription = enable_disable_email_subscription;
                            search_obj.getProxy().getExtraParams().enable_disable_email_subscription = enable_disable_email_subscription;
                            home_obj.load();
                            userSettings.email_subscription = 0;
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Default Access',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'enable_disable_default_access',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'enable_disable_default_access',
                label: 'Public',
                value: 'public',
                listeners: {
                    check: {
                        fn: function() {
                            enable_disable_default_access = 0;

                            glb_defaultAccess = 0;
                            application.set("defaultAccess", enable_disable_default_access);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();

                            account.set("enable_disable_default_access", enable_disable_default_access);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            list_videos_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            search_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            home_obj.load();
                            userSettings.default_access = 'public';
                        }
                    }
                }
            }, {
                name: 'enable_disable_default_access',
                label: 'Private',
                value: 'private',
                listeners: {
                    check: {
                        fn: function() {
                            enable_disable_default_access = 1;

                            glb_defaultAccess = 1;
                            application.set("defaultAccess", enable_disable_default_access);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();

                            account.set("enable_disable_default_access", enable_disable_default_access);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            list_videos_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            search_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            home_obj.load();
                            userSettings.default_access = 'private';
                          
                        }
                    }
                }
            }, {
                name: 'enable_disable_default_access',
                label: 'Adult',
                value: 'adult',
                listeners: {
                    check: {
                        fn: function() {
                            enable_disable_default_access = 2;

                            glb_defaultAccess = 2;
                            application.set("defaultAccess", enable_disable_default_access);
                            application.setDirty();
                            app_store.sync();
                            application = app_store.first();

                            account.set("enable_disable_default_access", enable_disable_default_access);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            list_videos_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            search_obj.getProxy().getExtraParams().enable_disable_default_access = enable_disable_default_access;
                            home_obj.load();
                            userSettings.default_access = 'adult';
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Visibility',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'userVisibility',
            defaults: {
                xtype: 'radiofield',
                labelWidth: '35%'
            },
            items: [{
                name: 'userVisibility',
                label: 'Everyone',
                value: 'everyone',
                listeners: {
                    check: {
                        fn: function() {
                            userVisibility = "Everyone";
                            account.set("userVisibility", userVisibility);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().userVisibility = userVisibility;
                            list_videos_obj.getProxy().getExtraParams().userVisibility = userVisibility;
                            search_obj.getProxy().getExtraParams().userVisibility = userVisibility;
                            home_obj.load();
                            userSettings.visibility = 'everyone';
                        }
                    }
                }
            }, {
                name: 'userVisibility',
                label: 'Logged In',
                value: 'logged_in',
                listeners: {
                    check: {
                        fn: function() {
                            userVisibility = "logged_in";
                            account.set("userVisibility", userVisibility);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().userVisibility = userVisibility;
                            list_videos_obj.getProxy().getExtraParams().userVisibility = userVisibility;
                            search_obj.getProxy().getExtraParams().userVisibility = userVisibility;
                            home_obj.load();
                            userSettings.visibility = 'login_users';
                        }
                    }
                }
            }]
        },
        {
            xtype: 'fieldset',
            pack: 'center',
            title: 'Email Notifications (check to disable)',
            style: settings_sz,
            hidden: false,
            margin: '0',
            id: 'emailNotifications',
            defaults: {
                xtype: 'checkboxfield',
                labelWidth: '35%'
            },
            items: [{
                name: 'emailNotifications',
                label: 'New Connection',
                value: 'newConnection',
                listeners: {
                    check: {
                        fn: function() {
                            emailNotifications = "newConnection";
                            account.set("emailNotifications", emailNotifications);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().emailNotifications = emailNotifications;
                            list_videos_obj.getProxy().getExtraParams().emailNotifications = emailNotifications;
                            search_obj.getProxy().getExtraParams().emailNotifications = emailNotifications;
                            home_obj.load();

                            userSettings.email_notification.email_conn = 1;
                        }
                    },
                    uncheck: {
                        fn: function() {
                            userSettings.email_notification.email_conn = 0;
                        }
                    }
                }
            }, {
                name: 'emailNotifications',
                label: 'Video Sharing',
                value: 'videoSharing',
                listeners: {
                    check: {
                        fn: function() {
                            emailNotifications = "videoSharing";
                            account.set("emailNotifications", emailNotifications);
                            account.setDirty();
                            store.sync();
                            account = store.first();
                            home_obj.getProxy().getExtraParams().emailNotifications = emailNotifications;
                            list_videos_obj.getProxy().getExtraParams().emailNotifications = emailNotifications;
                            search_obj.getProxy().getExtraParams().emailNotifications = emailNotifications;
                            home_obj.load();
                            userSettings.email_notification.email_video = 1;
                        }
                    },
                    uncheck: {
                        fn: function() {
                            userSettings.email_notification.email_video  = 0;
                        }
                    }
                }
            }]
        },
        {
            xtype: 'button',
            ui: 'plain',
            style: 'background-color: green; width: 90%; color: white; margin-left: 5%; margin-bottom: 15px; margin-top: 20px',
            id: 'save',
            text: 'Save Changes',
            handler: function() {
                packAndSendUserSettings(userSettings);
                setMask('Saving...');
            },
        },


        {
            xtype: 'button',
            style: 'width: 90%; margin-left: 5%; margin-bottom: 15px;',
            id: 'cancel',
            text: 'Cancel',
            handler: function(){
                clearCountdown();
                onBackKeyDown(); 
            }
        },
        {
            xtype: 'button',
            ui: 'plain',
            style: 'background-color: #B22222; width: 90%; color: white; margin-left: 5%; margin-bottom: 15px; margin-top: 40px',
            id: 'delete_account_btn',
            text: 'Delete Account',
            handler: function() {
                var r = confirm("Delete Account?");
                if (r == true) {
                    setMask('Requesting...');
                    Ext.util.JSONP.request({
                    url: 'https://api.myvidster.com/mobile_json2.php',
                    callbackKey: 'callback',
                    params: {
                        type: 'delete_account',
                        user_id: account.get('user_id'),
                        pkey: account.get('pw'),
                        uniquify: Math.random(),
                        token: glb_msg_token
                    },
                    callback: function(success, result) {
                            if (result.items[0].response == "success") {
                                clearMask();
                                myvAlert('Success.');
                                logout();
                                window.location.reload(); // restart to clear and unbind pusher and notifications etc
                            }
                            else {
                            	myvAlert(result.items[0].response);
                            	clearMask();
                            }
                    }
                });
                } 
            },
        },

    ],
};


function updateStatusOptions() {
  
}

Ext.define('myVidster.view.UserProfile', {
    extend: 'Ext.Panel',
    id: 'UserProfile',
    fields: [
        {name: 'disp_name', type: 'string'}
    ],
    alias: 'widget.UserProfile',
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'https://api.myvidster.com/mobile_json2.php',
        reader: {
            type: 'json',
            root: 'profileView',
            successProperty: false
        }
    },
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
    },
});


var myMsgList = {
    xtype: 'panel',
    scrollDock: 'top',
    docked: 'top',
    id: 'myMsgList',
    items: [{
        html: ""
    }, ]
};

// template for list of chats with friends
var chat_tpl = '';
if (glb_device_type == 'tablet') {
    chat_tpl = '<div> <div style="float:left;font-weight:bold;font-size:2.2em;width:15px;"> <tpl if="read==\'0\'"> &bull; <tpl else> &nbsp; </tpl> </div> <img src="{profile_photo}" style="border-radius: 50%;width:65px;height:65px;margin-right:15px; float:left;" class="dark_mode_msg_pic"/><table style="float:left;width:calc(100% - 100px);padding:0px;margin:0px;" border=0> <tr> <td rowspan=3 style="width:0px;padding:0px;">  </td> <td> <span style="font-weight:<tpl if="read==\'0\'">bold</tpl>;">{user_name}</span> </td> <td> <p style="float:right;font-size:.7em;margin-bottom:0px;color:<tpl if="read==\'0\'">black</tpl><tpl if="read==\'1\'">grey</tpl>;" class="dark_mode_msg_time">{time}</p> </td> </tr> <tr> <td rowspan=2 colspan=2> <p style="width:210px;margin-top:4px;padding:0px;font-size:.88em; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;pointer-events:none;font-weight: <tpl if="read==\'0\'">bold</tpl>;color:<tpl if="read==\'0\'">black</tpl><tpl if="read==\'1\'">grey</tpl>;" class="dark_mode_msg"> <tpl if="read==\'0\'"> <span class="dark_mode_msg2"> {message} </span> <tpl else> {message} </tpl> </p> </td> </tr> <tr> </tr> </table>  <div style="clear:both;"></div> </div>';
} else {
    chat_tpl = '<div> <div style="float:left;font-weight:bold;font-size:2.2em;width:15px;"> <tpl if="read==\'0\'"> &bull; <tpl else> &nbsp; </tpl> </div> <img src="{profile_photo}" style="border-radius: 50%;width:50px;height:50px;margin-right:10px; float:left;" class="dark_mode_msg_pic"/><table style="float:left;width:calc(100% - 80px);padding:0px;margin:0px;" border=0> <tr> <td rowspan=3 style="width:0px;padding:0px;">  </td> <td> <span style="font-weight:<tpl if="read==\'0\'">bold</tpl>;">{user_name}</span> </td> <td> <p style="float:right;font-size:.65em;margin-bottom:0px;color:<tpl if="read==\'0\'">black</tpl><tpl if="read==\'1\'">grey</tpl>;" class="dark_mode_msg_time">{time}</p> </td> </tr> <tr> <td rowspan=2 colspan=2> <p style="width:210px;margin-top:4px;padding:0px;font-size:.85em; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block;pointer-events:none;font-weight: <tpl if="read==\'0\'">bold</tpl>;color:<tpl if="read==\'0\'">black</tpl><tpl if="read==\'1\'">grey</tpl>;" class="dark_mode_msg"><tpl if="read==\'0\'"> <span class="dark_mode_msg2"> {message} </span> <tpl else> {message} </tpl> </p> </td> </tr> <tr> </tr> </table>  <div style="clear:both;"></div> </div>';
}

var list_msgs_var = {
    xtype: 'list',
    id: 'list_msgs_var',
    tite: 'title',
    style: settings_sz2,
    store: list_msgs_obj,
    cls: 'myv_list_chat1',
    itemTpl: chat_tpl,

    scrollable: {
        direction: 'vertical',
        directionLock: true,
        scroller: {
            listeners: {
                scroll: function(_scroller, _x, _y, _eOpts) {},
                scrollend: function(_scroller, _x, _y, _eOpts) {}
            }
        }
    },
    plugins: [

        {
            xclass: 'Ext.plugin.ListPaging',
            id: 'list_msgs_var_p',
            loadMoreText: '',
            autoPaging: true,
            noMoreRecordsText: '',
        }, {
            xclass: 'Ext.plugin.PullRefresh',
            pullText: 'Pull to refresh!',
            loadingText: 'Refreshing...',
            autoSnapBack: true,
            scrollerAutoRefresh: true,

        },

        {


            xclass: 'PLabs.plugin.SlideActions',
            buttons: [

                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Delete</div>",
                    ui: 'decline',
                    listeners: {
                        tap: function(button, e) {
                            setMask('Loading...');

                            var to = button.getRecord().get('to_u_id');
                            var last_id = button.getRecord().get('last_id');
                            var rowIndex = list_msgs_obj.indexOf(button.getRecord());
                            delete_chat_hist(to, last_id, '0', rowIndex);
                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                },
                {
                    xtype: 'button',
                    html: "<div style='text-align:center;margin:0px auto;'>Block</div>",
                    ui: 'actioin',
                    listeners: {
                        tap: function(button, e) {
                            var to = button.getRecord().get('to_u_id');
                            var last_id = button.getRecord().get('last_id');
                            var rowIndex = list_msgs_obj.indexOf(button.getRecord());

                            block_msg(to, last_id, '1', rowIndex);

                            e.stopPropagation();
                            //return false;
                        },
                        scope: this
                    }
                }
            ]
        }
    ],
    width: '100%',
    flex: 1,
    cls: 'myv_list',
    listeners: {

        itemtap: { // loads chat messages with individual user when user taps on chat
            fn: function(_view, _index, _target, record, event) {

                setMask('Loading Chat');
                read_msg(account.get('user_id'), account.get('pw'), record.get('to_u_id'), 1);
                var type = glb_current_list['type'];
                var prof = record.get('profile_photo');
                var uid5 = record.get('to_u_id');
                var uid_username = record.get('user_name');
                var pressed = window.innerWidth - event.pageX;
                //
                glb_last_panel = Ext.Viewport.getActiveItem().id;
                glb_current_messaging = uid5;

                list_detailed_msgs_obj.removeAll();
                Ext.getCmp('userListToolbarMessage').setTitle("");
                var myString = uid_username;
                 // limits length of chat user name at top to 10 chars
                var myTruncatedString = myString.substring(0, 9);
                Ext.getCmp('userListToolbarMessage').setTitle(myTruncatedString);

                var dt = new Date();
                var offset_time = dt.getTimezoneOffset();

                list_detailed_msgs_obj.getProxy().setExtraParams({
                    type: 'detailed_messages',
                    user_to: uid5,
                    user_id: account.get('user_id'),
                    pkey: account.get('pw'),
                    token: glb_msg_token,
                    start: 0,
                    id: Math.random(),
                    offset: offset_time
                }); 

       

                // loads chat messages here
                list_detailed_msgs_obj.load();

                wait_chat(uid5, 100, myTruncatedString);
                ////////



            }
        }
    }
};


Ext.define('myVidster.view.MsgList', {
    extend: 'Ext.Container',
    id: 'MsgList',
    alias: 'widget.MsgList',
    config: {
        cls: 'myv_list',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },

        fullscreen: true,
        listeners: {
            initialize: function(_panel, _eOpts) {

            }
        },
        autoScroll: true,
        items: [
            userListToolbarMessages,
            // msg_advice,
            list_msgs_var,
            createBottomTabBar('home_tabbar_MsgList')
        ]
    }
});

var search_bar_userlist = {
    xtype: 'toolbar',
    id: 'search_bar_userlistholder',
    items: [{
        xtype: 'searchfield',
        id: 'search_bar_userlist',
        flex: ifix_search_bar,
        //height: search_ht2,
        listeners: {
            action: function() {
                'use strict';
                var q = Ext.getCmp('search_bar_userlist').getValue();
                
                video_search(q, 1, glb_back_data['search_type'], glb_back_data['search_id'], glb_back_data['list_name']);
                Ext.Viewport.animateActiveItem("Search", {
                 type: 'fade',
                });
                
                setMask('Searching ...');
                Ext.getCmp('search_bar_userlist').setValue('');


            }
        }
    },
    {
        xtype: 'button',
        text: 'Search',
        hidden: ipad_search,
        pressedCls: '',
        handler: function() {
            'use strict';
            var q = Ext.getCmp('search_bar_userlist').getValue();
           
            video_search(q, 1, glb_back_data['search_type'], glb_back_data['search_id'], glb_back_data['list_name']);
            Ext.Viewport.animateActiveItem("Search", {
               type: 'fade',
             });
            
            Ext.getCmp('search_bar_userlist').setValue('');

        }

    }]
};



Ext.define('myVidster.view.UserList', {
    extend: 'Ext.Container',
    id: 'UserList',
    alias: 'widget.UserList',
    config: {
        cls: 'myv_list',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },

        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(_e) {

                    },
                });
            }
        },
        autoScroll: true,
        items: [
            userListToolbar,
            search_bar_userlist,
            sub_carousel,
            //follower_advice,
            //queue_advice,
            list_videos_var,
            createBottomTabBar('home_tabbar_UserList')
        ]
    }
});



var myGeneral = Ext.create('Ext.Panel', {
    items: [{
        html: "loading"
    }]
});

Ext.define('myVidster.view.General', {
    extend: 'Ext.Panel',
    id: 'General',
    alias: 'widget.General',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(e) {
                        onSwipe(e.direction, 5);
                    }
                });
            }
        },
        items: [generalToolbar, myGeneral, createBottomTabBar('home_tabbar_General')]
    }
});

Ext.define('Override.util.PaintMonitor', {
    override: 'Ext.util.PaintMonitor',

    constructor: function(config) {
        return new Ext.util.paintmonitor.CssAnimation(config);
    }
});

Ext.define('Override.util.SizeMonitor', {
    override: 'Ext.util.SizeMonitor',

    constructor: function(config) {
        var namespace = Ext.util.sizemonitor;

        if (Ext.browser.is.Firefox) {
            return new namespace.OverflowChange(config);
        } else if (Ext.browser.is.WebKit || Ext.browser.is.IE11) {
            return new namespace.Scroll(config);
        } else {
            return new namespace.Default(config);
        }
    }
});

var myGeneralInvite = Ext.create('Ext.Panel', {
    items: [{
        html: "<div style='margin:0px;text-align:center;' id='invitef'>Loading...</div>"
    }]
});

//used for contact invite
var contact_list_var = {
    xtype: 'list',
    id: 'contact_list_var',
    tite: 'title',
    style: settings_sz2,
    store: list_contacts_obj,

    scrollable: {
        direction: 'vertical',
        directionLock: true,
        scroller: {
            listeners: {
                scroll: function(_scroller, _x, _y, _eOpts) {},
                scrollend: function(_scroller, _x, _y, _eOpts) {}
            }
        }
    },

    width: '100%',
    flex: 1,
    cls: 'myv_list',
    pressedCls: '',
    selectedCls: '',
    itemTpl: inviteContacts_listTpl,
    listeners: {
        
    }
};

Ext.define('myVidster.view.inviteF', {
    extend: 'Ext.Panel',
    id: 'inviteF',
    alias: 'widget.inviteF',

    config: {

        layout: {
            type: 'vbox',
            align: 'stretch'
        },

        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(_panel, _eOpts) {



            }
        },
        items: [
          inviteToolbar,
          search_bar_contact,
          contact_list_var,
          createBottomTabBar('home_tabbar_InviteF')
        ]
    }
});

Ext.define('myVidster.view.ProMember', {
    extend: 'Ext.Panel',
    id: 'ProMember',
    alias: 'widget.ProMember',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(_panel, _eOpts) {

            }
        },
        items: [userListToolbar2, createBottomTabBar('home_tabbar_ProMember')]
    }
});

Ext.define('myVidster.view.Resubscribe', {
    extend: 'Ext.Panel',
    id: 'Resubscribe',
    alias: 'widget.Resubscribe',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(_panel, _eOpts) {

            }
        },
        items: [userListToolbar4, createBottomTabBar('home_tabbar_Resubscribe')]
    }
});

Ext.define('myVidster.view.FeedBack', {
    extend: 'Ext.Panel',
    id: 'FeedBack',
    alias: 'widget.FeedBack',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(_e) {
                        //onSwipe(e.direction, 5);
                    }
                });
            }
        },
        items: [userListToolbar3, {
                xtype: 'formpanel',
                scrollable: null,
                items: [{
                        xtype: 'fieldset',
                        items: [{
                            xtype: 'emailfield',
                            name: 'email',
                            id: 'email_c',
                            label: 'Email'
                        }, {
                            xtype: 'textareafield',
                            name: 'Feedback',
                            id: 'text_c',
                            label: 'Feedback'
                        }, {
                            xtype: 'textfield',
                            name: 'ddata',
                            id: 'ddata_c',
                            hidden: true
                        }, {
                            xtype: 'textfield',
                            name: 'devd',
                            id: 'devd_c',
                            hidden: true
                        }]
                    }, {
                        layout: {
                            type: 'hbox',
                            pack: 'center'
                        },
                        items: [{
                            xtype: 'button',
                            width: '180px',
                            margin: '0',
                            scope: this,
                            id: 'sub_c',
                            hidden: false,
                            text: 'Submit Feedback',
                            handler: function() {
                                var subC = Ext.getCmp('sub_c').setHidden(true);
                                var email = Ext.getCmp('email_c').getValue();
                                var text = Ext.getCmp('text_c').getValue();
                                var ddata = Ext.getCmp('ddata_c').getValue();

                                Ext.util.JSONP.request({
                                    url: 'https://api.myvidster.com/mobile_json2.php',
                                    callbackKey: 'callback',
                                    params: {
                                        type: 'feedback_form',
                                        email: email,
                                        text: text,
                                        token: glb_msg_token,
                                        ddata: ddata
                                    },
                                    callback: function(_success, result) {
                                        if (result) {
                                            var response = result.items[0]['response'];
                                            myvAlert("Feedback Received!");
                                            Ext.getCmp('text_c').setValue("");
                                        } else {
                                            myvAlert("Error!");
                                        }
                                        var subC = Ext.getCmp('sub_c').setHidden(false);
                                    }
                                });
                            }
                        }]
                    },
                    devd2
                ]
            },
            createBottomTabBar('home_tabbar_feedback')
        ]
    }
});

var chatHist = {
    xtype: 'list',
    id: 'msglistscroll',
    store: list_detailed_msgs_obj,
    height: '400px',
    flex: 1,
    tite: 'title',
    style: settings_sz2,
    pressedCls: '',
    selectedCls: '',
    scrollToTopOnRefresh: false,
    cls: 'myv_chats',
      itemTpl: '<div id="{chat_id}">'+
    '<img class="dark_mode_msg_pic" src="{from_u_profile_photo}" style="border-radius: 50%;margin-top:4px;'+
    ' <tpl if="float_u== \'right\'"> float:right; width:' + chat_pic2 + '; height:' + chat_pic2 + 
    '; <tpl else> float:left; width:' + chat_pic1 + '; height:' + chat_pic1 + 
    '; </tpl>" onclick="list_videos(\'User\', {from_u_id}, 0)" ><div style="padding:10px 15px;'+
    ' margin:0px 4px 4px 4px; width:79%; border-radius:26px; min-height:' + chat_bbl + 
    '; <tpl if="float_u== \'right\'"> float:right; background-color: rgb(102, 163, 255); '+
    '<tpl else> float:left; background-color: rgb(245, 245, 245); </tpl> font-size:' 
    + chat_bbl_text + '; word-wrap:break-word;">'+ 
    '{message}'+
    '<tpl if="video_id && video_id !=0">' +
    '<br/> <img style="margin:20px" onclick="redirectToVideo({video_id})" src={video_thumbnail} width="60%" height="60%" >'+
    '</tpl>'  +
    '</div><div style="clear:both;"></div>'+
    ' <div style="line-height:10px;font-size:' + chat_bbl_time + 
    ';text-align:center;color:grey;padding-bottom:2px;">{time}</div></div>',
    scrollable: {
        direction: 'vertical',
        directionLock: true,
        scroller: {
            listeners: {
                scroll: function(_scroller, _x, y, _eOpts) {

                    if (y < -28) {

                        chat_scroll_check = 1;


                    }

                },
                scrollend: function(scroller, _x, _y, _eOpts) {

                    var str4 = Ext.getCmp('msglistscroll').getScrollable().getScroller();
                    var scroll_h1 = str4['_size']['y'];



                    if (chat_scroll_check == 1) {

                        setMask('Loading History');

                        var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();

                        Ext.getCmp('MsgUser').setDisabled(true);
                        Ext.getCmp('msglistscroll').setScrollable(false);
                        Ext.getCmp('MsgUser').setScrollable(false);



                        chat_scroll_check = 0;



                        Ext.util.JSONP.request({
                            url: 'https://api.myvidster.com/mobile_json2.php',
                            callbackKey: 'callback',
                            params: {
                                type: 'load_chat_history',
                                user_id: account.get('user_id'),
                                pkey: account.get('pw'),
                                uniquify: Math.random(),
                                token: glb_msg_token,
                                chat_id: glb_first_chat_id,
                                user_to: glb_current_messaging
                            },
                            callback: function(_success, result) {

                                if (result) {


                                    var id_tt = 0;
                                    for (var i in result.items) {



                                        if (typeof result.items[i].from_u_profile_photo != 'undefined' && typeof result.items[i].chat_id != null) {



                                            glb_first_chat_id = result.items[0].chat_id;

                                            list_detailed_msgs_obj.insert(i, {
                                                from_u_profile_photo: result.items[i].from_u_profile_photo,
                                                message: myv_url_share(result.items[i].message),
                                                to_u_profile_photo: result.items[i].to_u_profile_photo,
                                                from_u_id: result.items[i].from_u_id,
                                                to_u: result.items[i].to_u,
                                                chat_id: result.items[i].chat_id,
                                                float_u: result.items[i].float_u,
                                                time: result.items[i].time,
                                                video_id: result.items[i].video_id,
                                                video_thumbnail: result.items[i].video_thumbnail,
                                            });

                                            var id_c = result.items[i].chat_id;


                                            id_tt = parseInt(document.getElementById(id_c).offsetHeight) + parseInt(id_tt) + 12;
                                            scroller.scrollTo(0, parseInt(id_tt), false);

                                        }

                                    }


                                    setTimeout(function() {
                                        Ext.getCmp('msglistscroll').setScrollable(true);
                                        Ext.getCmp('MsgUser').setScrollable(true);
                                        Ext.getCmp('MsgUser').setDisabled(false);


                                        clearMask();

                                    }, 500);




                                } else {
                                    Ext.getCmp('msglistscroll').setScrollable(true);
                                    Ext.getCmp('MsgUser').setScrollable(true);
                                    Ext.getCmp('MsgUser').setDisabled(false);

                                    clearMask();
                                }



                            }
                        });


                    }

                }
            }
        }
    },
    plugins: [

    ],
    width: '100%'
};



Ext.define('myVidster.view.MsgUser', {
    extend: 'Ext.Panel',
    id: 'MsgUser',
    alias: 'widget.MsgUser',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },

        cls: 'myv_chats',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },

        fullscreen: true,
        listeners: {
            initialize: function(_panel, _eOpts) {

            },

        },

        items: [userListToolbarMessage,
            chatHist,
            createBottomTabBar('home_tabbar_MsgUser'), {
                xtype: 'formpanel',
                scrollable: null,
                docked: 'bottom',
                cls: 'myv_chat_doc',
                height: '64px',
                listeners: {
                    painted: function(_field, _e) {
                    },
                },


                items: [{
                    xtype: 'fieldset',
                    items: [{
                        xtype: 'textfield',
                        name: 'message',
                        clearIcon: false,
                        readOnly: true,
                        cls: 'myv_chats_padding_one',

                        label: '<div style="height:50px;padding:0px;margin-bottom:6px;">  <button id="sub_c2" style="padding:0px !important;float:right;text-align:center;width:15%;height:50px;font-size:' + chat_send_btn + ';color:green;background-color:#c4c4c4;">Send</button>  <textarea style="padding-left:2px;float:right;width:84.5%;height:50px !important;max-height:50px !important; font-size:' + chat_font_sz + ';resize:none;font-weight:normal !important;border-color:#c4c4c4;margin-right:.5%;" id="msg_c" placeholder="Type a message..."></textarea> <div style="clear:both;"></div> </div>',
                        labelWidth: '100%',

                        //placeHolder: 'Type a message',
                        height: '60px',
                        listeners: {
                            painted: function(_field, _e) {
                                var scroller = Ext.getCmp('msglistscroll').getScrollable().getScroller();
                                scroller.scrollToEnd(false);

                                if (Ext.os.is.Desktop) {
                                    document.getElementById("sub_c2").addEventListener("click", send_msg_btn2, false);
                                } else {
                                    // ios and android
                                    document.getElementById("sub_c2").addEventListener("touchstart", send_msg_btn2, false);
                                    document.getElementById("msg_c").addEventListener("focus", img_btn_fix_one, false);
                                    document.getElementById("msg_c").addEventListener("blur", img_btn_fix_two, false);
                                }
                            }
                        }

                    }, {
                        xtype: 'textfield',
                        name: 'from',
                        id: 'from_c',
                        height: '0px',
                        hidden: true
                    }, {
                        xtype: 'textfield',
                        name: 'to',
                        id: 'to_c',
                        height: '0px',
                        hidden: true
                    }]
                }, {
                    layout: {
                        type: 'hbox',
                        pack: 'center'
                    },



                }]
            }
        ]
    }
});


var myGeneralHelp = Ext.create('Ext.Panel', {
    id: 'help_general',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true
        }
    },
    style: 'width:95%;padding-bottom:40px;padding-left:4px;padding-right:4px;margin:0px auto;',
    items: [{

        html: '<div style="width:100%;padding:0px;margin:0px auto;font-size:1.2em;">'+ 
        ' <div style="text-align:center;" id="qtips"> <br/> <b>Quick Tips (Help/feedback)</b>'+
        '<br/><br/> </div> <div class="div_borderer"> <label for="showblock"><b>'+
        ' <div style="padding-top:10px;padding-bottom:10px;"> What is MyVidster? </div>'+
        ' </b></label> <input type="checkbox" id="showblock" class="ch_hd"/> <div id="block">'+
        ' <div class="div_styler"> MyVidster is a social video sharing and bookmarking site that'+
        ' lets you collect and share your favorite videos you find on the web. You can also'+
        ' explore and follow video collections from other users using MyVidster. If you like '+
        'watching and sharing online video, then you will love using MyVidster! </div> </div>'+
        ' </div> ' +
        
        glb_ios_pro_help + glb_ios_pro_help2 + 
        
        '<div class="div_borderer"> <label for="showblock2"><b>'+
        ' <div style="padding-top:10px;padding-bottom:10px;"> How to collect videos </div></b>'+
        '</label> <input type="checkbox" id="showblock2" class="ch_hd" /> <div id="block2">'+
        ' <div class="div_styler"> Click the "collect" button on any video to save and watch later.'+
        ' You can organize the videos you collect by saving them to specific channels. <br/><br/>'+
        ' <img src="https://cdn2.myvidster.com/images/app/help_collect.jpg" style="width:250px;"/>'+
        ' </div> </div> </div> <div class="div_borderer"> <label for="showblock3"><b>'+
        '<div style="padding-top:10px;padding-bottom:10px;"> Messaging </div></b></label>'+
        ' <input type="checkbox" id="showblock3" class="ch_hd" /> <div id="block3"> '+
        '<div class="div_styler"> To message a user, click on their profile photo, type a message'+
        ' and click send. <br/><br/> You have the option to block a user from messaging you or'+
        ' delete an entire chat thread by swiping left on any message tab. <br/><br/>'+
        ' <img src="https://cdn2.myvidster.com/images/app/help_chat.jpg" style="width:250px;"/>'+
        ' </div> </div> </div> <div class="div_borderer"> <label for="showblock6"><b>'+
        '<div style="padding-top:10px;padding-bottom:10px;"> Save videos to watch later </div></b>'+
        '</label> <input type="checkbox" id="showblock6" class="ch_hd" /> <div id="block6">'+
        ' <div class="div_styler"> Click on the "queue" button on any video, to quickly save a '+
        'video you are interested in continueing to watch later. </div> </div> </div>'+
        ' <div class="div_borderer"> <label for="showblock4"><b>'+
        '<div style="padding-top:10px;padding-bottom:10px;"> Settings </div></b></label>'+
        ' <input type="checkbox" id="showblock4" class="ch_hd" /> <div id="block4">'+
        ' <div class="div_styler"> In the settings tab you can set a passcode for the app on '+
        'startup, lock the screen orientation, disable/enable video history, and much more. <br/>'+
        '<br/> For android users you can switch between the the HTML5 video player and the '+
        'legacy video player.<br/><br/> <img src="https://cdn2.myvidster.com/images/app/help_settings.jpg"'+
        ' style="width:250px;"/> </div> </div> </div> <div class="div_borderer">'+
        '<label for="showblock7"><b><div style="padding-top:10px;padding-bottom:10px;"> '+
        'Can I change my username? </div></b></label> <input type="checkbox" id="showblock7"'+
        ' class="ch_hd" /> <div id="block7"> <div class="div_styler"> Username changes are not '+
        'support on the app but you can change your user name by visiting our website at '+
        'myvidster.com </div> </div> </div> <div class="div_borderer"> <label for="showblock8">'+
        '<b><div style="padding-top:10px;padding-bottom:10px;"> How do I reset my password? </div>'+
        '</b></label> <input type="checkbox" id="showblock8" class="ch_hd" /> <div id="block8"> '+
        '<div class="div_styler"> You can reset your password by going to the login screen within'+
        ' the User tab, and selecting "Reset Password". </div> </div> </div>'+
        '<div class="div_borderer"> <label for="showblock10"><b><div style="padding-top:10px;padding-bottom:10px;">'+
        ' How to bookmark videos from your browser </div></b></label> <input type="checkbox"'+
        ' id="showblock10" class="ch_hd" /> <div id="block10"> <div class="div_styler"> Go to your'+
        ' mobile browser and search for a video you would like to bookmark. Share/post the selected'+
        ' website to the MyVidster app to view later in your bookmarked videos. If you are having '+
        'trouble bookmarking a video through this method, please email the website to us at: myvidster.com '+
        '<br/><br/> <img src="https://cdn2.myvidster.com/images/app/share_btn4.jpg"'+
        ' style="width:250px;"/> </div> </div> </div>      <div class="div_borderer">'+
        ' <label for="showblock9"><b><div style="padding-top:10px;padding-bottom:10px;"> '+
        'Can I upload my videos? </div></b></label> <input type="checkbox" id="showblock9" '+
        'class="ch_hd" /> <div id="block9"> <div class="div_styler"> MyVidster does not provide '+
        'video upload functionality, but you can upload your video to popular video host like'+
        ' Youtube or Vimeo and then bookmark them to MyVidster. </div> </div> </div>'+

        '<div class="div_borderer">'+
        ' <label for="showblock11"><b><div style="padding-top:10px;padding-bottom:10px;"> '+
        'How can I refresh videos? </div></b></label> <input type="checkbox" id="showblock11" '+
        'class="ch_hd" /> <div id="block11"> <div class="div_styler">You can drag/pull videos down and then release to refresh them </div> </div> </div>'+

        ' <!-- <div style="text-align:center;"> <label for="showblock5"><b>Getting Started Video</b>'+
        '/label> <input type="checkbox" id="showblock5" class="ch_hd" /> <br/>'+
        ' <video src="https://trello-attachments.s3.amazonaws.com/536a5c828b7b93506bf1beb7/5807984721d134c22dc9d599/a1281ec15848d8eea58e3938cdf735a6/Bookmark_any_video_to_myvidster_tutorial.mp4"'+
        ' style="width:300px;" controls preload="auto"> </video> </div> --> </div>'
    }]
});


Ext.define('myVidster.view.Help', {
    extend: 'Ext.Panel',
    id: 'Help',
    alias: 'widget.Help',
    config: {
        scrollable: {
            direction: 'vertical',
            directionLock: true,
        },
        fullscreen: true,
        listeners: {
            initialize: function(panel, _eOpts) {
                panel.element.on({
                    swipe: function(_e) {
                        //onSwipe(e.direction, 5);
                    }
                });
            }
        },
        items: [helpToolbar, myGeneralHelp, createBottomTabBar('home_tabbar_help')]
    }
});



Ext.application({
    name: "myVidster",

    views: ['Home', 'Video', 'Video2', 'UserMenu', 'UserLogin', 'Search', 'Settings', 'UserList', 'Collect', 'Payment', 'General', 'UserRegistration', 'UserPhoneInput', 'UserPhoneVerification', 'Comment', 'ChromeCast', 'LockScreen', 'ProMember', 'FeedBack', 'MsgUser', 'MsgList', 'EditVideo', 'EditCollection', 'Help', 'inviteF', 'Discover', 'Tutorial', 'UserProfile', 'Contacts'],

    viewport: {

        layout: {
            type: 'card',
            animation: 'fade'
        },

        listeners: {
            activeitemchange: card_handler
        }
    },

    icon: {
        '57': 'resources/icons/Icon.png',
        '72': 'resources/icons/Icon~ipad.png',
        '114': 'resources/icons/Icon@2x.png',
        '144': 'resources/icons/Icon~ipad@2x.png'
    },

    isIconPrecomposed: true,

    launch: function() {
        this.launched = true;
        this.mainLaunch();
    },
    mainLaunch: function() {
        if (typeof device != 'undefined') {
            //Native only

            //Andriod only
            if (Ext.os.is('Android')) {
                document.addEventListener("menubutton", onMenuKeyDown, false);
                document.addEventListener("backbutton", onBackKeyDown, false);
                document.addEventListener("searchbutton", onSearchKeyDown, false);
				
				if (typeof AndroidFullScreen != 'undefined') {
					AndroidFullScreen.isSupported(makeImmersive, Ext.emptyFn);
				}
				
				if (typeof PictureInPicture != 'undefined') {
					init_pip();
				}

                if (typeof window.AmazonMobileAds != 'undefined') {
                    window.AmazonMobileAds.setApplicationKey(onFail, onFail, [{
                        "stringValue": "b1548a514fdd4f8ea854ca1f35c0ff14"
                    }]);

                    window.AmazonMobileAds.registerApplication(onFail, onFail, []);

                    window.AmazonMobileAds.createInterstitialAd(
                        function(operationResponse) {
                            // Handle success
                            savedAdObject = operationResponse;
                            var adType = operationResponse.adType;
                            var identifier = operationResponse.identifier;
                        },
                        function(_errorResponse) {
                            // Handle error
                        }, []
                    );
                }
            }

            setTimeout(initializeCastApi, 1000);
			
            if (v_orientation_chk) {
                screen.lockOrientation(Ext.Viewport.determineOrientation());
            }

            document.addEventListener('onInterstitialPresent', function() {
                clearMask();
            });
            document.addEventListener("resume", onResume, false);
            document.addEventListener("pause", onPause, false);

            pictureSource = navigator.camera.PictureSourceType;
            destinationType = navigator.camera.DestinationType;

            window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, onRequestFileSystemSuccess, null);
			
			if(typeof analytics.startTrackerWithId != 'undefined') {
                analytics.startTrackerWithId('UA-1328730-5');
            }
            else if (typeof window.ga != 'undefined') {
                window.ga.startTrackerWithId('UA-1328730-5');
            }
			
            if (!checkConnection()) myvAlert('No network connection detected');
        } else {
            //web app only
            glb_ver = 1;

			//hack to fix scroll bounce for web app
			document.addEventListener('touchmove', function(e) {
				e.preventDefault();
			}, { passive: false });
        }
        //for all devices
        glb_HTML5_only = 1;

		if (typeof Pusher == 'undefined') {
			Ext.Loader.loadScriptFile('https://api.myvidster.com/scripts/pusher.min.php',create_pusher,Ext.emptyFn,Ext.emptyFn,true);	
		}
		else {
            create_pusher();
		}

		var remote_file_loader = 'https://api.myvidster.com/scripts/js_loader.php?url=https://m.myvidster.com/js/';
		
		if(!videojs.VERSION) {
			videojs = null;
			document.styleSheets[2].disabled = true;
			load_remote_css('videojs','https://m.myvidster.com/css/video-js.min.css?cb='+cachebuster);
			Ext.Loader.loadScriptFile(remote_file_loader+'video.min.js',Ext.emptyFn,Ext.emptyFn,Ext.emptyFn,true);
		}
		
		if(glb_device_type != 'phone' || Ext.os.is('Android')) {
			glb_pip_support = true;
		}

		load_remote_css('mobile','https://m.myvidster.com/css/videojs-mobile-ui.css?cb='+cachebuster);
		Ext.Loader.loadScriptFile(remote_file_loader+'videojs-mobile-ui.min.js',Ext.emptyFn);

		load_remote_css('download','https://m.myvidster.com/css/videojs-vjsdownload2.css?cb='+cachebuster);
		Ext.Loader.loadScriptFile(remote_file_loader+'videojs-vjsdownload.min2.js',Ext.emptyFn);

        Ext.Viewport.add(Ext.create('myVidster.view.Home')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Video'));  
        Ext.Viewport.add(Ext.create('myVidster.view.Video2'));   
        Ext.Viewport.add(Ext.create('myVidster.view.UserMenu')); 
        Ext.Viewport.add(Ext.create('myVidster.view.UserLogin')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Search')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Followers')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Settings')); 
        Ext.Viewport.add(Ext.create('myVidster.view.UserList'));  
        Ext.Viewport.add(Ext.create('myVidster.view.Collect'));  
        Ext.Viewport.add(Ext.create('myVidster.view.Payment'));   
        Ext.Viewport.add(Ext.create('myVidster.view.General')); 
        Ext.Viewport.add(Ext.create('myVidster.view.UserRegistration')); 
        Ext.Viewport.add(Ext.create('myVidster.view.UserPhoneInput')); 
        Ext.Viewport.add(Ext.create('myVidster.view.UserPhoneVerification')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Comment')); 
        Ext.Viewport.add(Ext.create('myVidster.view.ChromeCast')); 
        Ext.Viewport.add(Ext.create('myVidster.view.LockScreen')); 
        Ext.Viewport.add(Ext.create('myVidster.view.MsgList')); 
        Ext.Viewport.add(Ext.create('myVidster.view.FeedBack')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Help')); 
        Ext.Viewport.add(Ext.create('myVidster.view.MsgUser')); 
        Ext.Viewport.add(Ext.create('myVidster.view.ProMember'));  
        Ext.Viewport.add(Ext.create('myVidster.view.EditVideo')); 
        Ext.Viewport.add(Ext.create('myVidster.view.EditCollection')); 
        Ext.Viewport.add(Ext.create('myVidster.view.inviteF')); 
        Ext.Viewport.add(Ext.create('myVidster.view.UserProfile')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Tutorial')); 
        Ext.Viewport.add(Ext.create('myVidster.view.Resubscribe')); 
        contactsView = Ext.create('myVidster.view.Contacts'); 
        emptyContactsView = Ext.create('myVidster.view.EmptyContacts'); 
        emptyRecommendationsView = Ext.create('myVidster.view.EmptyRecommendations'); 
        recommendationsView = Ext.create('myVidster.view.Discover'); 
        discoverView = Ext.create('myVidster.view.Disco'); 
        sendVideoView = Ext.create('myVidster.view.SendVideo');  

        Ext.Viewport.add(sendVideoView); // chat func
        Ext.Viewport.add(discoverView);
        Ext.Viewport.add(contactsView);
        Ext.Viewport.add(recommendationsView);
        Ext.Viewport.add(emptyContactsView );
        Ext.Viewport.add(emptyRecommendationsView);

        if (typeof window.indexedDB != 'undefined') {
          Ext.Viewport.setWidth('100%');

          if (glb_device_type == 'tablet')  {
            Ext.Viewport.setHeight('100%');
          }
        }
        if (Ext.Viewport.determineOrientation() === 'landscape' && isIphoneX) {
            adjustLandscapeForIPhoneX();
        } else if (isIphoneX) {
            revertFromLandscapeIPhoneX();
        }
        if (Ext.Viewport.determineOrientation() === 'landscape' &&  Ext.os.is.iOS) {
            adjustLandscapeForIPhone();
        }
        if (Ext.Viewport.determineOrientation() === 'landscape') {
            isLandscape = true;
        }
        Ext.getCmp('UserProfile').setItems([userProfileToolbar, profileForm, createBottomTabBar('homeBottomBar_options')]);

        login_first();
        checkFingerprintSupport();
        checkFingerprintSettings();

        var checkExist = setInterval(function() {
            if(typeof Pusher != 'undefined' && glb_logincheck_done == true){
                load_app_data();
                checkHeightForError();
                clearInterval(checkExist);
            }
        }, 500);
        
        var faceIDcheck = setInterval(function() {
            if(glb_lock_check_done == true){
                if (!glb_lock_screen_override && glb_lock_screen) {
                    startFingerprintPrompt();
                }
                clearInterval(faceIDcheck);
            }
        }, 700);
        
    }
});