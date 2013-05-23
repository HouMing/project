Ext.define('Cucgp.view.Page', {  
  extend : 'Ext.panel.Panel',
  alias : 'widget.Page',  
  layout : 'fit',
  closable : true,
  html : 'none',
  description : 'description',
  initComponent : function() {  
    var me = this;  
    this.title = this.description;
    if (debug) {
      console.log('Cucgp.view.Page init ...');
    }
    me.callParent(arguments);  
  }
});
