from src.model import ListNode


def delete_node(node: ListNode):
    node.val = node.next.val
    node.next = node.next.next