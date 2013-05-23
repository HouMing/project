error/0
Ext.define('Cucgp.controller.loader', {
  extend : 'Ext.app.Controller',
  init : function() {
    var me = this; 
    MVC.v.viewport.removeAll();
    MVC.v.loader = loader = Ext.create('Cucgp.view.loader');
    MVC.v.actions = Ext.create('Cucgp.view.actions', {
      region : 'west',
      collapsible : true,
      flex : 1
    });
    MVC.v.main = Ext.create('Cucgp.view.main', {
      region : 'center',
      collapsible : false,
      flex : 5
    });
    MVC.v.viewport.insert(MVC.v.loader);
    MVC.v.loader.insert(MVC.v.actions);
    MVC.v.loader.insert(MVC.v.main);
    if (debug) {
      console.log('Cucgp.controller.loader init ...');
    }
    MVC.c.loader = me;
    me.callParent(arguments);  
  }
});
/*
Ext.require('Cucgp.controller.actions', function () {
  actions = Ext.create('Cucgp.controller.actions');
  actions.init();
});
*/
