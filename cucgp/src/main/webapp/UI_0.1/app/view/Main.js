Ext.define('Cucgp.view.Main', {  
  extend : 'Ext.tab.Panel',
  alias : 'widget.Main',  
  id : 'idMain',
  title : '中国传媒大学毕设平台',
  layout : 'card',
  initComponent : function() {  
    var me = this;  
    if (debug) {
      console.log('Cucgp.view.Main init ...');
    }
    me.callParent(arguments);  
  },
  myInit : function() {
  }
});
