input = """dir1
 dir11
 dir12
  file.txt
  file.jpeg
  dir13
   file.txt
dir2
 file.png
dir3
 dir31
  dir32
  dir33
   dir34
 dir35
 dir36
dir4"""

print(input)
print('-' * 10)
stack = []
longest_path = ""

for line in input.split('\n'):
    if '.' not in line:
        # is directory
        level = line.count(' ')
        stack = stack[0:level]
        stack.append(line.strip())
    elif 'jpeg' in line or 'png' in line:
        # is file and an image file
        abs_path = ''
        for dir in stack:
            abs_path += ('/' + dir)
        if len(stack) == 0:
            abs_path = '/'
        if len(abs_path) > len(longest_path):
            longest_path = abs_path


print("RESULT ", longest_path, len(longest_path))