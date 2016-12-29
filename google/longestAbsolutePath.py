# Given a directory input (spaces indicate a new subdirectory and files are the only 
# lines that have a '.' followed by extention), return the longest absolute path
# to an image file (either jpeg or png). In following example, answer is len('/dir1/dir12') = 11

input = """dir1
 dir11
 dir12
  file.txt
  file.jpeg
  dir13
   file.docx
dir2
 file2.txt
dir3
 dir31
  dir32
  dir33
   dir34
    file3.png
 dir35
  file4.png
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
        abs_path = '/' + '/'.join(stack)
        if len(abs_path) > len(longest_path):
            longest_path = abs_path


print("RESULT ", longest_path, len(longest_path))
