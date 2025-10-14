var tab_spacer_btn = {
    xtype: 'spacer',
};
var glb_device_type4 = '';
var agent_mobile = (navigator.userAgent).toLowerCase().indexOf('mobile') > -1;
var agent_mobile2 = (navigator.userAgent).toLowerCase().indexOf('ipad') > -1;
if(Ext.os.is.Desktop){ glb_device_type4 = 'desktop'; }
else if(agent_mobile && !agent_mobile2){ glb_device_type4 = 'phone'; } // Ext.os.is.Phone &&
else{ glb_device_type4 = 'tablet'; } // (Ext.os.is.Tablet)

var button_sz = '';
if(glb_device_type4 == 'tablet'){ button_sz = 'font-size: 1.5em;'; }

var tab_home_btn = {
    title: 'Home',
    iconCls: 'home',
    style: button_sz,
    iconMask: true,
    handler: function() {
        'use strict';
        if(glb_homecheck_done != true){return;}
        glb_bottom_panel = "Home";
        clearCountdown();
        Ext.Viewport.setActiveItem("Home");
        push_alert_bug_fix();
        glb_seb_back = 0;
        custom_views_arr = [];
        searchviewbackhack();
        custom_views_arr.push(Ext.Viewport.getActiveItem().id);
        var array_data = [0];
        glb_custom_views_arr_data = [];
        glb_custom_views_arr_data.push(array_data);
        inDiscoverView = false;
    },
};
var tab_user_btn = {
    title: 'User',
    iconCls: 'user',
    style: button_sz,
    iconMask: true,
    handler: function(){
        if(glb_homecheck_done != true){return;}
        onMenuKeyDown();
        searchviewbackhack(); 
    }
};

var tab_search_btn = {
    title: 'Search',
    iconCls: 'search',
    style: button_sz,
    iconMask: true,
    cls: 'myv_hack',
    handler: function(){
        if(glb_homecheck_done != true){return;}
        onSearchKeyDown();
        try{ Ext.getCmp('search_var').deselectAll(); }catch(err){}
        searchviewbackhack();
        custom_views_arr.push(Ext.Viewport.getActiveItem().id);
        var array_data = [0];
        glb_custom_views_arr_data.push(array_data);
       
    }
};

var tab_chat_btn = {
    title: 'Chat',
    iconCls: 'chat2',
    style: button_sz,
    iconMask: true,
    badgeText: null,
    handler: function(){
        if(glb_homecheck_done != true){return;}
        chat_handler();
        searchviewbackhack(); 
    }
};

var tab_more_btn = {
    title: 'More',
    iconCls: 'more',
    style: button_sz,
    iconMask: true,
    handler: function(){
        if(glb_homecheck_done != true){return;}
        onMoreDown();
    }
};

var all_bottom_buttons = [tab_home_btn, tab_spacer_btn, tab_user_btn, tab_spacer_btn,
tab_search_btn, tab_spacer_btn, tab_chat_btn, tab_spacer_btn, tab_more_btn];


// Discover Feature

function createDiscoverButton(id) {
  return {
    title: 'Discover',
    id: id,
    handler: function() {
        disco_func();
        setMask('Please wait...');
    }
  }
}

function createRecommendationsButton(id) {
  return {
    title: 'Recommendations',
    id: id,
    handler: function() {
        glb_discover_tab = 'recommendations';
        // retrieve contacts from phone and get recommendations based on contacts
        //retrievePhoneContacts();
        getRecommendations();
        discoverLastPanel = 'Discover';
        setMask('Please wait...');
    }
  };
}
function createContactsButton(id) {
  return {
    title: 'Contacts',
    id: id,
    handler: function() {
        glb_discover_tab = 'contacts';
        var discover_recommended_btn = document.getElementById('discover_recommended_btn');
        //retrievePhoneContacts();
        //logic to check phone for numbers and get contacts here
        getContactsList();
    }
  };

}
// ************* chat func
function createAllDiscoverButtons(number) {
  return [
  { xtype: 'spacer' },
  createDiscoverButton('discover_discover_btn'+ number),
  { xtype: 'spacer' },
  createRecommendationsButton('discover_recommended_btn'+ number),
  { xtype: 'spacer' },
  createContactsButton('discover_contacts_btn'+ number),
  { xtype: 'spacer' },
  ];
}

function createSendFollowerVideoButton(id) {
  return {
    title: 'Followers',
    id: id,
    handler: function() {
      populateSendVideoList('followers', null);
      }
  }
}
function createSendFollowingVideoButton(id) {
  return {
    title: 'Following',
    id: id,
    handler: function() {
      populateSendVideoList('following', null);
    }
  }
}
function createSendVideoButtons(number){
  return [
  { xtype: 'spacer' },
  createSendFollowerVideoButton('send_follower_video_btn'+ number),
  { xtype: 'spacer' },
  createSendFollowingVideoButton('send_following_video_btn'+ number),
  { xtype: 'spacer' },
  ];
}

function createAllDiscoverButtons(number){
  return [
  { xtype: 'spacer' },
  createDiscoverButton('discover_discover_btn'+ number),
  { xtype: 'spacer' },
  createRecommendationsButton('discover_recommended_btn'+ number),
  { xtype: 'spacer' },
  createContactsButton('discover_contacts_btn'+ number),
  { xtype: 'spacer' },
  ];
}


var send_video_buttons = createSendVideoButtons('01');

// ************* chat func


var all_discover_buttons = createAllDiscoverButtons('01');

var all_discover_buttons01 = createAllDiscoverButtons('01');

var all_discover_buttons02 = createAllDiscoverButtons('02');

var all_discover_buttons03 = createAllDiscoverButtons('03');

var all_discover_buttons04 = createAllDiscoverButtons('04'); // empty contacts

var all_discover_buttons05 = createAllDiscoverButtons('05'); // empty recommendations

// end Discover

// end Profile

var chomecast_btn1 = {
    xtype: 'button',
    id: 'chomecast_btn1',
    iconMask: true,
    hidden: true,
    iconCls: 'casticon_on',
    handler: function() {
    try{ var video = document.getElementsByTagName("video")[0]; video.pause(); }
    catch(err) {}

        if (Ext.os.is('iOS')) {
            if (!glb_ios_glb_session) {
                chromeCastDisconnectFromDevice();
                chromeCastShowActiveDevices();
            }
		
			else {
                navigator.notification.confirm('Disconnect from Chromecast?',
                    function(buttonIndex) {
                        if (buttonIndex == 1) {
                            glb_ios_glb_session = null;
                            chromeCastDisconnectFromDevice();
                        }
                    }, 'chromecast');
            }
        } else {
            if (!glb_session) {
                chomecast_requestSession();
            }
			
			else {
                navigator.notification.confirm('Disconnect from ' + glb_session.displayName + '?',
                    function(buttonIndex) {
                        if (buttonIndex == 1) {
                            glb_session.stop(onSuccess, onFail);
                            glb_session = null;
                        }
                    }, 'chromecast');
            }
        }
    }
};


var chomecast_btn2 = {
    xtype: 'button',
    id: 'chomecast_btn2',
    iconMask: true,
    hidden: true,
    iconCls: 'casticon_on',
    handler: function() {
    try{ var video = document.getElementsByTagName("video")[0]; video.pause(); }
    catch(err) {}

        if (Ext.os.is('iOS')) {
            if (!glb_ios_glb_session) {
                chromeCastDisconnectFromDevice();
                chromeCastShowActiveDevices();
            }
		
			else {
                navigator.notification.confirm('Disconnect from Chromecast?',
                    function(buttonIndex) {
                        if (buttonIndex == 1) {
                            glb_ios_glb_session = null;
                            chromeCastDisconnectFromDevice();
                        }
                    }, 'chromecast');
            }
        } else {
            if (!glb_session) {
                chomecast_requestSession();
            }
		
			else {
                navigator.notification.confirm('Disconnect from ' + glb_session.displayName + '?',
                    function(buttonIndex) {
                        if (buttonIndex == 1) {
                            glb_session.stop(onSuccess, onFail);
                            glb_session = null;
                        }
                    }, 'chromecast');
            }
        }
    }
};


var chomecast_btn3 = {
    xtype: 'button',
    id: 'chomecast_btn3',
    iconMask: true,
    hidden: true,
    iconCls: 'casticon_on',
    handler: function() {
    try{ var video = document.getElementsByTagName("video")[0]; video.pause(); }
    catch(err) {}

        if (Ext.os.is('iOS')) {
            if (!glb_ios_glb_session) {
                chromeCastDisconnectFromDevice();
                chromeCastShowActiveDevices();
            }
		
			else {
                navigator.notification.confirm('Disconnect from Chromecast?',
                    function(buttonIndex) {
                        if (buttonIndex == 1) {
                            glb_ios_glb_session = null;
                            chromeCastDisconnectFromDevice();
                        }
                    }, 'chromecast');
            }
        } else {
            if (!glb_session) {
                chomecast_requestSession();
            }
	
			else {
                navigator.notification.confirm('Disconnect from ' + glb_session.displayName + '?',
                    function(buttonIndex) {
                        if (buttonIndex == 1) {
                            glb_session.stop(onSuccess, onFail);
                            glb_session = null;
                        }
                    }, 'chromecast');
            }
        }
    }
};



var video_top_buttons1 = [{
        xtype: 'button',
        iconMask: true,
        iconCls: 'arrow-left2',
        pressedCls: '',
        handler: function() {
            onBackKeyDown();
        }
    }, {
        xtype: 'spacer'
    },
    chomecast_btn1, {
        iconMask: true,
        iconCls: 'refresh',
        pressedCls: '',
        handler: function() {
            getVideo(glb_current_video, glb_video_type, true);
        }
    }
];

var video_top_buttons2 = [{
        xtype: 'button',
        iconMask: true,
        iconCls: 'arrow-left2',
        pressedCls: '',
        handler: function() {
            onBackKeyDown();
        }
    }, {
        xtype: 'spacer'
    },
    chomecast_btn2, {
        iconMask: true,
        iconCls: 'refresh',
        pressedCls: '',
        handler: function() {
            getVideo(glb_current_video, glb_video_type, true);
        }
    }
];

var home_top_buttons = [{
    xtype: 'button',
    pressedCls: '',
    text: 'sort',
    handler: function() {

            if(glb_homecheck_done != true){return;}

            var more_btn_sz = '';
            if (glb_device_type == 'tablet') {
                more_btn_sz = 'font-size: 1.22em;';
            }
        
            var video_actionSheet = Ext.create('Ext.ActionSheet', {
                items: [{
                    text: 'New',
                    style: more_btn_sz,
                    handler: function(btn, evt) {
                        glb_sortby = "New";
                        load_homepage(0, 0);
                        clearMask();
                        video_actionSheet.hide();
                    }
                }, {
                    text: 'Popular',
                    style: more_btn_sz,
                    handler: function(btn, evt) {
                        glb_sortby = "Popular";
                        load_homepage(0, 0);
                        clearMask();
                        video_actionSheet.hide();
                    }
                }, {
                    text: 'Recent',
                    style: more_btn_sz,
                    handler: function(btn, evt) {
                        glb_sortby = "Recent";
                        load_homepage(0, 0);
                        clearMask();
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
}, {
    xtype: 'spacer'
}, chomecast_btn3];



function createTopButtons() {
  return [{
    xtype: 'button',
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    hidden: false,
    handler: //onBackKeyDown 
    function() {
        var items = custom_views_arr;
        onBackKeyDown();
    }

    }, {
        xtype: 'spacer'
    }];
}

function createTopButtonsAccount() {
    return [{
      xtype: 'button',
      iconMask: true,
      iconCls: 'arrow-left2',
      pressedCls: '',
      hidden: false,
      handler: 
      function() {
          var items = custom_views_arr;
          onBackKeyDown();
      }
  
      }, {
          xtype: 'spacer'
      },
    
      {
        xtype: 'button',
        text: 'Logout',
        pressedCls: '',
        handler: function() {
            logout();
        }
    }
    
    
    ];
  }


var gen_top_buttons = createTopButtons();
var account_top_buttons = createTopButtonsAccount();
var chrome_top_buttons = createTopButtons()


var search_top_buttons = [{
    xtype: 'button',
    iconMask: true,
    id: 'searchbackbtn',
    iconCls: 'arrow-left2',
    pressedCls: '',
    handler: onBackKeyDown
}, {
    xtype: 'spacer'
}, {
    xtype: 'button',
    text: 'Clear',
    pressedCls: '',
    handler: function() {
        clear_search(true);
    }
}];

function createTopBackButton(id) {
  return {
    xtype: 'button',
    id: id,
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    handler: onBackKeyDown
  };
}

var user_top_back_btn = createTopBackButton('user_top_back_btn');
var user_top_back_btn_msg = createTopBackButton('user_top_back_btn_msg');
var user_top_back_btn_msgList = createTopBackButton('user_top_back_btn_msgList');
var user_top_back_btn2 = createTopBackButton('user_top_back_btn2');
var user_top_back_btn3 = createTopBackButton('user_top_back_btn3');
var user_top_back_btn4 = createTopBackButton('user_top_back_btn4');

var user_top_follow_btn = {
    id: 'user_top_follow_btn',
    xtype: 'button',
    pressedCls: '',
    text: '',
    hidden: true,
    handler: function() {
        var x = Ext.getCmp('user_top_follow_btn').getText();
        if (x == 'Follow')
            follow_user(glb_current_list['type'], glb_current_list['id'], 0);
        else if (x == 'Unfollow')
            follow_user(glb_current_list['type'], glb_current_list['id'], 1);
        else
            list_videos(glb_current_list['type'], glb_current_list['id'], 0, 1);
    }
};


var user_top_channelcollection_btn = {
    xtype: 'button',
    pressedCls: '',
    id: 'user_top_channelcollection_btn',
    text: 'add',
    handler: function() {
        



        if(glb_top_channelcollection_btn == 'Collection'){

            // create a new channel
            var msg = new Ext.MessageBox({
                width: 300,
                floating: 1,
                margin: '80 0 0 0'
            });
            msg.show({
                title: 'Create a new channel',
                items: [            
                    {
                       xtype:'textfield' ,
                       name: 'name',
                       id: 'cname1',
                       label: 'Name'              
                     },
                    {
                       xtype:'textfield',    
                       name: 'description',
                       id: 'cdesc1',
                       label: 'Desc.'              
                     }
                   ],
                buttons: [{
                    text: 'submit',
                    itemId: 'submit'
                }, {
                    text: 'cancel',
                    itemId: 'cancel'
                }],
                fn: function(response) {
                    if (response == 'submit') {
                        var cname1 = Ext.getCmp('cname1').getValue();
                        var cdesc1 = Ext.getCmp('cdesc1').getValue();
    
                        setMask('Creating Channel...');
    
                        Ext.util.JSONP.request({
                            url: 'https://api.myvidster.com/mobile_json2.php',
                            callbackKey: 'callback',
                            params: {
                                type: 'create_channel',
                                name: cname1,
                                description: cdesc1,
                                user_id: account.get('user_id'),
                                token: glb_msg_token,
                                pro_status: glb_is_pro_ckeck,
                                pkey: account.get('pw'),
                                id: glb_gallery_id,
                                uniquify: Math.random()
                            },
                            callback: function(success, result) {
                                clearMask();
                                if (result.items) {
                                    if (result.items[0].errors) {
                                        myvAlert(result.items[0].errors);
                                    } else {
                                        glb_seb_back = 1;
                                        list_videos(glb_current_list['type'], glb_current_list['id'], 0, 1);
                                        list_videos_obj.currentPage = 1;
                                        glb_seb_back = 1;
                                        list_videos_obj.load();
                                        myvAlert("New channel added! You can now save videos to the channel.");
                                    }
                                }
                            }
                        });
                        
                    } else if (response == 'cancel') {
                           
                    }
                }
            });




        }else{


            var msg = new Ext.MessageBox({
                width: 300,
                floating: 1,
                margin: '80 0 0 0'
            });
            msg.show({
                title: 'Create a new collection',
                items: [            
                    {
                       xtype:'textfield' ,
                       name: 'name',
                       id: 'cname2',
                       label: 'Name'              
                     },
                    {
                       xtype:'textfield',    
                       name: 'description',
                       id: 'cdesc2',
                       label: 'Desc.'              
                     }
                   ],
                buttons: [{
                    text: 'submit',
                    itemId: 'submit'
                }, {
                    text: 'cancel',
                    itemId: 'cancel'
                }],
                fn: function(response) {
                    if (response == 'submit') {
                        var cname2 = Ext.getCmp('cname2').getValue();
                        var cdesc2 = Ext.getCmp('cdesc2').getValue();
    
                        setMask('Creating collection...');
    
                        Ext.util.JSONP.request({
                            url: 'https://api.myvidster.com/mobile_json2.php',
                            callbackKey: 'callback',
                            params: {
                                type: 'create_collection',
                                name: cname2,
                                description: cdesc2,
                                user_id: account.get('user_id'),
                                token: glb_msg_token,
                                pro_status: glb_is_pro_ckeck,
                                pkey: account.get('pw'),
                                id: 0,
                                uniquify: Math.random()
                            },
                            callback: function(success, result) {
                                clearMask();
                                if (result.items) {
                                    if (result.items.errors) {
                                        myvAlert(result.items.errors);
                                    } else {
                                        glb_seb_back = 1;
                                        list_videos('User', account.get('user_id'), 0);
                                        list_videos_obj.currentPage = 1;
                                        glb_seb_back = 1;
                                        list_videos_obj.load();
                                    }
                                }
                            }
                        });
                        
                    } else if (response == 'cancel') {
                           
                    }
                }
            });


        }


    }
};


var user_top_seach_btn = {
    xtype: 'button',
    pressedCls: '',
    id: 'user_top_seach_btn',
    text: 'edit',
    handler: function() {


     if(Ext.getCmp('user_top_seach_btn').getText() == 'edit'){
        Ext.getCmp('user_top_seach_btn').setText('action');

        glb_mass_edits = '';

        var x = document.getElementsByClassName('massedit');
        var i;
        for (i = 0; i < x.length; i++) {
          x[i].checked = false;
        }

        var x = document.getElementsByClassName('masseditbox');
        var i;
        for (i = 0; i < x.length; i++) {
          x[i].style.display = "block";
        }


      }

      else if(Ext.getCmp('user_top_seach_btn').getText() == 'action'){


        var watched_btn = '';
        if(glb_current_list['type'] == 'Queue'){
            watched_btn = false;
        }else{
            watched_btn = true;
        }

var massedit_actionSheet = Ext.create('Ext.ActionSheet', {
        items: [
       {
            text: 'Delete',
            //style: more_btn_sz2,
            handler: function(btn, evt) {
                
                massedit_actionSheet.hide();



   // delete on server

setMask('Deleting...');

var glb_mass_edits_ids = glb_mass_edits.replace(/_mass_edits/g, '');

if(glb_mass_edits_ids.charAt(0) == ','){
    glb_mass_edits_ids = glb_mass_edits_ids.replace(',', '');
}

var view = '';
if(glb_current_list['type'] == 'Queue'){
    view = 'update_queue';
}else if(glb_current_list['type'] == 'Profile'){
    view = 'remove_video';
}else if(glb_current_list['type'] == 'Channel'){
    view = 'remove_video';
}


Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: view,
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            id: glb_mass_edits_ids,
            status: 0
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                if (items && items[0].response == "Success") {
                  clearMask();
                  list_videos_obj.currentPage = 1;
                  glb_seb_back = 1;
                  list_videos_obj.load();

                  var scroller = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                  scroller.scrollTo({
                      x: 0,
                      y: 0
                  });
                } else if(items && items[0].response ) {
                    myvAlert(items[0].response);
                  clearMask();
                }

                clearMask();
                // scroll to top
                list_videos_obj.currentPage = 1;
                glb_seb_back = 1;
                list_videos_obj.load();

                var scroller = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                scroller.scrollTo({
                    x: 0,
                    y: 0
                });
                
            } else {
                   clearMask();
            }
        }
    });

    Ext.getCmp('user_top_seach_btn').setText('edit');
    var x = document.getElementsByClassName('masseditbox');
    var i;
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";
      
    }
     glb_mass_edits = '';

    var x = document.getElementsByClassName('massedit');
    var i;
    for (i = 0; i < x.length; i++) {
      x[i].checked = false;
    }
            }
        },
        
        {
            text: 'Mark as watched',
            hidden: watched_btn,
            handler: function(btn, evt) {
                
                massedit_actionSheet.hide();

            setMask('Updating...');

            var glb_mass_edits_ids = glb_mass_edits.replace(/_mass_edits/g, '');

            if(glb_mass_edits_ids.charAt(0) == ','){
                    glb_mass_edits_ids = glb_mass_edits_ids.replace(',', '');
            }

        Ext.util.JSONP.request({
        url: 'https://api.myvidster.com/mobile_json2.php',
        callbackKey: 'callback',
        params: {
            type: 'update_queue',
            user_id: account.get('user_id'),
            token: glb_msg_token,
            pro_status: glb_is_pro_ckeck,
            pkey: account.get('pw'),
            id: glb_mass_edits_ids,
            status: 1
        },
        callback: function(success, result) {
            if (result) {
                var items = result.items;
                if (items && items[0].response == "Success") {
                  clearMask();
                  list_videos_obj.currentPage = 1;
                  glb_seb_back = 1;
                  list_videos_obj.load();

                  var scroller = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                  scroller.scrollTo({
                      x: 0,
                      y: 0
                  });

                } else if(items && items[0].response ) {
                    myvAlert(items[0].response);
                  clearMask();
                }

                clearMask();

                list_videos_obj.currentPage = 1;
                glb_seb_back = 1;
                list_videos_obj.load();

                var scroller = Ext.getCmp('list_videos_var').getScrollable().getScroller();
                scroller.scrollTo({
                    x: 0,
                    y: 0
                });
                
            } else {
                   clearMask();
            }
        }
    });


    Ext.getCmp('user_top_seach_btn').setText('edit');
    var x = document.getElementsByClassName('masseditbox');
    var i;
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";
      
    }
     glb_mass_edits = '';

    var x = document.getElementsByClassName('massedit');
    var i;
    for (i = 0; i < x.length; i++) {
      x[i].checked = false;
    }
            }
        },
        
        {
            text: 'Cancel',
            ui: 'decline',
            handler: function(btn, evt) {
                massedit_actionSheet.hide();

    Ext.getCmp('user_top_seach_btn').setText('edit');
        var x = document.getElementsByClassName('masseditbox');
        var i;
        for (i = 0; i < x.length; i++) {
          x[i].style.display = "none";
        }
        glb_mass_edits = '';

        var x = document.getElementsByClassName('massedit');
        var i;
        for (i = 0; i < x.length; i++) {
          x[i].checked = false;
        }
            }
        }]
    });

    Ext.Viewport.add(massedit_actionSheet);
    massedit_actionSheet.show();

      }


      var interval = setInterval(function(){


        if(Ext.getCmp('user_top_seach_btn').getText() == 'action' && Ext.Viewport.getActiveItem().id == 'UserList'){
    
            // && glb_current_list['type'] == 'Profile' || glb_current_list['type'] == 'Queue'  || glb_current_list['type'] == 'Downloads' 
    
        }else{
    
            clearInterval(interval);
            Ext.getCmp('user_top_seach_btn').setText('edit');
            var x = document.getElementsByClassName('masseditbox');
            var i;
            for (i = 0; i < x.length; i++) {
              x[i].style.display = "none";
              
            }
             glb_mass_edits = '';
    
            var x = document.getElementsByClassName('massedit');
            var i;
            for (i = 0; i < x.length; i++) {
              x[i].checked = false;
            }
        }
    }, 80); 

    }
};

var collect_btn = {
    xtype: 'button',
    pressedCls: '',
    id: 'collect_btn',
    flex: 0,
    scope: this,
    handler: function() {
        var x = Ext.getCmp('collect_btn').getText();
        var fields = Ext.getCmp('videoCollectForm').getValues();
        var video_id = encodeURIComponent(Ext.getCmp('collect_video_id').getValue());
        var video_title = Ext.getCmp('collect_video_title').getValue();
        var video_tags = Ext.getCmp('collect_video_tags').getValue();
        var channel = Ext.getCmp('collect_channel').getValue();
        var access = fields['access'];
        if (!access) access = '0';

        if (x == 'collect') {
            bookmark_video(video_id, video_title, channel, video_tags, access);
        } else {
            record_video(video_title, channel, video_tags, access);
        }
    }
};

var follow_search_btn = {
    xtype: 'button',
    pressedCls: '',
    id: 'follow_search_btn',
    iconMask: true,
    iconCls: 'search',
    handler: function() {
        clear_search(true);
        //search_obj.removeAll();
        video_search('', 1, glb_back_data['search_type'], glb_back_data['search_id'], glb_back_data['list_name']);
        Ext.Viewport.animateActiveItem("Search", {
            type: 'fade',
        });
        
        setTimeout(function() {
            Ext.getCmp('search_bar').focus();
        }, 500);
    }
};


var follow_top_buttons = [{
    xtype: 'button',
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    handler: onBackKeyDown
}, {
    xtype: 'spacer'
}, follow_search_btn
];

var comment_btn = {
    xtype: 'button',
    id: 'comment_btn',
    pressedCls: '',
    flex: 0,
    scope: this,
    text: 'post',
    handler: function() {
        var fields = Ext.getCmp('videoCommentForm').getValues();
        var comment_name = fields['comment_name'];
        var comment_email = fields['comment_email'];
        var comment_post = fields['comment_post'];
        var comment_master_id = fields['comment_master_id'];
        var comment_ip = fields['comment_ip'];
        var comment_title = fields['comment_title'];
        if (comment_post)
            postComment(comment_name, comment_email, comment_post, comment_ip, comment_master_id, comment_title);
        else
            myvAlert('Please leave comment');
    }
};

var video_edit_btn = {
    xtype: 'button',
    id: 'video_edit_btn',
    pressedCls: '',
    scope: this,
    text: 'Update',
    margin: '10',
    handler: function() {
        setMask('Loading...');
        var video_id = Ext.getCmp('edit_video_id').getValue();
        editVid('edit', video_id);

    }
};

var col_btn = {
    xtype: 'button',
    id: 'edit_col_btn',
    pressedCls: '',
    scope: this,
    text: 'Update',
    margin: '10',
    handler: function() {
    //setMask('Loading...');
        var col1 = Ext.getCmp('edit_col_title').getValue();
        var col2 = Ext.getCmp('edit_desc_col_text').getValue();
        var col3 = Ext.getCmp('col_id').getValue();
        var col4 = Ext.getCmp('col_type_id').getValue();
        edit_col(col1, col2, col3, col4);

    }
};

var video_delete_edit_btn = {
    xtype: 'button',
    id: 'video_delete_edit_btn',
    pressedCls: '',
    scope: this,
    text: 'Delete Video',
    margin: '10',
    ui: 'decline',
    handler: function() {
      var video_id = Ext.getCmp('edit_video_id').getValue();
        //var fields = Ext.getCmp('videoEditForm').getValues();
        //var video_title = fields['video_title'];
        //var video_tags = fields['video_tags'];
        //editVid('delete', video_id);
      delete_video_collection(video_id);
    }
};

var card_scan_btn = {
    xtype: 'button',
    id: 'card_scan_btn',
    flex: 0,
    scope: this,
    text: 'scan card',
    margin: 10,
    handler: function() {
        if (typeof device != 'undefined' && Ext.os.is('Android')) {
            glb_lock_screen_override = 1;
            window.plugins.CardIOPlugin.scan(onCardIOComplete, onCardIOCancel);
        } else if (typeof CardIO != 'undefined') {
            CardIO.scan({
                    "collect_expiry": true,
                    "collect_cvv": false,
                    "collect_zip": false,
                    "shows_first_use_alert": true,
                    "disable_manual_entry_buttons": false
                },
                onCardIOComplete,
                onCardIOCancel
            );
        }
    }
};

var payment_btn = {
    xtype: 'button',
    id: 'payment_btn',
    flex: 0,
    scope: this,
    text: 'Upgrade',
    margin: 10,
    width: "100px",
    handler: function() {
        var fields = Ext.getCmp('PaymentForm').getValues();
        var card_num = fields['card_num'];
        var card_code = fields['card_code'];
        var exp_date = fields['exp_date'];
        var cust_id = fields['cust_id'];
        var first_name = fields['first_name'];
        var last_name = fields['last_name'];
        var myv_item = fields['myv_item'];
        var tiers = fields['tiers'];

        /*
        var auto_bill = '';
        if(Ext.ComponentQuery.query('radiofield[name=auto_bill]')[0]['_checked'] == true){ auto_bill = 0; }
        else{ auto_bill = 1; }
        */

        if (typeof device != 'undefined') {



            navigator.notification.confirm('Click yes to confirm.',
                function(buttonIndex) {
                    if (buttonIndex == 1) process_cc(card_num, exp_date, cust_id, first_name, last_name, myv_item, card_code, tiers);
                    if (buttonIndex == 2) {
                        Ext.getCmp('PaymentForm').setValues({
                            card_num: '',
                            card_code: '',
                            exp_date: '',
                            first_name: '',
                            last_name: '',
                            tiers: ''
                        });
                    }
                }, 'Upgrade to PRO', 'yes,cancel');
        }
        else {
            var msg = new Ext.MessageBox({
                width: 300,
                floating: 1,
                margin: '80 0 0 0'
            });
            msg.show({
                title: 'Upgrade to Pro',
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
                        process_cc(card_num, exp_date, cust_id, first_name, last_name, myv_item, card_code, tiers);
                    } else if (response == 'cancel') {
                        Ext.getCmp('PaymentForm').setValues({
                            card_num: '',
                            card_code: '',
                            exp_date: '',
                            first_name: '',
                            last_name: ''
                        });
                    }
                }
            });
        }
    }
};

var clear_history_btn = {
    xtype: 'button',
    flex: 0,
    text: 'Clear',
    margin: '10px',
    id: 'clear_history_btn',
    handler: function() {
        glb_video_history = new Array();
        account.set("video_history", glb_video_history);
        localStorage.setItem('video_history', '');
        account.setDirty();
        store.sync();
        account = store.first();
        list_videos_obj.removeAll();
        myvAlert('History has been cleared');
    }
};


var userlist_top_buttons = [
    user_top_back_btn, {
        xtype: 'spacer'
    },
   user_top_seach_btn, user_top_channelcollection_btn, clear_history_btn

];

var userlist_top_buttons2 = [
    user_top_back_btn2, {
        xtype: 'spacer'
    }
];

var userlist_top_buttons3 = [
    user_top_back_btn3, {
        xtype: 'spacer'
    }
];

var userlist_top_buttons4 = [
    user_top_back_btn4, {
        xtype: 'spacer'
    }
];


var msg_top_buttons = [
    user_top_back_btn_msg, {
        xtype: 'spacer'
    },

    {
        xtype: 'button',
        text: 'Clear',
        pressedCls: '',
        handler: function() {
            clear_msg(glb_last_chat_id);
        }
    }

];

var msgList_top_buttons = [
    user_top_back_btn_msgList, {
        xtype: 'spacer'
    }
];

var collect_top_buttons = [{
    xtype: 'button',
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    handler: function() {
        //getVideo(glb_current_video, glb_video_type, true);
        onBackKeyDown();
    }
}, {
    xtype: 'spacer'
}, {
    xtype: 'button',
    text: 'Logout',
    pressedCls: '',
    handler: function() {
        logout();
    }
}];

var edit_collect_top_buttons = [{
    xtype: 'button',
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    handler: function() {
      onBackKeyDown_col();
    }
}, {
    xtype: 'spacer'
}, {
    xtype: 'button',
    text: 'Logout',
    pressedCls: '',
    handler: function() {
        logout();
    }
}];

var payment_top_buttons = [{
    xtype: 'button',
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    handler: function() {
        onBackKeyDown();
    }
}, {
    xtype: 'spacer'
}, {
    xtype: 'button',
    text: 'Logout',
    pressedCls: '',
    handler: function() {
        logout();
    }
}];


var user_menu_top_buttons = [{
    xtype: 'button',
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    //style: 'visibility:hidden',
    handler: function() {
        onBackKeyDown();
    }
}, {
    xtype: 'spacer'
}

, {
    xtype: 'button',
    hidden: true,
    text: 'Logout',
    pressedCls: '',
    handler: function() {
        logout();
    }
}

];

var general_top_buttons = [{
    xtype: 'button',
    iconMask: true,
    iconCls: 'arrow-left2',
    pressedCls: '',
    handler: function() {
        onBackKeyDown();
    }
}];

var user_contacts_follow_btn = {
    id: 'user_contacts_follow_btn',
    xtype: 'button',
    pressedCls: '',
    text: '',
    hidden: true,
    handler: function() {

    }
};