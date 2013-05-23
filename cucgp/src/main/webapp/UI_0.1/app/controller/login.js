error/0
Ext.define('Cucgp.controller.login', {
  extend : 'Ext.app.Controller',
  init : function() {
    me = this;
    if (debug) {
      console.log('Cucgp.controller.login init ...');
    }
    me.callParent(arguments);  
  }
});
