# Steganography for Copyright

This proJect has the purpose of helping copyrighting laws by applying techniques to hide information at plain sight.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

## How to run

This repo also includes the .jar file, the one needed to run the proyect without compiling the repo in java. The jar file needs some information:

- A **jpg image**
- A **message** to encrypt

The steganography program lets you hide and recover a message from an image

## Steps

**Hide message**
1. Open the file "stegjava.jar (or download it if it's not).
2. On the left side is the Hiding Menu, enter the message to hide, select the image to use and click ”Hide”.
3. The result should be an image named "steg1.jpg".

**Recover message**
1. Open the file "stegjava.jar (or download it if it's not).
2. On the right side is the Recovery Menu, insert the modified image and click ”Recover”.
3. The result should be the recovered message.

On both cases the parallel and sequential execution times will be on the bottom.
