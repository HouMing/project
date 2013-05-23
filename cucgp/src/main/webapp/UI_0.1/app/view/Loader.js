Ext.define('Cucgp.view.Loader', {  
  extend : 'Ext.container.Container',  
  alias : 'widget.Loader',
  id : 'idLoader',
  requires : ['Cucgp.view.Actions', 'Cucgp.view.Main', 'Cucgp.view.Action'],
  isClosed : false,
  width: 1320,
  height: 720,
  layout : {
    type : 'border'
  },
  listeners : {
    disable: function () {
      console.log('vloader#disable');
    },
    destroy : function () {
      console.log('vloader#destroy');
    }
  },
  initComponent : function() {  
    var me = this;  
   if (debug) {
      console.log('Cucgp.view.Loader init ...');
    }
    me.callParent(arguments);  
  }, 
  myInit : function () {
    var actions = Ext.create('Cucgp.view.Actions', {region: 'west', flex : 1});
    actions.myInit();
    var main = Ext.create('Cucgp.view.Main', {region: 'center', flex : 5});
    main.myInit();
    this.insert(actions);
    this.insert(main);
  }
});
