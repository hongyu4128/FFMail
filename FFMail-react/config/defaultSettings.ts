import { Settings as ProSettings } from '@ant-design/pro-layout';

export type DefaultSettings = ProSettings & {
  pwa: boolean;
};

const proSettings: DefaultSettings = {
  navTheme: 'light',
  // 拂晓蓝
  primaryColor: '#1890ff',
  layout: 'side',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  colorWeak: false,
  menu: {
    locale: true,
  },
  title: 'FFMail',
  pwa: false,
  iconfontUrl: '',
};

// export type { DefaultSettings };

export default proSettings;
