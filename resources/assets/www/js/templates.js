var vid_screen_w = 110;
var vid_screen_h = 62;
var vid_screen_pby = 36;
var prof_h_div = 80;
var prof_h = 70;
var prof_h2 = 45;
var glb_device_type3 = ''; 
var list_desc_css = '1em';
var agent_mobile = (navigator.userAgent).toLowerCase().indexOf('mobile') > -1; 
var agent_mobile2 = (navigator.userAgent).toLowerCase().indexOf('ipad') > -1;
if(Ext.os.is.Desktop){ glb_device_type3 = 'desktop'; }
else if(agent_mobile && !agent_mobile2){ glb_device_type3 = 'phone'; } // Ext.os.is.Phone && 
else{ glb_device_type3 = 'tablet'; } // (Ext.os.is.Tablet)
var tablet_x = 1.8;

if(glb_device_type3 == 'tablet'){
	vid_screen_w = vid_screen_w * tablet_x; 
	vid_screen_h = vid_screen_h * tablet_x; 
	vid_screen_pby = vid_screen_pby * tablet_x; 
	prof_h_div = prof_h_div * tablet_x;  
	prof_h = prof_h * tablet_x;
  prof_h2 = prof_h2 * tablet_x;  
  list_desc_css = '1.5em';
}

var popular_listTpl = new Ext.XTemplate([
                                         '<tpl for=".">',
                                         '<div class="posted_video">',
                                         '<table width="100%" cellpadding="0" cellspacing="0">',
                                         '<tr><td valign="top" width="'+vid_screen_w+'">',
                                         '<div class="vidthumbnail" >',
                                         '<img src="{video_thumbnail}" height="'+vid_screen_h+'" width="'+vid_screen_w+'" border="0" alt="thumbnail">',
                                         '</div></td><td valign="top"><div class="viddetails">',
                                         '<div class="list_desc" style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:55vw;font-size:'+list_desc_css+';">',
                                         '<span class="title">{video_title}</span>',
                                         '<tpl if="posted_formatted">',
                                         '<br>',
                                         '<span class="postdate">Posted {posted_formatted}</span>',
                                         '</tpl>',
                                         '<br>',
                                         'by <span class="sub_links">{disp_name}</span>',
                                         '<tpl if="collect_cnt">',
                                         '<br>',
                                         '<span class="collected">collected by {collect_cnt} users | {comment_cnt} comments</span>',
                                         '</tpl>',
                                         //'<p style="margin-top:5px;">Mobile playback: {mobile_status}</p>',
                                         '</div>',
                                         '<tpl if="profile_photo">',
                                         '<div class="profile_thumb_mini" onclick="list_videos(\'User\',\'{posted_by}\',0);">',
                                         '<img src="{profile_photo}" height="'+vid_screen_pby+'" width="'+vid_screen_pby+'" border="0" alt="thumbnail" style="border-radius: 50%;" class="dark_theme_prof">',
                                         '</div>',
                                         '</tpl>',
                                         '</div></td>',
                                         '</tr>',
                                         '</table>',
                                         '</div>',
                                         '</tpl>'
                                         ]);


var recent_listTpl = new Ext.XTemplate([
                                        '<tpl for=".">',
                                        '<div class="posted_video" onclick="save_edits(\'{video_id}_mass_edits\');">',
                                        '<table width="100%" cellpadding="0" cellspacing="0">',
                                        '<tr><td valign="top" width="'+vid_screen_w+'">',
                                        '<div class="vidthumbnail" >',
                                        '<img src="{video_thumbnail}" height="'+vid_screen_h+'" width="'+vid_screen_w+'" border="0" alt="thumbnail">',
                                        '</div></td><td valign="top"><div class="viddetails">',
                                        '<div class="list_desc" style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:55vw;font-size:'+list_desc_css+';">',
                                        '<span class="title">{video_title}</span>',
                                        '<br>',
                                        '<span class="postdate">Posted {posted_formatted}</span>',
                                        '<br>',
                                        '<span class="sub_links">by {disp_name}</span>',
                                        //'<p style="margin-top:5px;">Mobile playback: {mobile_status}</p>',
                                        '</div>',
                                        '<tpl if="profile_photo">',
                                        '<div class="profile_thumb_mini" onclick="list_videos(\'User\',\'{posted_by}\',0);">',
                                        '<img src="{profile_photo}" height="'+vid_screen_pby+'" width="'+vid_screen_pby+'" border="0" alt="thumbnail" style="border-radius: 50%;" class="dark_theme_prof">',
                                        '</div>',
                                        '</tpl>',
                                        '</div></td>',
                                        '</tr>',
                                        '</table>',
                                        '<div class="masseditbox"> <input type="checkbox" class="massedit" id="{video_id}_mass_edits"/> <div style="clear:both;"></div> </div>',
                                        '</div>',
                                        '</tpl>'
                                        ]);

var recent_listTpl_2 = new Ext.XTemplate([
                                          '<tpl for=".">',
                                          '<div class="posted_video" onclick="save_edits(\'{video_id}_mass_edits\');">',
                                          '<table width="100%" cellpadding="0" cellspacing="0">',
                                          '<tr><td valign="top" width="'+vid_screen_w+'">',
                                          '<div class="vidthumbnail" >',
                                          '<img src="{video_thumbnail}" height="'+vid_screen_h+'" width="'+vid_screen_w+'" border="0" alt="thumbnail">',
                                          '</div></td><td valign="top"><div class="viddetails">',
                                          '<div onclick="getVideo(\'{video_id}\',\'videobyid\');" class="list_desc" style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:55vw;font-size:'+list_desc_css+';">',
                                          '<span class="title">{video_title}</span>',
                                          '<br>',
                                          '<span class="postdate">Posted {posted_formatted}</span>',
                                          '<br>',
                                          '<span class="sub_links">by {disp_name}</span>',
                                          //'<p style="margin-top:5px;">Mobile playback: {mobile_status}</p>',
                                          '</div>',
                                          '<tpl if="profile_photo">',
                                          '<div class="profile_thumb_mini" onclick="list_videos(\'User\',\'{posted_by}\',0);">',
                                          '<img src="{profile_photo}" height="'+vid_screen_pby+'" width="'+vid_screen_pby+'" border="0" alt="thumbnail" style="border-radius: 50%;" class="dark_theme_prof">',
                                          '</div>',
                                          '</tpl>',
                                          '</div></td>',
                                          '</tr>',
                                          '</table>',

                                          '<div class="masseditbox"> <input type="checkbox" class="massedit" id="{video_id}_mass_edits"/> <div style="clear:both;"></div> </div>',

                                          '</div>',
                                          '</tpl>'
                                          ]);

var external_listTpl = new Ext.XTemplate([
                                          '<tpl for=".">',
                                          '<div class="posted_video">',
                                          '<table width="100%" cellpadding="0" cellspacing="0">',
                                          '<tr><td valign="top" width="'+vid_screen_w+'">',
                                          '<div class="vidthumbnail" >',
                                          '<img src="{video_thumbnail}" height="'+vid_screen_h+'" width="'+vid_screen_w+'" border="0" alt="thumbnail">',
                                          '</div></td><td valign="top"><div class="viddetails">',
                                          '<div class="list_desc">',
                                          '<span class="title">{video_title}</span>',
                                          '<br>',
                                          '<span class="sub_links">Source: {source}</span>',
                                          '</div>',
                                          '<tpl if="profile_photo">',
                                          '<div class="profile_thumb_mini" onclick="list_videos(\'User\',\'{posted_by}\',0);">',
                                          '<img src="{profile_photo}" height="'+vid_screen_pby+'" width="'+vid_screen_pby+'" border="0" alt="thumbnail" style="border-radius: 50%;" class="dark_theme_prof">',
                                          '</div>',
                                          '</tpl>',
                                          '</div></td>',
                                          '</tr>',
                                          '</table>',
                                          '</div>',
                                          '</tpl>'
                                          ]);

var profiles_listTpl = new Ext.XTemplate([
                                          '<tpl for=".">',
                                          '<div class="posted_video">',
                                          '<table width="100%" cellpadding="0" cellspacing="0">',
                                          '<tr><td valign="top" width="'+vid_screen_w+'">',
                                          '<div class="vidthumbnail" >',
                                          '<img src="{user_thumbnail}" height="'+prof_h+'" width="'+prof_h+'" border="0" alt="thumbnail">',
                                          '</div></td><td valign="top"><div class="viddetails">',
                                          '<div class=" style="font-size:'+list_desc_css+';">',
                                          '<span class="title">{disp_name}</span>',
                                          '</div>',
                                          '</div></td>',
                                          '</tr>',
                                          '</table>',
                                          '</div>',
                                          '</tpl>'
                                          ]);

var channel_listTpl = new Ext.XTemplate(
                                        '<tpl for=".">',
                                        '<div class="posted_video"  onclick="save_edits(\'{id}_mass_edits\');">',
                                        '<table width="100%" cellpadding="0" cellspacing="0">',
                                        '<tr><td valign="top" width="'+vid_screen_w+'">',
                                        '<div class="vidthumbnail" >',
                                        '<img src="{video_thumbnail}" height="'+vid_screen_h+'" width="'+vid_screen_w+'" border="0" alt="thumbnail">',
                                        '</div></td><td valign="top"><div class="viddetails">',
                                        '<div class="list_desc" style="font-size:'+list_desc_css+';">',
                                        
                                        '<tpl if="status==\'2\'">',
                                        '<span class="titlebig" style="color:#999999;"><b>[Muted]</b> {name}</span>',
                                        '<tpl else>',
                                        '<span class="titlebig">{name}</span>',
                                        '</tpl>',
                                        
                                        '<tpl if="desc">',
                                        '<br>',
                                        '<span class="postdate">{desc}</span>',
                                        '</tpl>',
                                        '</div>',
                                        '<tpl if="profile_photo">',
                                        '<div class="profile_thumb_med" onclick="list_videos(\'User\',\'{posted_by}\',0);">',
                                        '<img src="{profile_photo}" height="'+prof_h2+'" width="'+prof_h2+'" border="0" alt="thumbnail" style="border-radius: 50%;" class="dark_theme_prof">',
                                        '</div>',
                                        '</tpl>',
                                        '</div></td>',
                                        '</tr>',
                                        '</table>',

                                        '<div class="masseditbox"> <input type="checkbox" class="massedit" id="{id}_mass_edits"/> <div style="clear:both;"></div> </div>',

                                        '</div>',
                                        '</tpl>',
                                        {
                                        disableFormats: true,
                                        }
                                        );

var collectors_listTpl = new Ext.XTemplate([
                                            '<tpl for=".">',
                                            '<div style="margin-top:5px;margin-bottom:5px;margin-left:auto;margin-right:auto;width:270px;text-align:center;font-size:14px;">Collected by:<br>',
                                            '<tpl for="collectors">',
                                            '<div class="profile_thumb" style="height:'+prof_h_div+'px">',
                                            '<img src="{c_profile_photo}" height="'+prof_h+'" width="'+prof_h+'" border="0" style="border-radius: 50%;" class="dark_theme_prof">',
                                            '</div>',
                                            '</tpl>',
                                            '</div>',
                                            '</tpl>'
                                            ]);

var collectors_v2_listTpl = new Ext.XTemplate([
                                               '<tpl for=".">',
                                               '<div class="posted_video">',
                                               '<table width="100%" cellpadding="0" cellspacing="0">',
                                               '<tr><td valign="top" width="'+vid_screen_w+'">',
                                               '<div class="vidthumbnail" >',
                                               '<img src="{c_profile_photo}" height="'+prof_h+'" width="'+prof_h+'" border="0" alt="thumbnail">',
                                               '</div></td><td valign="top"><div class="viddetails">',
                                               '<div class="list_desc" style="font-size:'+list_desc_css+';">',
                                               '<span class="title">{c_disp_name}</span>',
                                               '</div>',
                                               '</div></td>',
                                               '</tr>',
                                               '</table>',
                                               '</div>',
                                               '</tpl>'
                                               ]);
 

var download_listTpl = new Ext.XTemplate([
                                          '<tpl for=".">',
                                          '<div class="posted_video" onclick="save_edits(\'{video_id}_mass_edits\');" >',
                                          '<table width="100%" cellpadding="0" cellspacing="0">',
                                          '<tr><td valign="top" width="'+vid_screen_w+'">',
                                          '<div class="vidthumbnail">',
                                          '<img src="{video_thumbnail}" height="'+vid_screen_h+'" width="'+vid_screen_w+'" border="0" alt="thumbnail">',
                                          '</div></td><td valign="top"><div class="viddetails">',
                                          '<span class="title">{video_title}</span>',
                                           
                                          ' <tpl if="file_size &gt; 0"> <div style="bottom: 0px; left: 0px; position: absolute;"> <span style="font-size: 12px;color:#4e5965;"> File size: <tpl if="file_size &gt; 999">  { file_size/1000.00 }GB <tpl else> {file_size}MB </tpl>   </span> </div> </tpl> ',
                                          
                                          '<div class="profile_thumb_mini"> Saved in <span class="storage">{storage}</span></div>',
                                          '</div></td>',
                                          '</tr>',
                                          '</table>',

                                          '<div class="masseditbox"> <input type="checkbox" class="massedit" id="{video_id}_mass_edits"/> <div style="clear:both;"></div> </div>',
                                        
                                          '</div>',
                                          '</tpl>'
                                          ]);

var video_pageTpl = new Ext.XTemplate([
                                       '<tpl for=".">',
                                       '<div style="height:{height}px;width:100%;overflow:hidden;background-color:#000;" align="center" onclick="lockOverride();">',
                                       '<iframe width="{width}" height="{height}" src="https://api.myvidster.com/mobile_zoom.php?ov=4416&id={video_id}" frameborder="0"></iframe>',
                                       '</div>',
                                       '<tpl if="banner_api">',
                                       '<div style="background-color:#000;height:60px;padding-bottom:5px;padding-top:5px;" align="center" id="top_banner_{video_id}"></div>',
                                       '</tpl>',
                                       '<div class="video_options">',
                                       '<tpl if="type!=\'videobydownload\'">',
                                       
                                             '<tpl if="isOwner">',
                                             '<a href="javascript:void(0);" onclick="edit_video(\'{video_id}\', 0);"><div class="option_btn"><img src="resources/themes/images/default/pictos/settings6.png" width="25"><div>edit</div></div></a>',
                                             '<tpl else>',
                                             '<a href="javascript:void(0);" onclick="collect_video_handler(\'{video_id}\',\'{video_title_encoded}\',{access});"><div class="option_btn"><img src="resources/themes/images/default/pictos/add.png" width="25"><div>collect</div></div></a>',
                                             '</tpl>',
                                       '<a href="javascript:void(0);" onclick="queue_video_handler(\'{video_id}\',\'{master_id}\',\'{video_title_encoded}\');"><div class="option_btn"><img src="resources/themes/images/default/pictos/star.png" width="25"><div>queue</div></div></a>',
                                       '</tpl>',
									   '{playlist_btn}',
                                       '{download_btn}',
                                       '<tpl if="type!=\'videobydownload\'">',
                                       '<tpl if="isOwner">',
									   '<a href="javascript:void(0);" onclick="video_show_more(\'{video_id}\',\'{master_id}\',\'{ip}\',\'{type_url_encoded}\',\'{video_title_encoded}\', {access}, 1);"><div class="option_btn"><img src="resources/themes/images/default/pictos/more.png" width="25"><div>more</div></div></a>',
								       '<tpl else>',
                                       '<a href="javascript:void(0);" onclick="video_show_more(\'{video_id}\',\'{master_id}\',\'{ip}\',\'{type_url_encoded}\',\'{video_title_encoded}\', {access}, 0);"><div class="option_btn"><img src="resources/themes/images/default/pictos/more.png" width="25"><div>more</div></div></a>',
                                       '</tpl>',
                                       '</tpl>',
                                       '</div>',
                                       '<table width="100%" cellpadding="5" cellspacing="0" border="0">',
                                       '<tr><td>',
                                       '<div class="video_title" id="myv_video_title">',
                                       '<tpl if="watching==1">',
                                       '<span style="weight:bold;color:red;">[Watched]</span> ',
                                       '</tpl>',
                                       '<tpl if="watching==2">',
                                       '<span style="weight:bold;color:green;">[In Queue]</span> ',
                                       '</tpl>',
                                       '{video_title}',
                                       '</div>',
                                       '<div class="collected_title">',
                                       'Collected by<tpl if="collectors_cnt &gt; 1"> {collectors_cnt} users:</tpl><tpl if="collectors_cnt == 1">:</tpl> ',
                                       '<tpl if="collectors_cnt &gt; 4"><a href="javascript:void(0);" onclick="list_videos(\'collectors_v2\',\'{video_id}\',0);">show more</a></tpl>',
                                       '</div>',
                                       '<div class="profile_thumb" style="height: 50px;">',
                                       '<a href="javascript:void(0);" onclick="list_videos(\'User\',\'{user_id}\',0);"><img src="{profile_photo}" height="36" width="36" border="0" style="border-radius: 50%;" class="dark_theme_prof"></a>',
                                       '</div>',
                                       '<tpl for="collectors">',
                                       '<div class="profile_thumb" style="height: 50px;">',
                                       '<a href="javascript:void(0);" onclick="list_videos(\'User\',\'{c_user_id}\',0);"><img src="{c_profile_photo}" height="36" width="36" border="0" style="border-radius: 50%;" class="dark_theme_prof"></a>',
                                       '</div>',
                                       '</tpl>',
                                       '</td></tr>',
                                       '</table>',
                                       '</tpl>'
                                       ]);
                                       
var video_pageTpl_HTML5 = new Ext.XTemplate([
                                             '<tpl for=".">',
                                             '<tpl if="ad_img_url">',
                                             '<div style=\'margin:4px auto;text-align:center;\'><img src="{ad_img_url}" style="width:300px; height:50px; background-color:#333;" onclick="load_webpage(\'{ad_link_url}\',false);"/></div>',
                                             '</tpl>',
                                             '<div style="background-color:#000;" align="center" onclick="lockOverride();" >',
                                             '{embed}',
                                             '</div>',
                                             '<tpl if="banner_api">',
                                             '<div style="background-color:#000;height:60px;padding-bottom:5px;padding-top:5px;" align="center" id="top_banner_{video_id}"></div>',
                                             '</tpl>',
                                             '<div class="video_options">',
                                             '<tpl if="type==\'videobyurl\'">',
                                             '<a href="javascript:void(0);" onclick="collect_video_handler(\'{video_id}\',\'{video_title_encoded}\',{access});"><div class="option_btn"><img src="resources/themes/images/default/pictos/add.png" width="25"><div>collect</div></div></a>',
                                             '</tpl>',
                                             '<tpl if="type!=\'videobyurl\'">',
                                             '<tpl if="type!=\'videobydownload\'">',
                                             
                                             '<tpl if="isOwner">',
                                             '<a href="javascript:void(0);" onclick="edit_video(\'{video_id}\', 0);"><div class="option_btn"><img src="resources/themes/images/default/pictos/settings6.png" width="25"><div>edit</div></div></a>',
                                             '<tpl else>',
                                             '<a href="javascript:void(0);" onclick="collect_video_handler(\'{video_id}\',\'{video_title_encoded}\',{access});"><div class="option_btn"><img src="resources/themes/images/default/pictos/add.png" width="25"><div>collect</div></div></a>',
                                             '</tpl>',
                                             
                                             '<a href="javascript:void(0);" onclick="queue_video_handler(\'{video_id}\',\'{master_id}\',\'{video_title_encoded}\');"><div class="option_btn"><img src="resources/themes/images/default/pictos/star.png" width="25"><div>queue</div></div></a>',
                                             '</tpl>',
                                             '{playlist_btn}',
                                             '{download_btn}',
                                             '<tpl if="type!=\'videobydownload\'">',
                                             '<tpl if="isOwner">',
											 '<a href="javascript:void(0);" onclick="video_show_more(\'{video_id}\',\'{master_id}\',\'{ip}\',\'{type_url_encoded}\',\'{video_title_encoded}\',{access}, 1);"><div class="option_btn"><img src="resources/themes/images/default/pictos/more.png" width="25"><div>more</div></div></a>',
                                             '<tpl else>',
                                             '<a href="javascript:void(0);" onclick="video_show_more(\'{video_id}\',\'{master_id}\',\'{ip}\',\'{type_url_encoded}\',\'{video_title_encoded}\',{access}, 0);"><div class="option_btn"><img src="resources/themes/images/default/pictos/more.png" width="25"><div>more</div></div></a>',
                                             '</tpl>',
                                             '</tpl>',
                                             '</tpl>',
                                             '</div>',
                                             '<table width="100%" cellpadding="5" cellspacing="0" border="0">',
                                             '<tr><td>',
                                             '<div class="video_title" id="myv_video_title">',
                                             '<tpl if="watching==1">',
                                             '<span style="weight:bold;color:red;">[Watched]</span> ',
                                             '</tpl>',
                                             '<tpl if="watching==2">',
                                             '<span style="weight:bold;color:green;">[In Queue]</span> ',
                                             '</tpl>',
                                             '{video_title}',
                                             '</div>',
                                             '<tpl if="profile_photo">',
                                             '<div class="collected_title">',
                                             'Collected by<tpl if="collectors_cnt &gt; 1"> {collectors_cnt} users:</tpl><tpl if="collectors_cnt == 1">:</tpl> ',
                                             '<tpl if="collectors_cnt &gt; 4"><a href="javascript:void(0);" onclick="list_videos(\'collectors_v2\',\'{video_id}\',0);">show more</a></tpl>',
                                             '</div>',
                                             '<div class="profile_thumb" style="height: 50px;">',
                                             '<a href="javascript:void(0);" onclick="list_videos(\'User\',\'{user_id}\',0);"><img src="{profile_photo}" height="36" width="36" border="0" style="border-radius: 50%;" class="dark_theme_prof"></a>',
                                             '</div>',
                                             '</tpl>',
                                             '<tpl for="collectors">',
                                             '<div class="profile_thumb" style="height: 50px;">',
                                             '<a href="javascript:void(0);" onclick="list_videos(\'User\',\'{c_user_id}\',0);"><img src="{c_profile_photo}" height="36" width="36" border="0" style="border-radius: 50%;" class="dark_theme_prof"></a>',
                                             '</div>',
                                             '</tpl>',
                                             '</td></tr>',
                                             '</table>',
                                             '</tpl>'
                                             ]);
var video_comments_pageTpl = new Ext.XTemplate([
                                                '<div class="comment_title" width="100%">Comments</div>',
                                                '<tpl for=".">',
                                                '<div class="details_comment" style="border-left:0px;margin-left:0px;">',
                                                '<table width="100%" cellpadding="5" cellspacing="0" border="0">',
                                                '<tr>',
                                                '<td width="40px" style="padding-right:8px;" valign="top">',
												'<div align="left" id="test" name="test" class="profile_thumb" style="height:50px;margin-left:0px;">',
												'<a href="javascript:void(0);" onclick="profile_handler(\'{c_user_id}\');"><img src="{c_profile_photo}" width="40px" height="40px" style="border-radius: 50%;" class="dark_theme_prof"/></a>',
												'</div>',
												'</td>',
                                                '<td valign="top" style="padding:4px;">',
                                                '<div align="left" id="test" name="test"><b>{name}</b></div>',
                                                '<div align="left" id="test" name="test" style="font-size:.84em;">{message}</div>',
                                                '</td>',
                                                '</tr>',
                                                '</table>',
                                                '</div>',
                                                '</tpl>',
                                                '<div style="height:5px"></div>'
                                             
                                                ]);

// Discover Feature
var discover_recommendations_listTpl = new Ext.XTemplate([
    '<tpl for=".">',
    '<div class="posted_video">',
    '<table width="100%" cellpadding="0" cellspacing="0">',
    '<tr><td valign="top" width="'+vid_screen_w+'">',
    '<div class="vidthumbnail">',
    '<img src="{profile_photo}" height="'+prof_h+'" width="'+prof_h+'" border="0" alt="thumbnail">',
    '</div></td><td valign="top"><div class="viddetails">',
    '<div class="list_desc" style="font-size:'+list_desc_css+';">',
    '<span class="titlebig">{disp_name}</span>',
    '<br>',
    '<br>',
    '<span class="sub_links">{followers} Followers</span>',
    '</div>',
    '</div>',
    '</div></td>',
    '<td align="right">',
    '<div style="width:100%;font-size:13pt;font-weight:bold" ><div '+ 
    'style="color:white;background-color:#00A300;padding:10px 0px;border-radius:8px;width:25vw; text-align:center"'+ 
    ' onclick="followContactWithAccount({user_id})" id={user_id} class="follow_button_target" >{followButtonText}</div> </div>',
    '</td>',
    '</tr>',
    '</table>',
    '</div>',
    '</tpl>'
]);
var send_video_listTpl = new Ext.XTemplate([
    '<tpl for=".">',
    '<div class="posted_video" onclick="sendVideoViaChat({user_id})">',
    '<table width="100%" cellpadding="0" cellspacing="0">',
      '<tr>',
        '<td valign="top" width="'+vid_screen_w+'">',
          '<div>',
            '<div class="vidthumbnail">',
              '<img src="{user_thumbnail}" height="'+prof_h+'" width="'+prof_h+'" border="0" alt="thumbnail">',
            '</div>',
          '</div>',
        '</td>',
        '<td valign="top">',
          '<div class="viddetails">',
            '<div class="list_desc" style="font-size:'+list_desc_css+';">',
              '<span class="titlebig">{disp_name}</span>',
              '<br>',
              '<br>',
            '</div>',
          '</div>',
        '</td>',
      '</tr>',
    '</table>',
    '</div>',
    '</tpl>'
]);
var tutorial_recommendations_listTpl = new Ext.XTemplate([
    '<tpl for=".">',
    '<div class="posted_video">',
    '<table width="100%" cellpadding="0" cellspacing="0">',
    '<tr><td valign="top" width="'+vid_screen_w+'">',
    '<div class="vidthumbnail">',
    '<img src="{profile_photo}" height="'+prof_h+'" width="'+prof_h+'" border="0" alt="thumbnail">',
    '</div></td><td valign="top"><div class="viddetails">',
    '<div class="list_desc">',
    '<span class="titlebig">{disp_name}</span>',
    '<br>',
    '<br>',
    '<span class="sub_links">{followers} Followers</span>',
    '</div>',
    '</div>',
    '</div></td>',
    '</tr>',
    '</table>',
    '</div>',
    '</tpl>'
]);
var contactAccounts_listTpl = new Ext.XTemplate([
    '<tpl for=".">',
    '<div class="posted_video">',
    '<table width="100%" cellpadding="0" cellspacing="0">',
    '<tr><td valign="top" width="'+vid_screen_w+'">',
    '<div class="vidthumbnail">',
    '<img src="{profile_photo}" height="'+prof_h+'" width="'+prof_h+'" border="0" alt="thumbnail">',
    '</div></td><td valign="top"><div class="viddetails">',
    '<div class="list_desc">',
    '<span class="titlebig">{disp_name}</span>',
    '<br>',
    '<br>',
    '<span class="sub_links">{followers} Followers</span>',  
    '</div>',
    '</div>',
    '</div></td>',
    '<td align="right">',
    '<div style="width:100%;font-size:13pt;font-weight:bold" ><div '+ 
    'style="color:white;background-color:#00A300;padding:10px 0px;border-radius:8px;width:25vw; text-align:center"'+ 
    ' onclick="followContactWithAccount({user_id})" id={user_id} class="follow_button_target" >{followButtonText}</div> </div>',
    '</td>',
    '</tr>',
    '</table>',
    '</div>',
    '</tpl>'
]);
var emptyContacts_listTpl = new Ext.XTemplate([
    '<div class="posted_video">',
    '<h1 style="font-size:2em; text-align:center">Sorry, it doesn\'t look like any of your friends are currently using MyVidster. Feel free to invite your friends!</h1>',
    '</div>',
]);
var emptyRecommendations_listTpl = new Ext.XTemplate([
    '<div class="posted_video">',
    '<h1 style="font-size:2em; text-align:center">Sorry, we don\'t have any recommendations for you at this time. Start collecting videos to get recommendations!</h1>',
    '</div>',
]);
var inviteContacts_listTpl = new Ext.XTemplate([
    '<tpl for=".">',
    '<div class="posted_video">',
    '<table width="100%" cellpadding="0" cellspacing="0">',
    '<tr> <td valign="top"><div class="viddetails">',
    '<div class="list_desc">',
    '<div style="font-size:2em;text-align:left;">{data}</div>',
    '<div style="font-size:1.4em;text-align:left;">{extra}</div>',  
    '</div>',
    '</div>',
    '</div></td>',
    '<td align="right">',
    '<div style="width:100%;font-size:13pt;font-weight:bold;" ><div '+ 
    'style="color:white;background-color:#00A300;padding:10px 0px;border-radius:8px;width:25vw; text-align:center"'+ 
    ' onclick="sendSMSInviteToContact({phoneNum})" class="follow_button_target">Invite</div> </div>',
    '</td>',
    '</tr>',
    '</table>',
    '</div>',
    '</tpl>'
]);
