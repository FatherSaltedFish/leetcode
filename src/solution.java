import java.util.Arrays;
import java.util.HashMap;

public class solution {
    public static void main(String[] args){
        solution main=new solution();
        System.out.println(main.maximumProduct(new int[]{-1, 0, -2, 3, 6, 8, 2, 10, -12}));
    }

//    给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
    //能一遍循环结束就用循环一遍，排序消耗资源
    public int maximumProduct(int[] nums) {
//        Arrays.sort(nums);//排序
//        int n=nums.length;
//        if((nums[n-1]*nums[n-2]*nums[n-3]) >(nums[0]*nums[1]*nums[n-1]))
//            return  nums[n-1]*nums[n-2]*nums[n-3];
//        else
//            return  nums[0]*nums[1]*nums[n-1];

        if (nums.length < 3) {
            return 0;
        }
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


//    给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
//为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
//
//说明：不允许修改给定的链表。
//
//进阶：
//
//你是否可以使用 O(1) 空间解决此题？
    //简单方法用哈希表解决问题，进阶用快慢指针解决问题，如果存在环，快慢指针必定相遇

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        //如果存在环，则快指针不会找到null,反之则会不断循环直到遇到
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            //
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
          val = x;
          next = null;
    }
  }
