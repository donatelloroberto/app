# MyVidster Desktop - Complete Setup & Build Guide

## Quick Start (Development)

```bash
# 1. Install root dependencies
cd /app
yarn install

# 2. Install frontend dependencies
cd frontend
yarn install

# 3. Go back to root and start the app
cd /app
yarn start
```

The app will open automatically with hot reload enabled!

## Building Windows .exe

### Step 1: Build React App
```bash
cd /app
yarn build
```

This creates an optimized production build in `/app/frontend/build/`

### Step 2: Build Windows Installer
```bash
cd /app
yarn build:win
```

This creates:
- `/app/dist/MyVidster Setup 1.0.0.exe` - NSIS installer for Windows

### Step 3: Distribute
The `.exe` file in `/app/dist/` is your final application installer.

Users can:
1. Download the .exe
2. Run installer
3. Choose installation directory
4. Launch MyVidster Desktop from Start Menu or Desktop

## Icon Setup (Important!)

Before building for production, replace placeholder icons:

1. **App Icon** (`/app/electron/assets/icon.png`)
   - Size: 512x512 pixels
   - Format: PNG with transparency
   - Used for: Application window, taskbar

2. **Windows Icon** (`/app/electron/assets/icon.ico`)
   - Size: 256x256 (multi-resolution ICO)
   - Format: .ico file
   - Used for: Installer, .exe file icon

3. **Tray Icon** (`/app/electron/assets/tray-icon.png`)
   - Size: 32x32 pixels
   - Format: PNG with transparency
   - Used for: System tray icon

### Creating Icons

**Online Tools:**
- https://www.icoconverter.com/ - Convert PNG to ICO
- https://www.favicon-generator.org/ - Generate various sizes

**Example with ImageMagick:**
```bash
# Create ICO from PNG
convert icon.png -define icon:auto-resize=256,128,64,48,32,16 icon.ico

# Resize for tray
convert icon.png -resize 32x32 tray-icon.png
```

## Application Configuration

### Update App Info

Edit `/app/package.json`:

```json
{
  "name": "myvidster-desktop",
  "version": "1.0.0",  // <- Update version here
  "description": "MyVidster Desktop App for Windows 10",
  "build": {
    "appId": "com.myvidster.desktop",  // <- Your unique app ID
    "productName": "MyVidster",  // <- Display name
    // ... other settings
  }
}
```

### API Configuration

Your API key is in `/app/frontend/src/services/myvidsterApi.js`:
```javascript
const API_KEY = '72ac41dce00fa5d9d845ef9ed66a4723';
```

## Testing Before Release

### 1. Test in Development
```bash
yarn start
```
- Verify all features work
- Test authentication
- Try video playback
- Check all navigation

### 2. Test Production Build
```bash
yarn build
yarn build:win
```
- Install the generated .exe
- Test on fresh Windows 10 machine
- Verify no console errors
- Check all features again

### 3. Checklist
- [ ] Login works with MyVidster credentials
- [ ] Videos load and display correctly
- [ ] Search functionality works
- [ ] Collections can be viewed
- [ ] Sidebar navigation works
- [ ] Keyboard shortcuts respond
- [ ] System tray functions properly
- [ ] App closes and reopens correctly
- [ ] Settings persist between sessions

## Deployment Options

### Option 1: Direct Distribution
1. Build the .exe file
2. Upload to file hosting (Google Drive, Dropbox, etc.)
3. Share download link

### Option 2: GitHub Releases
1. Create GitHub repository
2. Tag version: `git tag v1.0.0`
3. Push tags: `git push --tags`
4. Upload .exe to GitHub Releases page

### Option 3: Website Download
1. Host .exe on your website
2. Create download page
3. Add installation instructions

## Advanced Configuration

### Auto-Update Setup

To enable auto-updates, you need:
1. Code signing certificate (for Windows)
2. Update server or GitHub releases
3. Configure electron-builder for updates

Example `package.json` update config:
```json
{
  "build": {
    "publish": [{
      "provider": "github",
      "owner": "your-username",
      "repo": "myvidster-desktop"
    }]
  }
}
```

### Custom URL Scheme

To open myvidster:// links:

Edit `/app/package.json`:
```json
{
  "build": {
    "protocols": [{
      "name": "myvidster",
      "schemes": ["myvidster"]
    }]
  }
}
```

### Portable Version

For portable (no-install) version:
```bash
yarn build:win --portable
```

This creates a portable .exe that doesn't require installation.

## File Structure After Build

```
/app/
├── dist/                          # Build output
│   ├── MyVidster Setup 1.0.0.exe  # Windows installer
│   ├── win-unpacked/              # Unpacked app files
│   └── builder-*.yaml             # Build metadata
├── frontend/build/                # React production build
└── ...
```

## Environment Variables

Create `/app/.env` for custom configuration:

```env
# React app settings
REACT_APP_API_URL=https://www.myvidster.com
REACT_APP_VERSION=1.0.0

# Electron settings
ELECTRON_DEV_MODE=false
```

## Troubleshooting Build Issues

### Issue: "electron-builder" not found
**Solution:**
```bash
cd /app
yarn add -D electron-builder
```

### Issue: Build fails on Windows
**Solution:**
- Ensure you have Windows Build Tools
- Install: `npm install --global windows-build-tools`

### Issue: Icon not showing
**Solution:**
- Verify icon files exist and are valid
- Rebuild: `yarn build:win --force`

### Issue: App crashes on start
**Solution:**
- Check `/app/electron/main.js` for errors
- Test in dev mode first: `yarn start`
- Check Windows Event Viewer for crash logs

### Issue: Missing dependencies
**Solution:**
```bash
# Clean install
rm -rf node_modules
rm -rf frontend/node_modules
yarn install
cd frontend && yarn install
```

## Performance Optimization

### Reduce Bundle Size
1. Enable code splitting
2. Remove unused dependencies
3. Use production React build

### Faster Startup
1. Lazy load components
2. Optimize images
3. Minimize initial API calls

### Better UX
1. Add loading states
2. Implement offline mode
3. Cache API responses

## Security Best Practices

1. **Never commit API keys** to public repos
2. **Use environment variables** for sensitive data
3. **Implement HTTPS only** for API calls
4. **Enable context isolation** in Electron
5. **Regular dependency updates** for security patches

## Maintenance

### Updating Dependencies
```bash
# Check outdated packages
yarn outdated

# Update all dependencies
yarn upgrade-interactive

# Update specific package
yarn upgrade electron@latest
```

### Version Management
Use semantic versioning (SemVer):
- `1.0.0` - Initial release
- `1.0.1` - Bug fixes
- `1.1.0` - New features
- `2.0.0` - Breaking changes

Update in `package.json` and rebuild.

## Support & Resources

### Documentation
- Electron: https://www.electronjs.org/docs
- React: https://react.dev
- Electron Builder: https://www.electron.build

### Community
- Electron Discord
- Stack Overflow
- GitHub Discussions

## Next Steps

After successful build:
1. ✅ Test thoroughly on Windows 10
2. ✅ Create user documentation
3. ✅ Set up feedback mechanism
4. ✅ Plan update strategy
5. ✅ Consider code signing for production

---

**Congratulations!** You've successfully set up MyVidster Desktop. 

For questions or issues, refer to the main README.md or create an issue in your repository.
