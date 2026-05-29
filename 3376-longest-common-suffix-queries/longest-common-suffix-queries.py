class TrieNode:
    def __init__(self):
        self.children = {}
        self.idx = -1
        self.length = float('inf')


class Solution:
    def stringIndices(self, wordsContainer, wordsQuery):

        root = TrieNode()

        def update(node, idx, length):
            if length < node.length:
                node.length = length
                node.idx = idx
            elif length == node.length and idx < node.idx:
                node.idx = idx

        # Build Trie
        for idx, word in enumerate(wordsContainer):

            node = root
            update(node, idx, len(word))

            for ch in reversed(word):

                if ch not in node.children:
                    node.children[ch] = TrieNode()

                node = node.children[ch]
                update(node, idx, len(word))

        ans = []

        # Process Queries
        for query in wordsQuery:

            node = root

            for ch in reversed(query):

                if ch not in node.children:
                    break

                node = node.children[ch]

            ans.append(node.idx)

        return ans