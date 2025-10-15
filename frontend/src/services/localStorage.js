// Wrapper for localStorage that works with Electron

class LocalStorageService {
  constructor() {
    this.isElectron = window.electron?.isElectron || false;
  }

  async get(key, defaultValue = null) {
    if (this.isElectron) {
      return await window.electron.store.get(key) || defaultValue;
    }
    const value = localStorage.getItem(key);
    return value ? JSON.parse(value) : defaultValue;
  }

  async set(key, value) {
    if (this.isElectron) {
      return await window.electron.store.set(key, value);
    }
    localStorage.setItem(key, JSON.stringify(value));
  }

  async remove(key) {
    if (this.isElectron) {
      return await window.electron.store.delete(key);
    }
    localStorage.removeItem(key);
  }

  async clear() {
    if (this.isElectron) {
      // Electron store doesn't have clear, would need to implement
      console.warn('Clear not implemented for Electron store');
    } else {
      localStorage.clear();
    }
  }
}

export default new LocalStorageService();
