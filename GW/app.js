//<debug>
Ext.Loader.setPath({
    'Ext': 'touch/src',
    'GW': 'app'
});
//</debug>

Ext.application({
    name: 'GW',

    requires: [
        'Ext.MessageBox',
        'Ext.TitleBar',
        'Ext.Ajax'
    ],

    views: [],

    icon: {
        '57': 'resources/icons/Icon.png',
        '72': 'resources/icons/Icon~ipad.png',
        '114': 'resources/icons/Icon@2x.png',
        '144': 'resources/icons/Icon~ipad@2x.png'
    },

    isIconPrecomposed: true,

    startupImage: {
        '320x460': 'resources/startup/320x460.jpg',
        '640x920': 'resources/startup/640x920.png',
        '768x1004': 'resources/startup/768x1004.png',
        '748x1024': 'resources/startup/748x1024.png',
        '1536x2008': 'resources/startup/1536x2008.png',
        '1496x2048': 'resources/startup/1496x2048.png'
    },

    launch: function() {
        // Destroy the #appLoadingIndicator element
        Ext.fly('appLoadingIndicator').destroy();
        
        var showStat = function() {
        	
        	Ext.Ajax.request({
        		url: '/gawi/statJSON.jsp',
        	    success: function(response){
        	        var text = response.responseText;

        	        var r = JSON.parse(text);

        	        var message = r.win + '승 '+
        	        r.even + '무 ' + 
        	        r.lose + '패 승률:' + r.rate;

        	        Ext.getCmp('result').setHtml(message);
        	    }
        		
        	});
        };
        
        var play = function(btn, e) {
        	
        	Ext.Ajax.request({
        		url: '/gawi/queryJSON.jsp',
        		params: {
        			choice: btn.value
        		},
        		success: function(response, opts){
        	        var result = Ext.decode(response.responseText);
        	        var r = result.stat;
        			var message = '---- 당신: ' + result.p1.choice +
        			    ' 컴퓨터: '+ result.p2.choice + ' ----<br />' +
        			    result.judgement + '<br />' +
        				r.win + '승 '+
	        			r.even + '무 ' + 
	        			r.lose + '패 승률:' + r.rate;
        			
        			Ext.getCmp('result').setHtml(message);
        		},
        	    failure: function(response, opts) {
        	        console.log('server-side failure with status code ' + response.status);
        	    }
        	
        	});
        };

        // Initialize the main view
        Ext.Viewport.add(
    		{
                styleHtmlContent: true,
                scrollable: true,

                items: [{
                    docked: 'top',
                    xtype: 'titlebar',
                    title: 'Welcome to 가위, 바위, 보!!'
                },
                {
                	xtype: 'panel',
                	layout: 'hbox',
                	items: [
            	        {
            	        	xtype: 'button',
            	        	text: '가위',
            	        	name: 'choice',
            	        	value: 0,
            	        	flex: 1,
            	        	handler: play
            	        },
            	        {
            	        	xtype: 'button',
            	        	text: '바위',
            	        	name: 'choice',
            	        	value: 1,
            	        	flex: 1,
            	        	handler: play
            	        },
            	        {
            	        	xtype: 'button',
            	        	text: '보',
            	        	name: 'choice',
            	        	value: 2,
            	        	flex: 1,
            	        	handler: play
            	        }
                	]
                },
                {
                	xtype: 'spacer',
                	height: '20px'
                },
                {
                	xtype: 'panel',
                	html: '----',
                	id: 'result',
                	style: 'text-align: center'
                }
                
                ]                    
            }        		
        );
        
        showStat();
    },

    onUpdated: function() {
        Ext.Msg.confirm(
            "Application Update",
            "This application has just successfully been updated to the latest version. Reload now?",
            function(buttonId) {
                if (buttonId === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});


