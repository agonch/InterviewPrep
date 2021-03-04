import heapq

class Node(object):
    def __init__(self, val: int, idx: int, moving_right: bool):
        self.val = val
        self.idx = idx
        self.moving_right = moving_right

    def __repr__(self):
        return "([%s%i]=%i)" % (('>' if self.moving_right else '<'), self.idx, self.val)

    def __lt__(self, other):
        return self.val < other.val

heap = [Node(2, 0, True), Node(0, 1, False), Node(1, 2, True), Node(4, 3, False), Node(2, 4, True)]
heapq.heapify(heap)
print(heap)
heapq.heappop(heap)
print(heap)