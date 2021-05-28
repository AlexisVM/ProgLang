# Steganography for copyrighting

This proyect has the purpose of helping copyrighting laws by applying techniques to hide information at plain sight.

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

- A **jpg image**. The name of the image + jpg extension file. ex: "image.jpg"
- The **route** where the image is located. ex: "C:\Users\user\Documents\proyect"
- a **message** to encrypt

The steganography program lets you encrypt and decrypt an image, with both funcionalities.

## Steps

**To Encrypt**
1. Open the file "stegjava.jar (or download it if it's not).
2. On te left side is the encryption menu, enter the message to hide, the image route and the image name, finally press "hide".
3. The result should be an image named "steg1.jpg".

**To Decrypt**
1. Open the file "stegjava.jar (or download it if it's not).
2. On the right side is the decryption menu, insert the encrypted image name, the route and click "recover".
3. The result should be the recovered message.

On both cases the parallel and sequential execution times will be on the bottom.

