error/0
Ext.define('Cucgp.controller.actions', {
  id : 'actions',
  extend : 'Ext.app.Controller',
  stores : ['actions'],
  views : [ 'Cucgp.view.actions' ],
  init : function() {
    alert('ddd');
    var me = this;  
    MVC.s.actions = store = this.getStore('actions');
    store.addListener('load',function () {
      this.each( function(record, idx) {
        actions = Ext.getCmp('idActions');
        var tmp_but = Ext.widget('action', {action : record.data});
        tmp_but.initComponent();
        actions.insert(tmp_but);
      });
    }, store);
    if (debug) {
      console.log('Cucgp.controller.actions init ...');
    }
    MVC.c.actions = me;
    me.callParent(arguments);
  }
});
