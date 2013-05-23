Ext.Loader.setConfig({enabled: true});
debug = 1;
MVC = new Object();
MVC.m = new Object();
MVC.v = new Object();
MVC.s = new Object();
Ext.application({
  name : 'Cucgp',
  views : ['Cucgp.view.Login'],
  launch : function() {
    MVC.app = this;
    MVC.v.viewport = Ext.create('Ext.container.Viewport', {
      id : 'idViewport',
      layout : {
        type : 'border'
      }, 
      items : [ {
        xtype : 'panel',
        border: 0,
        flex : 1,
        region : 'north',
      }, {
        xtype : 'panel',
        border: 0,
        flex : 1,
        region : 'south'
      }, {
        xtype : 'panel',
        border: 0,
        flex : 1,
        region : 'west'
      }, {
        xtype : 'panel',
        border: 0,
        flex : 1,
        region : 'east'
      }, {
        xtype : 'Login',
        flex : 1,
        region : 'center'
        } ]
      });
    }
  });
