import org.junit.Test;

import java.util.List;



public class SearchPostingsListTest {

    private Integer n;

    @Test
    public void setJumpOrder1() {
        n = 5;

        test(n);
    }

    @Test
    public void setJumpOrder2() {
        n = 20;

        test(n);
    }

    @Test
    public void setJumpOrder3() {
        n = 50;

        test(n);
    }

    private void test(Integer n) {
        final PostingListNode<Integer> expected = NodeUtil.createPostingList(n);
        final PostingListNode<Integer> input = NodeUtil.createPostingList(n);
        List<Integer> shuffle = StreamUtil.shuffle(StreamUtil.sequence(n));
        for (int i = 0; i < n; i++) {
            expected.get(shuffle.get(i)).jump = expected.get(shuffle.get((i + 1) % n));
            input.get(shuffle.get(i)).jump = input.get(shuffle.get((i + 1) % n));
            expected.get(shuffle.get(i)).data = shuffle.get((i + 1) % n);
            input.get(i).data = -1;
        }
        AssertUtils.assertSameListPosting(expected, input);
    }
}