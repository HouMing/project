Ext.define('Cucgp.controller.Login', {
	extend : 'Ext.app.Controller',
	init : function() {
    Cucgp.view.viewport.removeAll();
    window.location = '/';
	}
});
