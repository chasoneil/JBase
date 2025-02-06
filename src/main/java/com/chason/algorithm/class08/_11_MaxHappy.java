package com.chason.algorithm.class08;

/**
 * 一个公司，有严格的上下级关系，即一个员工只能有一个上级，一个上级可以有多个员工
 * 公司开办年会，你可以选择邀请任意员工到场参加，每个员工有一个happy值
 * 如果你给A员工发了请柬，那么他的下级都不会来，他的上级也不回来
 * 在不违反上述原则的情况下，怎么让年会的快乐值最大。
 *
 * 演变成一个多叉树 上下级关系其实就是多叉树，每个员工就是一个节点
 *
 */
public class _11_MaxHappy {

    public static class Employee {
        int happy;
        Employee[] employees;
        public Employee(int happy) {
            this.happy = happy;
        }
    }

    /*
       对于任意节点 E
       如果E来：
       1. 他的每个子节点的不来时候的最大happy + E的happy
       如果E不来：
       1.每个子节点的来或者不来的最大happy选个大的然后求和
       条件：
       1. 这个节点来的最大happy
       2. 这个节点不来的最大happy
     */
    public static int maxHappy1(Employee employee) {
        TreeInfo info = process(employee);
        return Math.max(info.maxHappyCome, info.maxHappyNotCome);
    }

    public static TreeInfo process (Employee employee) {

        if (employee == null) {
            return new TreeInfo(0, 0);
        }

        int maxHappyCome = employee.happy;
        int maxHappyNotCome = 0;

        for (Employee next : employee.employees) {
            TreeInfo info = process(next);
            maxHappyCome += info.maxHappyNotCome;
            maxHappyNotCome += Math.max(info.maxHappyCome, info.maxHappyNotCome);
        }

        return new TreeInfo(maxHappyCome, maxHappyNotCome);
    }

    public static class TreeInfo {

        int maxHappyCome;
        int maxHappyNotCome;

        public TreeInfo (int maxHappyCome, int maxHappyNotCome) {
            this.maxHappyCome = maxHappyCome;
            this.maxHappyNotCome = maxHappyNotCome;
        }
    }

}
