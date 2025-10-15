# MyVidster Desktop Application

A native Windows 10 desktop application for MyVidster.com built with Electron and React.

## Features

### Core Features
- ✅ **Video Browsing** - Browse videos in a desktop-optimized grid layout
- ✅ **Search** - Search for videos across MyVidster
- ✅ **Video Playback** - Watch videos with embedded player
- ✅ **Collections** - Organize videos into custom collections
- ✅ **Bookmarking** - Save videos to your collections using MyVidster API
- ✅ **User Authentication** - Secure login with SHA1 password encryption

### Desktop Features
- ✅ **System Tray** - Minimize to system tray
- ✅ **Keyboard Shortcuts**
  - `Ctrl+Shift+M` - Show/Hide window
  - `Ctrl+N` - New window
  - `Ctrl+Q` - Quit application
  - `F11` - Toggle fullscreen
- ✅ **Native Window Controls** - Minimize, maximize, close
- ✅ **Multiple Windows** - Open multiple app windows
- ✅ **Persistent Storage** - Settings and credentials stored locally
- ✅ **Auto-update ready** - Built with electron-builder for easy updates

## Tech Stack

- **Electron** - Native desktop application framework
- **React 19** - Modern UI library
- **Tailwind CSS** - Utility-first CSS framework
- **Axios** - HTTP client for API requests
- **Crypto-JS** - For SHA1 password encryption
- **Lucide React** - Beautiful icon library

## Project Structure

```
/app/
├── electron/              # Electron main process
│   ├── main.js           # Main electron entry point
│   ├── preload.js        # Preload script for security
│   └── assets/           # App icons and assets
├── frontend/             # React application
│   └── src/
│       ├── components/   # React components
│       ├── services/     # API services
│       ├── App.js        # Main app component
│       └── App.css       # Global styles
└── package.json          # Root package config
```

## Installation & Setup

### Prerequisites
- Node.js 16+ and Yarn package manager
- MyVidster account (register at https://www.myvidster.com/register)

### Development Setup

1. **Install dependencies:**
   ```bash
   cd /app
   yarn install
   cd frontend
   yarn install
   ```

2. **Run in development mode:**
   ```bash
   cd /app
   yarn start
   ```
   This will:
   - Start the React dev server on http://localhost:3000
   - Launch Electron app automatically
   - Enable hot reload for instant updates

### Building for Production

#### Build Windows .exe installer:
```bash
cd /app
yarn build        # Build React app
yarn build:win    # Create Windows installer
```

The installer will be created in `/app/dist/` directory.

#### Build options:
- `yarn build:win` - Windows NSIS installer (.exe)
- Output: `dist/MyVidster Setup 1.0.0.exe`

## API Integration

### MyVidster API Configuration

The app uses your private API key: `72ac41dce00fa5d9d845ef9ed66a4723`

**Available Endpoints:**

1. **Insert Video (Bookmark)**
   ```
   GET http://www.myvidster.com/user/api.php
   Params:
   - action: insert
   - email: your@email.com
   - password: SHA1(your_password)
   - channel_id: collection_id
   - url: encoded_video_url
   - access: (optional) 1=private, 2=adult
   ```

2. **Fetch Video**
   ```
   GET http://www.myvidster.com/user/api.php
   Params:
   - action: fetch
   - email: your@email.com
   - password: SHA1(your_password)
   - video_id: video_id
   ```

3. **Get Video by ID (Mobile API)**
   ```
   GET http://api.myvidster.com/mobile_json2.php
   Params:
   - type: videobyid
   - id: video_id
   ```

### Authentication
- Passwords are encrypted using SHA1 before sending to API
- Credentials are stored securely in Electron Store
- Auto-login on app restart

## Usage

### First Time Setup

1. Launch the application
2. Sign in with your MyVidster account credentials
3. Grant the app necessary permissions

### Navigation

- **Home** - View trending and recent videos
- **Search** - Find specific videos
- **Collections** - Manage your video collections
- **Video Player** - Watch and interact with videos

### Keyboard Shortcuts

Global:
- `Ctrl+Shift+M` - Toggle window visibility
- `Ctrl+Q` - Quit application

Window:
- `Ctrl+N` - New window
- `Ctrl+R` - Reload
- `F11` - Fullscreen
- `Ctrl+W` - Close window

## Security

- **No hardcoded credentials** - All user data stored locally
- **Context isolation** - Renderer process isolated from Node.js
- **Preload script** - Controlled API exposure to renderer
- **HTTPS only** - All API calls use secure connections
- **SHA1 password hashing** - As required by MyVidster API

## Customization

### Changing App Icon
Replace files in `/app/electron/assets/`:
- `icon.png` - App icon (512x512)
- `icon.ico` - Windows icon
- `tray-icon.png` - System tray icon (32x32)

### Modifying Colors/Theme
Edit `/app/frontend/src/App.css` or update Tailwind config.

### Adding Features
1. Create new component in `/app/frontend/src/components/`
2. Add route in `/app/frontend/src/App.js`
3. Implement API calls in `/app/frontend/src/services/myvidsterApi.js`

## Troubleshooting

### App won't start
- Check Node.js version (16+)
- Delete `node_modules` and reinstall: `yarn install`
- Check console for errors

### Can't login
- Verify MyVidster credentials at https://www.myvidster.com
- Check internet connection
- Ensure API key is valid

### Videos not loading
- Check MyVidster website status
- Verify video ID is correct
- Check browser console for API errors

### Build fails
- Ensure all dependencies are installed
- Run `yarn build` before `yarn build:win`
- Check disk space for installer creation

## Development Notes

### Adding New API Endpoints
1. Add method to `/app/frontend/src/services/myvidsterApi.js`
2. Handle authentication and error cases
3. Parse XML/JSON responses appropriately

### Testing
- Manual testing in development mode
- Test authentication flow
- Verify all routes work correctly
- Test keyboard shortcuts
- Test system tray functionality

## Future Enhancements

Potential features to add:
- [ ] Video download functionality
- [ ] Playlist creation and management
- [ ] Advanced search filters
- [ ] Video sharing to social media
- [ ] Dark/Light theme toggle
- [ ] Video quality selector
- [ ] Offline mode with cached videos
- [ ] Auto-play next video
- [ ] Picture-in-picture mode
- [ ] Chromecast support

## Credits

- Built for personal use with MyVidster.com
- Electron framework by GitHub
- React by Meta
- Icons by Lucide Icons

## License

MIT License - Personal use only

## Support

For MyVidster-related issues:
- Website: https://www.myvidster.com
- Email: help@myvidster.com

For app issues:
- Check this README
- Review console logs
- Check Electron documentation

---

**Note:** This is an unofficial desktop application. Use at your own discretion.
