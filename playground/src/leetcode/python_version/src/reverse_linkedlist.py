from src.model import ListNode


def reverse_list(head: ListNode) -> ListNode:
    current = head
    previous = None
    while current is not None:
        next = current.next
        current.next = previous
        previous = current
        current = next
    return previous
