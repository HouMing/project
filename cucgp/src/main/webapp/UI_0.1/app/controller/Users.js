error/0
Ext.define('Cucgp.controller.Users', {
  extend : 'Ext.app.Controller',
  id : 'idUsers',
  stores : [ 'users' ],
  model : ['user' ],
  views : [ 'Cucgp.view.Users' ],
  init : function() {
    var me = this;
    page = Ext.widget('users', {
      title : this.action.description,
      store : this.getStore('users')
    });
    Cucgp.view.main.insert(page);
    me.callParent(arguments); 
  }
});
