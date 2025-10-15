import axios from 'axios';
import CryptoJS from 'crypto-js';

const API_KEY = '72ac41dce00fa5d9d845ef9ed66a4723';
const API_BASE_URL = 'http://www.myvidster.com';
const MOBILE_API_BASE_URL = 'http://api.myvidster.com';

class MyVidsterAPI {
  constructor() {
    this.email = null;
    this.password = null;
    this.passwordSha1 = null;
    
    // Load saved credentials from electron store
    this.loadCredentials();
  }

  async loadCredentials() {
    if (window.electron) {
      const savedCreds = await window.electron.store.get('credentials');
      if (savedCreds) {
        this.email = savedCreds.email;
        this.passwordSha1 = savedCreds.passwordSha1;
      }
    }
  }

  async saveCredentials(email, password) {
    this.email = email;
    this.password = password;
    this.passwordSha1 = CryptoJS.SHA1(password).toString();
    
    if (window.electron) {
      await window.electron.store.set('credentials', {
        email: this.email,
        passwordSha1: this.passwordSha1
      });
    }
  }

  clearCredentials() {
    this.email = null;
    this.password = null;
    this.passwordSha1 = null;
    
    if (window.electron) {
      window.electron.store.delete('credentials');
    }
  }

  isAuthenticated() {
    return !!(this.email && this.passwordSha1);
  }

  // Get video by ID using mobile API
  async getVideoById(videoId) {
    try {
      const response = await axios.get(
        `${MOBILE_API_BASE_URL}/mobile_json2.php`,
        {
          params: {
            type: 'videobyid',
            id: videoId
          },
          headers: {
            'User-Agent': 'MyVidster-Desktop/1.0'
          }
        }
      );
      return response.data;
    } catch (error) {
      console.error('Error fetching video:', error);
      throw error;
    }
  }

  // Insert/bookmark video to collection
  async insertVideo(videoUrl, channelId, options = {}) {
    if (!this.isAuthenticated()) {
      throw new Error('User not authenticated');
    }

    const params = {
      action: 'insert',
      email: this.email,
      password: this.passwordSha1,
      channel_id: channelId,
      url: encodeURIComponent(videoUrl)
    };

    if (options.access) params.access = options.access; // 1=private, 2=adult
    if (options.embed) params.embed = encodeURIComponent(options.embed);
    if (options.count !== undefined) params.count = options.count;

    try {
      const response = await axios.get(
        `${API_BASE_URL}/user/api.php`,
        { params }
      );
      
      // Parse XML response
      const parser = new DOMParser();
      const xmlDoc = parser.parseFromString(response.data, 'text/xml');
      
      return {
        video_id: xmlDoc.querySelector('video_id')?.textContent,
        title: xmlDoc.querySelector('title')?.textContent,
        description: xmlDoc.querySelector('description')?.textContent,
        status: xmlDoc.querySelector('status')?.textContent
      };
    } catch (error) {
      console.error('Error inserting video:', error);
      throw error;
    }
  }

  // Fetch video information
  async fetchVideo(videoId) {
    if (!this.isAuthenticated()) {
      throw new Error('User not authenticated');
    }

    const params = {
      action: 'fetch',
      email: this.email,
      password: this.passwordSha1,
      video_id: videoId
    };

    try {
      const response = await axios.get(
        `${API_BASE_URL}/user/api.php`,
        { params }
      );
      
      // Parse XML response
      const parser = new DOMParser();
      const xmlDoc = parser.parseFromString(response.data, 'text/xml');
      
      const thumbnails = [];
      let i = 1;
      while (xmlDoc.querySelector(`thumbnail_url${i}`)) {
        thumbnails.push(xmlDoc.querySelector(`thumbnail_url${i}`).textContent);
        i++;
      }
      
      return {
        video_id: xmlDoc.querySelector('video_id')?.textContent,
        title: xmlDoc.querySelector('title')?.textContent,
        description: xmlDoc.querySelector('description')?.textContent,
        thumb_status: xmlDoc.querySelector('thumb_status')?.textContent,
        thumbnails
      };
    } catch (error) {
      console.error('Error fetching video:', error);
      throw error;
    }
  }

  // Legacy API to get video metadata from URL
  async getVideoMetadata(videoUrl) {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/api_rest/`,
        {
          params: {
            video_link: encodeURIComponent(videoUrl),
            api_id: API_KEY,
            video_size: '720'
          }
        }
      );
      
      // Parse XML response
      const parser = new DOMParser();
      const xmlDoc = parser.parseFromString(response.data, 'text/xml');
      
      return {
        title: xmlDoc.querySelector('title')?.textContent,
        embed: xmlDoc.querySelector('embed')?.textContent,
        thumbnail: xmlDoc.querySelector('thumbnail')?.textContent,
        description: xmlDoc.querySelector('description')?.textContent
      };
    } catch (error) {
      console.error('Error fetching video metadata:', error);
      throw error;
    }
  }

  // Search videos (using website scraping as no official search API)
  async searchVideos(query, page = 1) {
    try {
      // Note: This is a placeholder. In a real implementation, you might:
      // 1. Use the website's internal API if available
      // 2. Implement a backend proxy that scrapes the website
      // 3. Use the official API if search endpoint exists
      
      const response = await axios.get(
        `${API_BASE_URL}/search.php`,
        {
          params: {
            q: query,
            page: page
          }
        }
      );
      
      // This would need proper HTML parsing
      return {
        results: [],
        page: page,
        totalPages: 1
      };
    } catch (error) {
      console.error('Error searching videos:', error);
      throw error;
    }
  }
}

export default new MyVidsterAPI();
