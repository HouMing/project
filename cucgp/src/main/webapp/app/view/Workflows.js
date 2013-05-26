/*
 * File: app/view/Workflows.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Cucgp.view.Workflows', {
    extend: 'Ext.grid.Panel',

    height: 250,
    id: 'idWorkflows',
    width: 400,
    closable: true,
    title: '流程管理',
    titleAlign: 'center',
    store: 'Workflows',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            tbar: [
                {
                    text: '教师申报',
                    handler: function() {
                                      var s = this.up('grid').getSelectionModel().getSelection()[0];
                                      var record = this.up('grid').getStore().getAt(s.index);
                                      record.set("workflowStatus", 0);
                                      record.commit();
                                      this.up('grid').getStore().commitChanges();
                                      this.up('grid').plugins[0].cancelEdit();   
                                   }
                },
                {
                    text: '审核发布',
                    handler: function() {
                                      var s = this.up('grid').getSelectionModel().getSelection()[0];
                                      var record = this.up('grid').getStore().getAt(s.index);
                                      record.set("workflowStatus", 1);
                                      record.commit();
                                      this.up('grid').getStore().commitChanges();
                                      this.up('grid').plugins[0].cancelEdit();   
                                            }
                },
                {
                    text: '学生执行',
                    handler: function() {
                                      var s = this.up('grid').getSelectionModel().getSelection()[0];
                                      var record = this.up('grid').getStore().getAt(s.index);
                                      record.set("workflowStatus", 2);
                                      record.commit();
                                      this.up('grid').getStore().commitChanges();
                                      this.up('grid').plugins[0].cancelEdit();   
                                    }
                },
                {
                    text: '总评分数',
                    handler: function() {
                                      var s = this.up('grid').getSelectionModel().getSelection()[0];
                                      var record = this.up('grid').getStore().getAt(s.index);
                                      record.set("workflowStatus", 3);
                                      record.commit();
                                      this.up('grid').getStore().commitChanges();
                                      this.up('grid').plugins[0].cancelEdit();   
                                    }
                },
                {
                    text: '关闭流程',
                    handler: function() {
                                      var s = this.up('grid').getSelectionModel().getSelection()[0];
                                      var record = this.up('grid').getStore().getAt(s.index);
                                      record.set("workflowStatus", 4);
                                      record.commit();
                                      this.up('grid').getStore().commitChanges();
                                      this.up('grid').plugins[0].cancelEdit();   
                                    }
                }
            ],
            columns: [
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'workflowName',
                    text: '流程名'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value == 0) {
                            return "教师申报";
                        }
                        if (value == 1) {
                            return "审核发布";
                        }
                        if (value == 2) {
                            return "学生执行";
                        }
                        if (value == 3) {
                            return "总评分数";
                        }
                        if (value == 4) {
                            return "关闭流程";
                        }
                    },
                    dataIndex: 'workflowStatus',
                    text: '流程状态'
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'workflowStatus',
                    text: '状态说明'
                }
            ],
            plugins: [
                Ext.create('Ext.grid.plugin.RowEditing', {

                })
            ]
        });

        me.callParent(arguments);
    }

});