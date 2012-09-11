DESCRIPTION = "The most basic Gumstix image"

inherit image
IMAGE_FEATURES += "package-management"
IMAGE_EXTRA_INSTALL ?= ""

XSERVER +=  "\
  xmodmap \
  xrdb \
 "

AUDIO_INSTALL = " \
  alsa-utils-aplay \
  alsa-utils-alsactl \
  alsa-utils-alsamixer \
  alsa-utils-amixer \
  alsa-utils-speakertest \
  mplayer2 \
 "


BASE_INSTALL = " \
  ${MACHINE_EXTRA_RRECOMMENDS} \
  ${@base_contains("DISTRO_FEATURES", "bluetooth", "bluez4", "", d)} \
  avahi-systemd avahi-utils \
  base-files \
  base-passwd \
  bash \
  coreutils \
  dbus \
  devmem2 \
  memtester \
  netbase \
  ntp-systemd \
  net-tools \
  polkit \
  iputils \
  openssh-ssh openssh-keygen openssh-scp openssh-sshd-systemd \
  rsyslog-systemd \
  sed \
  shadow tinylogin \
  systemd systemd-compat-units \
  u-boot-mkimage \
  udev \
  udisks udisks-systemd \
  upower \
  update-alternatives-cworth \
  util-linux \
  which \
 "

XFCE_INSTALL = " \
  task-xfce-base \
  gdm-systemd \
  gtk-tweak \
  "

FIRMWARE_INSTALL = " \
  linux-firmware-sd8686 \
  linux-firmware-rtl8192cu \
  linux-firmware-rtl8192ce \
  linux-firmware-rtl8192su \
  linux-firmware-wl12xx \
 "
NETWORK_INSTALL = " \
  networkmanager \
  networkmanager-tests \
  rfkill \
  ${@base_contains("DISTRO_FEATURES", "wifi", "iw wpa-supplicant", "", d)} \
 "

TOOLS_INSTALL = " \
  bzip2 \
  cpufrequtils \
  dosfstools \
  e2fsprogs \
  evtest \
  findutils \
  grep \
  gzip \
  htop \
  nano \
  sudo \
  systemd-analyze \
  tar \
  vim \
  wget \
  zip \
 "
IMAGE_INSTALL += " \
  ${BASE_INSTALL} \
  ${AUDIO_INSTALL} \
  ${FIRMWARE_INSTALL} \
  ${NETWORK_INSTALL} \
  ${ROOTFS_PKGMANAGE} \
  ${TOOLS_INSTALL} \
  ${XFCE_INSTALL} \
  ${XSERVER} \
 "

# this section removes remnants of legacy sysvinit support
# for packages installed above
IMAGE_FILE_BLACKLIST += " \
                        /etc/init.d/NetworkManager \
                        /etc/init.d/avahi-daemon \
                        /etc/init.d/dbus-1 \
                        /etc/init.d/dnsmasq \
                        /etc/init.d/networking \
                        /etc/init.d/ntpd \
                        /etc/init.d/sshd \
                        /etc/init.d/udev \
                        /etc/init.d/udev-cache \
			/usr/share/xsessions/gnome.desktop \
			/usr/share/gdm/autostart/LoginWindow/at-spi-registryd-wrapper.desktop \
			/usr/share/gdm/autostart/LoginWindow/gnome-mag.desktop \
			/usr/share/gdm/autostart/LoginWindow/gnome-power-manager.desktop \
			/usr/share/gdm/autostart/LoginWindow/gnome-settings-daemon.desktop \
			/usr/share/gdm/autostart/LoginWindow/gok.desktop \
			/usr/share/gdm/autostart/LoginWindow/libcanberra-ready-sound.desktop \
			/usr/share/gdm/autostart/LoginWindow/metacity.desktop \
			/usr/share/gdm/autostart/LoginWindow/orca-screen-reader.desktop \
                       "

remove_blacklist_files() {
	for i in ${IMAGE_FILE_BLACKLIST}; do
		rm -rf ${IMAGE_ROOTFS}$i
	done

}

ROOTFS_POSTPROCESS_COMMAND =+ "remove_blacklist_files ; "

