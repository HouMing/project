DELETE FROM cucgp.`user` WHERE user_id = 0;
DELETE FROM cucgp.`group` WHERE group_id = 0;
DELETE FROM cucgp.`workflow` WHERE workflow_id = 0;
DELETE FROM cucgp.`action` WHERE action_id = 0;
DELETE FROM cucgp.`role` WHERE role_id = 0;
DELETE FROM cucgp.`department` WHERE department_name not in ("");