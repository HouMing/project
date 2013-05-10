Ext.application({ name: 'Cucgp',
    views: ['Cucgp.view.Index'],
    launch: function() {
      var loginForm = Ext.create('Cucgp.view.LoginForm');
      viewport = Ext.create('Ext.container.Viewport', {
          layout: {
            type: 'vbox',
            align: 'center'
          },
          items: [ 
            {
              xtype:'panel',
              flex: 1,
              border: 0
            }, {
              xtype:'panel',
              flex: 1,
              border: 0
            } 
          ]
      });
      loginForm.flex = 1;
      viewport.insert(1, loginForm);
    }
});
