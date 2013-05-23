/*
 * File: app/view/Students.js
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

Ext.define('Cucgp.view.Students', {
    extend: 'Ext.grid.Panel',

    frame: true,
    height: 250,
    id: 'idStudents',
    width: 800,
    title: '学生信息管理',
    store: 'Students',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            tbar: [
                {
                    text: '添加学生',
                    iconCls: 'employee-add',
                    handler: function() {
                                Ext.getCmp('idStudents').plugins[0].cancelEdit();
                                // Create a record instance through the ModelManager
                                var r = Cucgp.model.Student.create();
                                Ext.getStore('Students').insert(0, r);
                                Ext.getCmp('idStudents').plugins[0].startEdit(0, 0);
                            }
                },
                {
                    text: '删除学生',
                    iconCls: 'employee-remove',
                    handler: function() {
                                var sm = Ext.getCmp('idStudents').getSelectionModel();
                                Ext.getCmp('idStudents').plugins[0].cancelEdit();
                                Ext.getStore('Students').remove(sm.getSelection());
                                sm.select(0);
                            }
                }
            ],
            columns: [
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'userId',
                    text: '用户ID',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'userName',
                    text: '学号',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'password',
                    text: '密码',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'studentName',
                    text: '姓名',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'telephone',
                    text: '电话',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'email',
                    text: '邮箱',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'weibo',
                    text: '微博',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'introduction',
                    text: '个人介绍',
                    editor: {
                        xtype: 'textareafield'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'classroomName',
                    text: '班级',
                    editor: {
                        xtype: 'combobox'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'departmentName',
                    text: '院系',
                    editor: {
                        xtype: 'combobox'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'groupId',
                    text: '分组',
                    editor: {
                        xtype: 'textareafield'
                    }
                }
            ],
            plugins: [
                Ext.create('Ext.grid.plugin.RowEditing', {
                    pluginId: 'studentRow',
                    listeners: {
                        edit: {
                            fn: me.onRowEditingEdit,
                            scope: me
                        }
                    }
                })
            ]
        });

        me.callParent(arguments);
    },

    onRowEditingEdit: function(editor, context, eOpts) {
        Ext.getStore('Students').sync();
        Ext.getStore('Students').load();
    }

});