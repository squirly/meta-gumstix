require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "overo|duovero|pepper"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

KERNEL_DEVICETREE_overo = " \
    omap3-overo-storm-tobi.dtb omap3-overo-tobi.dtb \
    omap3-overo-storm-chestnut43.dtb omap3-overo-chestnut43.dtb \
    omap3-overo-storm-alto35.dtb omap3-overo-alto35.dtb \
    omap3-overo-storm-arbor43c.dtb omap3-overo-arbor43c.dtb \
    omap3-overo-storm-gallop43.dtb omap3-overo-gallop43.dtb \
    omap3-overo-storm-palo43.dtb omap3-overo-palo43.dtb \
    omap3-overo-storm-summit.dtb omap3-overo-summit.dtb \
"
KERNEL_DEVICETREE_duovero = "omap4-duovero-parlor.dtb"
KERNEL_DEVICETREE_pepper = "am335x-pepper.dtb"


LINUX_VERSION = "3.17"
LINUX_VERSION_EXTENSION = "-custom"

BOOT_SPLASH ?= "logo_linux_clut224-generic.ppm"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-gumstix-3.17:"

S = "${WORKDIR}/git"

# v3.17 = bfe01a5ba2490f299e1d2d5508cbbbadd897bbe9
SRCREV = "bfe01a5ba2490f299e1d2d5508cbbbadd897bbe9"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;nocheckout=1;branch=linux-3.17.y \
    file://0001-ARM-Gumstix-DuoVero-Bind-vdac-regulator-to-hdmi-node.patch \
    file://0002-ARM-Config-regulators-for-Gumstix-Pepper.patch \
    file://0003-HACK-Invert-the-y-axis-of-the-touchscreen-for-pepper.patch \
    file://0004-Gumstix-Overo-Arbor43C-Device-Tree.patch \    
    file://0005-Add-driver-for-Newhaven-FT5X0X-multitouch-controller.patch \
    file://0006-FT5x0x-Touchscreen-Driver-Updated.patch \
    file://defconfig \
    file://${BOOT_SPLASH} \
"
