rowEditing = Ext.create('Ext.grid.plugin.RowEditing',
{
  clicksToEdit : 1 ,
  listeners : {
    cancelEdit: function(rowEditing, context) {
      // Canceling editing of a locally added, unsaved record: remove it
      if (context.record.phantom) {
        Cucgp.app.getStore('users').remove(context.record);
      }
    },
    edit : function(editor, e, opt) {
      console.log(editor);
      console.log(e);
      console.log(opt);
    }
  }
});

Ext.define('Cucgp.view.Users', {
  extend : 'Ext.grid.GridPanel',
  alias : 'widget.Users',
  models : ['Cucgp.model.User'],
  store : 'Users',
  height : '100%',
  action : null,
  closable : true,
  columns : [
    { text : 'userId', dataIndex : 'userId' },
    { text : 'userName', dataIndex : 'userName', flex : 1 , editor : { xtype : 'textfield', allowBlank : false}},
    { text : 'password', dataIndex : 'password', editor : { xtype : 'textfield', allowBlank : false}},
    { text : 'userHome', dataIndex : 'userHome'}
    ],
    selType : 'rowmodel',
    plugins : [ rowEditing ],
    dockedItems : [
      {
        xtype : 'toolbar',
        items : [ {
          text : '新建用户',
          iconCls : 'icon-add',
          handler : function(){
            Ext.getStore('Users').insert(0, new Cucgp.model.user());
            rowEditing.startEdit(0, 0);
          }
        }, '-', {
          text : '删除用户',
          iconCls : 'icon-delete',
          handler : function(){
            var selection = this.up('gridpanel').getSelectionModel().getSelection()[0];
            if (selection) {
              Ext.MessageBox.confirm('提示','确认删除用户？',function (id) {
                if (id === 'yes') {
                  Ext.getStore('Users').remove(selection);
                  Ext.getStore('Users').sync();
                } else {
                  return ;
                }
              });
            }
          }
          }] } ],
          layout : {
            type : 'vbox',
            align : 'left'
          },
          initComponent : function () {
            this.store = Ext.create('Cucgp.store.Users');
            if (debug) {
              console.log('Cucgp.view.Users init ...');
            }
            this.callParent(arguments);
          }
        });
