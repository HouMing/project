error/0
Ext.define('Cucgp.controller.menuItem', {
	extend : 'Ext.app.Controller',
	views : [ 'Cucgp.view.menuItem'],
  controllers : ['Cucgp.controller.menuItem'],
	init : function() {
    
	},
  initCmps: function() {
    var viewport = Ext.getCmp('idViewport');
    viewport.removeAll();
    var mainLoader = Ext.create('Cucgp.view.loader');
    viewport.insert(1, mainLoader);
  }
});
