Ext.define('Cucgp.view.Action', {
  extend : 'Ext.button.Button',  
  alias : 'widget.Action',
  id : null,
  action : null,
  height : '100%',
  layout : 'fit',
  text : null,
  textAlign: 'center',
  width: '100%',
  height : '20',
  margin : '3',
  listeners : {
    click : function (e, eOpts) {
      tmp = Ext.create(this.action.actionUrl, {id : this.action.actionName, action : this.action});
      Ext.getCmp('idMain').add(tmp);
      if (debug) {
        console.debug('Req:' + this.action.actionUrl);
      }
    }
  },
  initComponent : function() {  
    var me = this;
    this.id = this.action.actionName;
    this.text = this.action.description;
    me.callParent(arguments);  
  }
});
