package com.chason.algorithm.class09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些会议要利用会议室，一个会议室同一时间只能开展一个会议
 * 给你每个项目的开始时间和结束时间，安排会议日程（开始时间和结束时间都是整数，且大于0）
 * 要求能开尽可能多的会议
 * 返回开会的场次
 */
public class _02_MaxMeeting {

    /*
    贪心策略：
    每次都选结束时间最早的
    做法：
    将所有的会议按照结束时间排序，选择第一个会议，然后根据会议时间删除整个排序中冲突的会议
    然后再选择排在最前面的会议
     */
    public static int maxMeeting1(Meeting[] meetings) {

        if (meetings == null || meetings.length < 1) {
            return 0;
        }

        Arrays.sort(meetings, new MyComparator());
        int startTime = 0;
        int result = 0;
        for (Meeting meeting : meetings) {
            // 如果当前会议在当前时间之后，可以召开
            if (meeting.start >= startTime) {
                result++;
                // 可以开会的时间变成这场会议的结束时间
                startTime = meeting.end;
            }
        }
        return result;
    }

    public static class MyComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }

    public static class Meeting {
        int start;
        int end;
        public Meeting (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 暴力的方法
    public static int maxMeeting2 (Meeting[] meetings) {
        if (meetings == null || meetings.length < 1) {
            return 0;
        }
        return process(meetings, 0, 0);
    }

    /**
     * @param meetings  剩下可以安排的会议集合
     * @param arranged  已经安排的会议数
     * @param start     当前能安排的会议的开始时间
     * @return  最大安排的会议数
     */
    private static int process (Meeting[] meetings, int arranged, int start) {

        // 没有会议可以安排了
        if (meetings == null || meetings.length < 1) {
            return arranged;
        }

        int max = arranged;
        // 还有会议可以安排,每个会议都首先安排一次试试
        for (int i=0; i<meetings.length; i++) {
            if (meetings[i].start >= start) { // 可以安排
                Meeting[] rest = getRestMeeting(meetings, i);
                max = Math.max(max, process(rest, arranged + 1, meetings[i].end));
            }
        }
        return max;
    }

    // 从meetings 中把index位置的meeting remove, return rest meeting
    private static Meeting[] getRestMeeting(Meeting[] meetings, int index) {
        Meeting[] help = new Meeting[meetings.length - 1];
        int k = 0;
        for (int i=0; i<meetings.length; i++) {
            if (i != index) {
                help[k++] = meetings[i];
            }
        }
        return help;
    }

    // ------------------- 对数器 -----------------------
    public static void main(String[] args) {
        // 最大的会议数
        int maxMeetingTime = 15;
        // 单次会议的最大持续时间
        int maxMeetingDelay = 20;
        int testTime = 10000;

        boolean suc = true;
        for (int i=0; i<testTime; i++) {
            Meeting[] meetings = createRandomMeetings(maxMeetingTime, maxMeetingDelay);
            if (maxMeeting1(meetings) != maxMeeting2(meetings)) {
                suc = false;
                break;
            }
        }

        System.out.println(suc ? "Pass!" : "Failed!");
    }

    private static Meeting[] createRandomMeetings(int maxMeetingTime, int maxMeetingDelay) {
        Meeting[] meetings = new Meeting[(int) (Math.random() * maxMeetingTime) + 1];
        for (int i=0; i<meetings.length; i++) {
            int start = (int) (Math.random() * maxMeetingDelay);
            int end = (int) (Math.random() * maxMeetingDelay) + start;
            // 会议至少要有持续的时间
            if (start == end) {
                end++;
            }
            meetings[i] = new Meeting(start, end);
        }
        return meetings;
    }

}
