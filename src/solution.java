import java.util.*;

public class solution {
    public static void main(String[] args){
        solution main=new solution();
        System.out.println(main.addToArrayForm(new int[]{1,1,0,0},34));
    }

//    628
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


//   142 环路链接2
//    给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
//为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
//说明：不允许修改给定的链表。
//进阶：
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
            //当遇到时，fast跑的路程为slow的两倍 f:2nb s:nb n为f比s多跑的圈数，而b为环的点数
            //随后ptr从起点出发，当而ptr和s相遇时的路程为 ptr:a s:a+nb 因为nb为环，所以当ptr与s相遇时为环的起点
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
    //989. 数组形式的整数加法
//    对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果x = 1231，那么其数组形式为[1,2,3,1]。
//
//    给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
    public List<Integer> addToArrayForm(int[] A, int K) {
        //按照A的长度进行计算，随后再对K进行判断，将K分位加入a
//        ArrayList a=new ArrayList();
//        for(int i=A.length-1;i>=0;i--){
//            a.add((A[i]+K)%10);
//            K=(A[i]+K)/10;
//        }
//        if(K!=0) {
//            while (K != 0)
//            {
//                a.add(K%10);
//                K=K/10;
//            }
//        }
//        Collections.reverse(a);
//        return a;
        //通过K的长度进行判断，微微提高内存使用率
        ArrayList a=new ArrayList();
        int len=A.length-1;
        while(K!=0 || len>=0){
            if(len<0){
                a.add(K%10);
                K=K/10;
            }else {
                a.add((A[len]+K)%10);
                K=(A[len]+K)/10;
                len--;
            }
        }
        Collections.reverse(a);
        return a;
    }



}


