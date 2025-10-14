Ext.define('Home', {
    extend: 'Ext.data.Model',
    config: {
        fields: ['sortby', 'value']
    }
});

var home_sortby = new Ext.data.Store({
    model: 'Home',
    data: [{
        sortby: 'New',
        value: 'New'
    }, {
        sortby: 'Popular',
        value: 'Popular'
    }, {
        sortby: 'Recent',
        value: 'Recent'
    }]
});

Ext.define('contact_model', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'data',
            type: 'string'
        }, {
            name: 'extra',
            type: 'string'
        }, {
            name: 'lowerc',
            type: 'string'
        }, {
            name: 'phoneNum',
            type: 'string'
        }]
    }
});

var list_contacts_obj = Ext.create("Ext.data.Store", {
    model: 'contact_model',
    pageSize: 3000,
    clearOnPageLoad: true,
    proxy: {
        type: 'jsonp',
        url: ' https://api.myvidster.com/mobile_json2.php',
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        }
    },
    autoLoad: false,
    listeners: {
        load: function (store) {
            'use strict';
            var tmp = store.first();
            var tmp2 = store.getData();

            if (Object.keys(tmp2.all).length == 0) {
                myvAlert('no contacts');

            } else {
                var data = tmp.get('data');
                var extra = tmp.get('extra');
                var lowerc = tmp.get('lowerc');
            }
            clearMask();
        }
    }
});

//Contacts with Accounts
Ext.define('contact_account_model', createDiscoverModel());

var list_contactAccounts_obj = Ext.create("Ext.data.Store", {
    model: 'contact_account_model',
    pageSize: 3000,
    clearOnPageLoad: true,
    proxy: {
        type: 'jsonp',
        url: ' https://api.myvidster.com/mobile_json2.php',
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        }
    },
    autoLoad: false,
    listeners: {
    }
});

Ext.define('User', {
    extend: 'Ext.data.Model',
    config: {
        fields: ['user_options', 'value', 'img', 'leftMargin', 'bottomMargin', 'topMargin', 'fontSize', 'rightImageMargin']
    }
});

var cachebusterForImages = Math.round(new Date().getTime() / 1000);
var cachebusterParameters = '?cb=' + cachebusterForImages;

var iconImages = [];
var imageCount = 0;
var imageLocalPrefix = './resources/images/';
var imageRemotePrefix = 'https://m.myvidster.com/resources/images/';


var iconNames = [
    'Icon_Bookmark.png',
    'Icon_Collections.png',
    'Icon_Queue.png',
    'Icon_Subscription.png',
    'Icon_Discover.png',
    'Icon_Cloud.png',
    'Icon_History.png',
    'Icon_ProMembership.png',
    'Icon_Tutorial.png',
    'Icon_Settings.png',
    'Icon_Following.png',
    'Icon_White.png'
];

var imageIconExists = iconNames.map(function () {
    'use strict';
    return false;
});

checkAllIconImages();
function checkAllIconImages() {
    'use strict';
    iconNames.map(function (icon, index) {
        checkImage(imageLocalPrefix, icon, index, 10);
    });
}
preloadIcons(100, true);
function preloadIcons(count, local) {
    'use strict';
    iconNames.map(function (icon, index) {
        iconImages[index] = new Image();

        if (imageIconExists[index] || local) {
            iconImages[index].src = imageLocalPrefix + icon;

        } else {
            iconImages[index].src = imageRemotePrefix + icon + cachebusterParameters;
        }
    });
}
function getOptionData() {
    return [{
        user_options: 'Bookmarks',
        value: 'Profile',
        img: iconImages[0] ? iconImages[0].src : '',
    }, {
        user_options: 'Collections',
        value: 'User',
        img: iconImages[1] ? iconImages[1].src : '',
    }, {
        user_options: 'Queue',
        img: iconImages[2] ? iconImages[2].src : '',
        value: 'Queue',
    }, {
        user_options: 'Subscription',
        value: 'subscriptions',
        img: iconImages[3] ? iconImages[3].src : '',
    }, {
        user_options: 'Following',
        value: 'subscription_list',
        img: iconImages[10] ? iconImages[10].src : '',
    }, {
        user_options: 'Discover',
        value: 'Discover',
        img: iconImages[4] ? iconImages[4].src : '',
    }, {
        user_options: 'Cloud',
        value: 'Downloads',
        img: iconImages[5] ? iconImages[5].src : '',
    }, {
        user_options: 'History',
        value: 'History_V2',
        img: iconImages[6] ? iconImages[6].src : '',
    }, {
        user_options: 'PRO',
        value: 'pro_member',
        img: iconImages[7] ? iconImages[7].src : '',
    }, {
        user_options: 'Tutorial',
        value: 'Tutorial',
        img: iconImages[8] ? iconImages[8].src : '',
    },
    {
        user_options: 'Account',
        value: 'Options',
        img: iconImages[9] ? iconImages[9].src : '',
    },
    {
        user_options: '',
        value: 'n/a',
        img: iconImages[11] ? iconImages[11].src : '',
    },
    ];
}
function checkImage(localPath, imageName, index, count) {
    'use strict';
    var checkIconImage = new Image();
    checkIconImage.src = localPath + imageName;
    checkIconImage.onload = function () {

        imageIconExists[index] = true;
        imageCount++;

        if (imageCount > count) {
            preloadIcons(count, false);
        }

        user_options.setData(getOptionData());

    };

    checkIconImage.onerror = function () {
        imageIconExists[index] = false;
        imageCount++;

        if (imageCount > count) {
            preloadIcons(count, false);
        }

        user_options.setData(getOptionData());
    };
}

var user_options = new Ext.data.Store({
    model: 'User',
    data: []
});


//for localstorage you need an id field
Ext.define('App', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'id',
            type: 'int'
        }, {
            name: 'lock_screen',
            type: 'int'
        }, {
            name: 'pin_code1',
            type: 'string'
        }, {
            name: 'pin_code2',
            type: 'string'
        }, {
            name: 'alerts_set',
            type: 'int'
        }, {
            name: 'push_set',
            type: 'int'
        }, {
            name: 'show_hide_comments',
            type: 'int'
        }, {
            name: 'play_playlist',
            type: 'int'
        }, {
            name: 'chrome_cast_setting',
            type: 'int'
        }, {
            name: 'default_html5_playback',
            type: 'string'
        }, {
            name: 'receipt',
            type: 'string'
        }, {
            name: 'transID',
            type: 'string'
        }, {
            name: 'tierIAP',
            type: 'string'
        }, {
            name: 'sigIAP',
            type: 'string'
        }, {
            name: 'darkTheme',
            type: 'int'
        }, {
            name: 'videoCall',
            type: 'int'
        },{
            name: 'defaultAccess',
            type: 'int'
        }

        ],
        proxy: {
            type: 'localstorage',
            id: 'myvidster-settings-app'
        }
    }
});
var app_store = Ext.create("Ext.data.Store", {
    model: "App"
});

//for localstorage you need an id field
Ext.define('Account', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'id',
            type: 'int'
        }, {
            name: 'user_id',
            type: 'string'
        }, {
            name: 'disp_name',
            type: 'string'
        }, {
            name: 'email',
            type: 'string'
        }, {
            name: 'phone_number',
            type: 'string'
        }, {
            name: 'pw',
            type: 'string'
        }, {
            name: 'family_filter',
            type: 'string'
        }, {
            name: 'v_orientation_chk',
            type: 'string'
        }, {
            name: 'anc1',
            type: 'int'
        }, {
            name: 'current_video',
            type: 'int'
        }, {
            name: 'current_video_type',
            type: 'string'
        }, {
            name: 'video_history',
            type: 'auto'
        }, {
            name: 'video_history_chk',
            type: 'string'
        }, {
            name: 'HTML5_only',
            type: 'string'
        }, {
            name: 'profile_photo',
            type: 'string'
        }, {
            name: 'filter_by',
            type: 'string'
        }, {
            name: 'unlock',
            type: 'int'
        }],
        proxy: {
            type: 'localstorage',
            id: 'myvidster-settings'
        }
    }
});
Ext.define('home_model', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'video_title',
            type: 'string'
        }, {
            name: 'video_thumbnail',
            type: 'string'
        }, {
            name: 'disp_name',
            type: 'string'
        }, {
            name: 'video_id',
            type: 'string'
        }, {
            name: 'posted_formatted',
            type: 'string'
        }, {
            name: 'mobile_status',
            type: 'string'
        }, {
            name: 'posted_by',
            type: 'string'
        }, {
            name: 'master_id',
            type: 'string'
        }, {
            name: 'bc_profile_photo',
            type: 'string'
        }, {
            name: 'c_profile_photo',
            type: 'string'
        }, {
            name: 'collect_cnt',
            type: 'string'
        }, {
            name: 'comment_cnt',
            type: 'string'
        }, {
            name: 'profile_photo',
            type: 'string'
        }, {
            name: 'posted',
            type: 'string'
        }]
    }
});
Ext.define('msg_model', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'profile_photo',
            type: 'string'
        }, {
            name: 'message',
            type: 'string'
        }, {
            name: 'to_u_id',
            type: 'string'
        }, {
            name: 'user_name',
            type: 'string'
        }, {
            name: 'chat_id',
            type: 'string'
        }, {
            name: 'read',
            type: 'string'
        }, {
            name: 'time',
            type: 'string'
        }, {
            name: 'last_id',
            type: 'string'
        }]
    }
});
Ext.define('msg_model_message', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'from_u_profile_photo',
            type: 'string'
        }, {
            name: 'message',
            type: 'string'
        }, {
            name: 'to_u_profile_photo',
            type: 'string'
        }, {
            name: 'from_u_id',
            type: 'string'
        }, {
            name: 'to_u',
            type: 'string'
        }, {
            name: 'chat_id',
            type: 'string'
        }, {
            name: 'time',
            type: 'string'
        }, {
            name: 'float_u',
            type: 'string'
        }, {
            name: 'block',
            type: 'string'
        }, {
            name: 'blocked_from',
            type: 'string'
        },{
            name: 'video_id',
            type: 'string'
        },{
            name: 'video_thumbnail',
            type: 'string'
        }]
    }
});
Ext.define('video_model', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'list_title',
            type: 'string'
        }, {
            name: 'name',
            type: 'string'
        }, {
            name: 'list_name',
            type: 'string'
        }, {
            name: 'bc_profile_photo',
            type: 'string'
        }, {
            name: 'bc_type',
            type: 'string'
        }, {
            name: 'bc_id',
            type: 'string'
        }, {
            name: 'bc_owner',
            type: 'string'
        }, {
            name: 'listof',
            type: 'string'
        }, {
            name: 'c_user_id',
            type: 'string'
        }, {
            name: 'c_disp_name',
            type: 'string'
        }, {
            name: 'c_profile_photo',
            type: 'string'
        }, {
            name: 'video_thumbnail',
            type: 'string'
        }, {
            name: 'video_id',
            type: 'string'
        }, {
            name: 'master_id',
            type: 'string'
        }, {
            name: 'posted_formatted',
            type: 'string'
        }, {
            name: 'disp_name',
            type: 'string'
        }, {
            name: 'isfollow',
            type: 'string'
        }, {
            name: 'mobile_status',
            type: 'string'
        }, {
            name: 'profile_photo',
            type: 'string'
        }, {
            name: 'storage',
            type: 'string'
        }, {
            name: 'video_title',
            type: 'string'
        }, {
            name: 'login_as',
            type: 'string'
        }, {
            name: 'posted_by',
            type: 'string'
        }, {
            name: 'bc_disp_name',
            type: 'string'
        }, {
            name: 'video_count',
            type: 'string'
        }, {
            name: 'follower_count',
            type: 'string'
        }, {
            name: 'following_count',
            type: 'string'
        }, {
            name: 'desc',
            type: 'string'
        }, {
            name: 'sid',
            type: 'string'
        }, {
            name: 'sub_disp_name',
            type: 'string'
        }, {
            name: 'status',
            type: 'string'
        }, {
            name: 'video_file',
            type: 'string'
        }, {
            name: 'file_size',
            type: 'string'
        }, {
            name: 'blocked',
            type: 'string'
        }
        ]
    }
});
Ext.define('search_model', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'list_title',
            type: 'string'
        }, {
            name: 'name',
            type: 'string'
        }, {
            name: 'list_name',
            type: 'string'
        }, {
            name: 'bc_profile_photo',
            type: 'string'
        }, {
            name: 'bc_type',
            type: 'string'
        }, {
            name: 'bc_id',
            type: 'string'
        }, {
            name: 'listof',
            type: 'string'
        }, {
            name: 'c_user_id',
            type: 'string'
        }, {
            name: 'c_profile_photo',
            type: 'string'
        }, {
            name: 'video_thumbnail',
            type: 'string'
        }, {
            name: 'video_id',
            type: 'string'
        }, {
            name: 'posted_formatted',
            type: 'string'
        }, {
            name: 'disp_name',
            type: 'string'
        }, {
            name: 'mobile_status',
            type: 'string'
        }, {
            name: 'master_id',
            type: 'string'
        }, {
            name: 'isfollow',
            type: 'string'
        }, {
            name: 'source',
            type: 'string'
        }, {
            name: 'videobyurl',
            type: 'string'
        }, {
            name: 'video_title',
            type: 'string'
        }, {
            name: 'user_thumbnail',
            type: 'string'
        }, {
            name: 'user_id',
            type: 'string'
        }, {
            name: 'profile_photo',
            type: 'string'
        }, {
            name: 'desc',
            type: 'string'
        }, {
            name: 'id',
            type: 'string'
        }, {
            name: 'posted_by',
            type: 'string'
        }, {
            name: 'storage',
            type: 'string'
        }, {
            name: 'blocked',
            type: 'string'
        }]
    }
});
var search_obj = Ext.create("Ext.data.Store", {
    model: 'search_model',
    pageSize: 9,
    proxy: {
        type: 'jsonp',
        url: "https://api.myvidster.com/web_search.php",
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'
        }
    },
    autoLoad: false,
    listeners: {
        load: function (store) {
            'use strict';
            var tmp = store.first();
            var tmp2 = store.getData();
            var page = glb_current_list['page'];
            Ext.getCmp('search_var').setScrollable(true);
            if (page == 1) {

                glb_search_page = 2;
            } else {
                glb_search_page += 1;
            }
            var tmp_len = Object.keys(tmp2.all).length;
            if (tmp_len > 0) {
                if (typeof device != 'undefined') {
                    cordova.plugins.Keyboard.close();
                }
            }
            if (page == 1 && tmp_len == 0) {
                myvAlert('No results found');
            }
        }
    }
});

Ext.define('dummy_model', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'dummy',
            type: 'string'
        }]
    }
});
var dummy_obj = Ext.create("Ext.data.Store", {
    model: 'dummy_model',
    pageSize: 9,
    proxy: {
        type: 'jsonp',
        url: "https://api.myvidster.com/moblie_search.php",
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'
        }
    },
    autoLoad: false,
    listeners: {
        load: function (store) {
            'use strict';
        }
    }
});
//****** chat func
function createSendVideoModel() {
  return {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'user_id',
            type: 'string'
        }, {
            name: 'disp_name',
            type: 'string'
        }, {
            name: 'user_thumbnail',
            type: 'string'
        }
        ]
    }
  };
}
Ext.define('send_video_model', createSendVideoModel());
var send_video_obj = Ext.create("Ext.data.Store", {
    model: 'send_video_model',
    pageSize: 9,
    clearOnPageLoad: true,
    proxy: {
        type: 'jsonp',
        url: "https://api.myvidster.com/moblie_search.php",
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {

        }
    },
    autoLoad: false,
    listeners: {
        refresh: function(store) {
          send_video_obj.getParams().page = glb_page_number;
          glb_page_number += 1;
        },
        load: function(store) {
            'use strict';

        }
    }
});
//****** chat func
var home_obj = Ext.create("Ext.data.Store", {
    model: 'home_model',
    pageSize: 10,
    clearOnPageLoad: true,
    proxy: {
        type: 'jsonp',
        url: ' https://api.myvidster.com/mobile_json2.php',
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'
        }
    },
    autoLoad: false,
    listeners: {
        refresh: function(store) {
            'use strict';
			
           Ext.getCmp('myHome').removeAll();

            var headerItem = {
                html: '<div align="center">' + glb_sortby + ' Bookmarks</div>'
            };
            result = Ext.getCmp('myHome').add(headerItem);
            if (family_filter == 0) {
                var tmp;
                if (glb_filter_by == "all") {
                    tmp = '<div class="filter_opts"><b>all</b></div>';
                    tmp += '<a href="javascript:void(0);" onclick="set_filter_by(\'straight\')">';
                    tmp += '<div class="filter_opts">straight</div>';
                    tmp += '</a>';
                    tmp += '<a href="javascript:void(0);" onclick="set_filter_by(\'gay\')">';
                    tmp += '<div class="filter_opts">gay</div>';
                    tmp += '</a>';
                } else if (glb_filter_by == "straight") {
                    tmp = '<a href="javascript:void(0);" onclick="set_filter_by(\'all\')">';
                    tmp += '<div class="filter_opts">all</div>';
                    tmp += '</a>';
                    tmp += '<div class="filter_opts"><b>straight</b></div>';
                    tmp += '<a href="javascript:void(0);" onclick="set_filter_by(\'gay\')">';
                    tmp += '<div class="filter_opts">gay</div>';
                    tmp += '</a>';
                } else if (glb_filter_by == "gay") {
                    tmp = '<a href="javascript:void(0);" onclick="set_filter_by(\'all\')">';
                    tmp += '<div class="filter_opts">all</div>';
                    tmp += '</a>';
                    tmp += '<a href="javascript:void(0);" onclick="set_filter_by(\'straight\')">';
                    tmp += '<div class="filter_opts">straight</div>';
                    tmp += '</a>';
                    tmp += '<div class="filter_opts"><b>gay</b></div>';
                }
                headerItem = {
                    html: '<div class="filter_area">' + tmp + '</div>'
                };
                result = Ext.getCmp('myHome').add(headerItem);
            }
            if (glb_sortby == 'Recent') {
                Ext.getCmp('home_refresh_var').setItemTpl(recent_listTpl);
            } else {
                Ext.getCmp('home_refresh_var').setItemTpl(popular_listTpl);
            }
        },
        load: function (store) {
            'use strict';
            var tmp = store.last();
            try {
                var last_id = tmp.get('video_id');
                store.getProxy().getExtraParams().last_id = last_id;
            } catch (err) {
                
            }
            clearMask();
            // ios 13.4 beta search scroll hack seb 2020
            Ext.getCmp('search_var').setStore(search_obj);
        }
    }
});

// used for Queue and other views
var list_videos_obj = Ext.create("Ext.data.Store", {
    model: 'video_model',
    pageSize: 10,
    clearOnPageLoad: true,
    proxy: {
        type: 'jsonp',
        url: 'https://api.myvidster.com/mobile_json2.php',
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'
        }
    },
    autoLoad: false,
    listeners: {
        refresh: function (store) {
            'use strict';
            var headerItem;
            if (glb_current_list['type'] == 'Downloads') {
                if (glb_downloading) {
                    var tmp_html = '<div class="download_progress" onclick="abort_download();">';
                    tmp_html += 'Download in progress for <b>' + glb_dl_title + '</b><br>' + glb_loadingStatus + ' click to abort.';
                    tmp_html += '</div>';
                    headerItem = {
                        html: tmp_html
                    };
                    Ext.getCmp('myUserList').removeAll();
                    Ext.getCmp('myUserList').add(headerItem);
                }
            }

        },
        load: function (store) {
            'use strict';
            var headerItem;
            var tmp = store.first();
            var tmp2 = store.getData();
            var type = glb_current_list['type'];

            var id = glb_current_list['id'];
            var q = glb_current_list['q'];
            var start = glb_current_list['start'];
            var tmp_html;

            Ext.getCmp('myUserList').removeAll();

            // Queue View
            if (Object.keys(tmp2.all).length == 0) {
                headerItem = {
                    html: '<div align="center" style="margin-top:5px;">No Videos Found</div>'
                };
                var headerItem2 = {
                    html: '<div align="center" style="margin-top:5px;">Suggested users to follow!</div>'
                };
                Ext.getCmp('userListToolbar').setTitle("MyVidster");

                if (type == 'subscriptions') {
                    Ext.getCmp('myUserList').add(headerItem2);
                } else {
                    Ext.getCmp('myUserList').add(headerItem);
                }

                // user has no uploaded videos in the cloud
                if (type == 'Downloads') {
                    glb_back_data['list_name'] = 'Downloads';
                    glb_back_data['search_type'] = type;
                    glb_back_data['search_id'] = id;
                    Ext.getCmp('user_top_seach_btn').setHidden(false);
                }
            } else {
                if (tmp.get('list_name') !== null) {
                    var list_name_short = tmp.get('list_name').trunc(12).toString();
                }
                //valid login check
                if (account.get('user_id') && (account.get('user_id') != tmp.get('login_as'))) {
                    myvAlert('Invalid login due to password change, please login with the new password.');
                    logout();
                }
                if (type == 'Downloads') {
                    if (glb_downloading) {
                        tmp_html = '<div class="download_progress" onclick="abort_download();">';
                        tmp_html += 'Download in progress for <b>' + glb_dl_title + '</b><br>' + glb_loadingStatus + ' click to abort.';
                        tmp_html += '</div>';
                        headerItem = {
                            html: tmp_html
                        };
                    }
                }
                glb_last_list['type'] = type;
                glb_last_list['id'] = id;
                if (tmp.get('bc_profile_photo')) {
                    Ext.getCmp('userListToolbar').setTitle(list_name_short);
                    var rowspan_cnt;
                    var colspan_cnt;
                    if (tmp.get('bc_owner') == account.get('user_id')) {
                        rowspan_cnt = 1;
                        glb_profile_check = 1;
                    } else {
                        rowspan_cnt = 2;
                        glb_profile_check = 2;
                    }
                    tmp_html = '<div class="table_div">';
                    tmp_html += '<table border="0" class="new_table"><tr><td rowspan=' + rowspan_cnt + ' style="text-align:left;padding-left:15px;">';

                    if (type != 'User' && type != 'Channel' && type != 'Collection') {
                        tmp_html += '<img src="' + tmp.get('bc_profile_photo') + '" height="75" width="75" onclick="list_videos(\'User\', \'' + tmp.get('bc_owner') + '\');" style="border-radius: 50%;"></td>';
                    } else {
                        if (tmp.get('bc_owner') != account.get('user_id')) { 
                            var chat_bubble_link = 'https://m.myvidster.com/resources/images/chat_bubble.png'+  cachebusterParameters;
                            if (type == 'User') {
                              tmp_html += '<div style="position:relative;width:75px;height:75px;">'+ 
                              '<img id="ppic" src="' + tmp.get('bc_profile_photo') + '" height="75" width="75" '+ 
                              'style="border-radius:50%;" onclick="msg_a_user(' + tmp.get('bc_owner') + ',\'' 
                              + tmp.get('bc_disp_name') + '\''+', 0' + ')">'+ 
                              '</div></td>';
                              // add chat bubble
                              tmp_html += '<div style="position:absolute;width:75px;height:75px;">'+
                                '<img id="chat" src="'+ chat_bubble_link +'" height="30" width="30" '+ 
                                'style="position:absolute; left:71px;top:54px;z-index:1;"' + 
                              '</div>';
                            } else { 
                              tmp_html += '<div style="position:relative;width:75px;height:75px;">'+ 
                              '<img id="ppic" src="' + tmp.get('bc_profile_photo') + '" height="75" width="75" '+ 
                              'style="border-radius:50%;" onclick="list_videos(\'User\',\''+tmp.get('bc_owner') + '\')" >' + 
                              '</div></td>';
                            }
                        } else {
                            tmp_html += '<div style="position:relative;width:75px;height:75px;"   > <img src="' + tmp.get('bc_profile_photo') + '" height="75" width="75" style="border-radius:50%;"></div></td>';
                        }

                        tmp_html += '<td height="50">' + tmp.get('video_count') + '<br/><span style="color:#808080;" class="dark_mode_txt">Video Count</span></td>';
                        tmp_html += '<td style="border-left:solid 0px;" onclick="search_handler(\'' + 'followers:' + tmp.get('bc_disp_name') + '\');">' + tmp.get('follower_count') + '<br/> <span style="color:#808080;" class="dark_mode_txt">Followers</span></td>';

                        if (type == "User") {
                            tmp_html += '<td style="border-left:solid 0px;" onclick="search_handler(\'' + 'following:' + tmp.get('bc_disp_name') + '\');">' + tmp.get('following_count') + '<br/> <span style="color:#808080;" class="dark_mode_txt">Following</span></td>';
                            colspan_cnt = 4;
                        } else {
                            colspan_cnt = 3;
                        }
                        tmp_html += '</tr>';

                        var blocked_stat = tmp.get('blocked');
                        var blocked_stat_txt = '';
                        var blocked_stat_var = '';
                        var blocked_func = '';

                        if (tmp.get('bc_owner') != account.get('user_id')) {

                            glb_addbtn_fix = 0;

                            tmp_html += '<tr><td colspan=' + colspan_cnt + ' style="font-size:1.2em !important;" align="center">';

                            if (type == 'User') {
                                tmp_html += '<div id="special_1" style="width:100%;float:left;"><div class="follows_2" onclick="follow_user(\'' + glb_current_list['type'] + '\',' + glb_current_list['id'] + ',';
                            } else {
                                tmp_html += '<div id="special_1" style="width:100%;float:left;"><div class="follows" onclick="follow_user(\'' + glb_current_list['type'] + '\',' + glb_current_list['id'] + ',';

                            }


                            if (tmp.get('isfollow') == '1') {

                                if (blocked_stat == '1') {
                                    blocked_stat_txt = 'Unblock';
                                    blocked_stat_var = '555';
                                    blocked_func = '2';
                                } else {
                                    blocked_stat_txt = 'Unfollow';
                                    blocked_stat_var = '1';
                                    blocked_func = '';
                                }

                                if (type == 'User') {
                                    tmp_html += blocked_stat_var + ');">' + blocked_stat_txt + '</div> <div id="spl_for_user" class="follows_3" onclick="more_for_user'+blocked_func+'(' + tmp.get('bc_owner') + ',\'' + tmp.get('bc_disp_name') + '\');">&bull;&bull;&bull;</div> <div id="spl_for_user2" style="clear:both;"></div> </div>';
                                } else {
                                    tmp_html += '1);">Unfollow ' + type + '</div> </div>';
                                }

                            } else {

                                if (blocked_stat == '1') {
                                    blocked_stat_txt = 'Unblock';
                                    blocked_stat_var = '555';
                                    blocked_func = '2';

                                } else {
                                    blocked_stat_txt = 'Follow';
                                    blocked_stat_var = '0';
                                    blocked_func = '';
                                }

                                if (type == 'User') {
                                    tmp_html += blocked_stat_var + ');">' + blocked_stat_txt + '</div> <div id="spl_for_user" class="follows_3" onclick="more_for_user'+blocked_func+'(' + tmp.get('bc_owner') + ',\'' + tmp.get('bc_disp_name') + '\');">&bull;&bull;&bull;</div> <div id="spl_for_user2" style="clear:both;"></div> </div>';
                                } else {
                                    tmp_html += '0);">Follow ' + type + '</div> </div>';
                                }
                            }

                            tmp_html += '</td></tr>';
                        }else{
                            glb_addbtn_fix = 1;
                        }

                        tmp_html += '<tr><td colspan=' + colspan_cnt + '>';
                        tmp_html += '<div style="text-align:left;padding-left:16px;">' + tmp.get('bc_disp_name') + '</div></td></tr></table></div>';

                        headerItem = {
                            html: tmp_html
                        };
                    }

                } else if (type == 'collectors_v2') {
                    Ext.getCmp('userListToolbar').setTitle("MyVidster");
                    headerItem = {
                        html: '<div class="video_thumb"><a href="javascript:void(0);" ' +
                            'onclick="getVideo(\'' + tmp.get('video_id') + '\',\'videobyid\');">' +
                            '<img src="' + tmp.get('video_thumbnail') + '" height="106" width="160" ' +
                            'border="0"></a></div>' + '<div align="center">' + tmp.get('video_title') +
                            '</div><div align="center" style="font-size:0.8em;padding-bottom:5px;">' +
                            'Collected by:</div>',
                    };
                } else {

                    if(tmp.get('list_title') == 'Downloads'){
                        Ext.getCmp('userListToolbar').setTitle('Cloud Videos');
                    }
                    else{
                        Ext.getCmp('userListToolbar').setTitle(tmp.get('list_title'));
                    }

                }
                //sets up Views
                if (type == 'User' || type == 'Collection' || type == 'Channel' || type == 'Queue' || type == 'Downloads' || type == 'Profile' || type == 'subscription_list') {
                    glb_back_data['list_name'] = tmp.get('list_name');

                    glb_back_data['search_type'] = type;
                    glb_back_data['search_id'] = id;

                    Ext.getCmp('user_top_seach_btn').setHidden(false);

                } else {
                    Ext.getCmp('user_top_seach_btn').setHidden(true);

                }

                if (headerItem) Ext.getCmp('myUserList').add(headerItem);
            }

            if (glb_last_panel == 'Video' || glb_last_panel == 'Video2') {

                Ext.Viewport.animateActiveItem("UserList", {

                    type: 'fade',
                });
                if(glb_seb_back == 0){
                    searchviewbackhack();
                    custom_views_arr.push("UserList");
              
                    glb_custom_views_arr_data.push([id, type, start, q]);
                }else{
                    glb_seb_back = 0;
                }

            } else if (glb_last_panel == 'UserList' && glb_current_list['type'] == 'Channel') {

                Ext.Viewport.animateActiveItem("UserList", {
                    type: 'fade',
                });

                if(glb_seb_back == 0){
                    searchviewbackhack();
                    custom_views_arr.push("UserList");
     
                    glb_custom_views_arr_data.push([id, type, start]);
                }else{
                    glb_seb_back = 0;
                }

            } else {

                Ext.Viewport.animateActiveItem("UserList", {
                    type: 'fade',
                });
                if(glb_seb_back == 0){
                    searchviewbackhack();
                    custom_views_arr.push("UserList");
                    glb_custom_views_arr_data.push([id, type, start]);

                }else{
                    glb_seb_back = 0;
                }

            }
            Ext.getCmp('list_videos_var').deselectAll();
            if (type == 'History_V2') {
                Ext.getCmp('clear_history_btn').setHidden(false);
                Ext.getCmp('user_top_seach_btn').setHidden(true);
            } else {
                Ext.getCmp('clear_history_btn').setHidden(true);
            }

            if (type == 'Collection' || type == 'User' || type == 'subscription_list') {
                Ext.getCmp('list_videos_var').setItemTpl(channel_listTpl); //creates list
            } else if (type == 'Downloads') {
                Ext.getCmp('list_videos_var').setItemTpl(download_listTpl);
            } else if (type == 'collectors_v2') {
                Ext.getCmp('list_videos_var').setItemTpl(collectors_v2_listTpl);
            } else {
                Ext.getCmp('list_videos_var').setItemTpl(recent_listTpl);
            }

            //clears mask for initial selection of bookmarks, collections, queue,
            // subscription, following, cloud, history
            // does NOT clear mask for discover, pro membership, tutorial, options
            clearMask();

            if (glb_last_panel == 'Search')
                Ext.getCmp('search_var').deselectAll();
        }
    }
});

var store = Ext.create("Ext.data.Store", {
    model: "Account"
});

// chat func
var list_msgs_obj = Ext.create("Ext.data.Store", {
    model: 'msg_model',
    pageSize: 12,
    clearOnPageLoad: true,
    proxy: {
        type: 'jsonp',
        url: ' https://api.myvidster.com/mobile_json2.php',
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'
        },
    },
    autoLoad: false,
    listeners: {
        refresh: function () {
            //
        },
        load: function (store) {
            var tmp = store.first();
            var tmp2 = store.getData();
            if (Object.keys(tmp2.all).length == 0) {
                if (Ext.Viewport.getActiveItem().id == 'MsgList') {
                    myvAlert("No Messages. Go to a users profile to send a message.");
                }
                var headerItem = {
                    html: '<div align="center" style="margin-top:5px;">No Messages. Go to a users profile to send a message.</div>'
                };
            } else {
                var user_name = tmp.get('user_name');
                var to_u_id = tmp.get('to_u_id');
                var message = tmp.get('message');
                var time = tmp.get('time');
                var read = tmp.get('read');
                var last_chat_id = tmp.get('chat_id');
                var profile_photo = tmp.get('profile_photo');
            }
            clearMask();  // this may not be needed

        }
    }
});

var list_detailed_msgs_obj = Ext.create("Ext.data.Store", {
    model: 'msg_model_message',
    pageSize: 9,
    proxy: {
        type: 'jsonp',
        url: ' https://api.myvidster.com/mobile_json2.php',
		loadingText: false,
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'

        },
    },
    autoLoad: false,
    listeners: {
        refresh: function (store) {

        },
        load: function(store) {
            glb_last_chat_id = "";
            glb_first_chat_id = '';
            var tmp = store.first();

            try {
                var first_id = tmp.get('chat_id');
            } catch (err) {

                if (first_id == null) { } else {
                    myvAlert('Sorry there is an issue with our chat server at the moment');
                }
            }

            glb_first_chat_id = first_id;

            var tmp2 = store.getData();
            if (Object.keys(tmp2.all).length == 0) {


            } else {

                store.each(function (item, i) {

                    var msg1 = myv_url_share(store.getAt(i).get('message'));
                    var video_thumbnail = store.data.items[i].data.video_thumbnail;

                    item.set('message', msg1);
                    //var video_thumbnail = null;
                    
                    // workaround to access video_thumbnail; direct access returns undefined
                    Object.keys(store.data.items[i].raw).map(function(key,index) {
                      if (index == 12) {
                        video_thumbnail = store.data.items[i].raw[key];
                      }
                      
                    });

                    item.set('video_thumbnail', video_thumbnail);

                });


                chat_scroll_btm();

                var tmp_lst = store.last();
                var last_id = tmp_lst.get('chat_id');
                store.getProxy().getExtraParams().last_id = last_id;
                glb_last_chat_id = last_id;
                glb_chat_block = '';

                var blocked = tmp_lst.get('block');


                if (blocked == '1') {
                    glb_chat_block = 'disabled';
                    //myvAlert('You are blocked from messaging this user.');
                }
            }
        }
    }
});

function createDiscoverModel() {
    return {
        extend: 'Ext.data.Model',
        config: {
            fields: [{
                name: 'user_id',
                type: 'string'
            }, {
                name: 'disp_name',
                type: 'string'
            }, {
                name: 'profile_photo',
                type: 'string'
            }, {
                name: 'followers',
                type: 'string'
            }, {
                name: 'followStatus',
                type: 'string'
            }, {
                name: 'followButtonText',
                type: 'string'
            },
            ]
        }
    };
}
// Discover Feature
Ext.define('recommendations_model', createDiscoverModel());

var recommendations_obj = Ext.create("Ext.data.Store", {
    model: 'recommendations_model',
    pageSize: 9,
    proxy: {
        type: 'jsonp',
        url: "https://api.myvidster.com/mobile_json2.php",
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'
        },
    },
    autoLoad: false,
    listeners: {
        load: function (store) {
            var tmp = store.first();
            var tmp2 = store.getData();

            Ext.getCmp('discover_recommendations_var').setItemTpl(discover_recommendations_listTpl)

        }
    }
});

// Discover Feature
Ext.define('disco_model', createDiscoverModel());

var discover_obj = Ext.create("Ext.data.Store", {
    model: 'disco_model',
    pageSize: 9,
    proxy: {
        type: 'jsonp',
        url: "https://api.myvidster.com/mobile_json2.php",
        reader: {
            type: 'json',
            rootProperty: 'items',
            totalProperty: 'total'
        },
        extraParams: {
            last_id: '0'
        },
    },
    autoLoad: false,
    listeners: {
        load: function (store) {
            var tmp = store.first();
            var tmp2 = store.getData();

            Ext.getCmp('discover_discover_var').setItemTpl(discover_recommendations_listTpl)

        }
    }
});

var carousel = new Ext.Carousel({
    items: [
        {
            html: '<p>This is a test</p>',
            cls: 'card'
        },
        {
            html: '<p>This is another part of the test</p>',
            cls: 'card'
        },
        {
            html: '<p>This is the last part of the test</p>',
            cls: 'card'
        }
    ]
});

//User Profile Data
Ext.define('userprofile_model', {
    extend: 'Ext.data.Model',
    config: {
        fields: [{
            name: 'id',
            type: 'int'
        }, {
            name: 'user_id',
            type: 'string'
        }, {
            name: 'disp_name',
            type: 'string'
        }, {
            name: 'email',
            type: 'string'
        }, {
            name: 'phone_number',
            type: 'string'
        }, {
            name: 'pw',
            type: 'string'
        }, {
            name: 'family_filter',
            type: 'string'
        }, {
            name: 'v_orientation_chk',
            type: 'string'
        }, {
            name: 'anc1',
            type: 'int'
        }, {
            name: 'current_video',
            type: 'int'
        }, {
            name: 'current_video_type',
            type: 'string'
        }, {
            name: 'video_history',
            type: 'auto'
        }, {
            name: 'video_history_chk',
            type: 'string'
        }, {
            name: 'HTML5_only',
            type: 'string'
        }, {
            name: 'filter_by',
            type: 'string'
        }, {
            name: 'unlock',
            type: 'int'
        }],
        proxy: {
            type: 'localstorage',
            id: 'UserProfile'
        }
    }
});