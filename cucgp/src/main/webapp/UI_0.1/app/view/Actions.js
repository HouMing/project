Ext.define('Cucgp.view.Actions', {
  extend : 'Ext.panel.Panel',
  requires : ['Cucgp.view.Action'],
  id : 'idActions',
  alias : 'widget.Actions',
  store : 'Actions',
  height : '100%',
  autoShow : true,
  layout : {
    type : 'vbox',
    align : 'left'
  },
  items : [],
  title : '功能区',
  initComponent : function () {
    var me = this;
    this.store = Ext.create('Cucgp.store.Actions'); 
    if (debug) {
      console.log('Cucgp.view.actions init ...');
    }
    me.callParent(arguments);
  },
  myInit : function() {
    this.store.addListener('load',function () {
      this.store.each(function(record, idx) {
        actions = Ext.getCmp('idActions');
        var tmp_but = Ext.widget('Action', {action : record.data});
        tmp_but.initComponent();
        actions.insert(tmp_but);
      });
    }, this);
  }
});  
