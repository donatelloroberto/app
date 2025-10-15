import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ArrowLeft, Share2, Bookmark, Download } from 'lucide-react';
import myvidsterApi from '../services/myvidsterApi';

function VideoPlayer() {
  const { videoId } = useParams();
  const navigate = useNavigate();
  const [video, setVideo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadVideo();
  }, [videoId]);

  const loadVideo = async () => {
    try {
      setLoading(true);
      const videoData = await myvidsterApi.getVideoById(videoId);
      setVideo(videoData);
    } catch (err) {
      setError('Failed to load video');
      console.error('Error loading video:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleBookmark = async () => {
    try {
      // Implement bookmarking logic
      alert('Bookmark feature coming soon!');
    } catch (err) {
      console.error('Error bookmarking video:', err);
    }
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center h-screen">
        <div className="loading-spinner" />
      </div>
    );
  }

  if (error || !video) {
    return (
      <div className="flex flex-col items-center justify-center h-screen">
        <h2 className="text-2xl font-bold mb-4">Video Not Found</h2>
        <p className="text-gray-400 mb-6">{error || 'The video you\'re looking for doesn\'t exist.'}</p>
        <button onClick={() => navigate('/')} className="btn btn-primary">
          <ArrowLeft className="w-5 h-5" />
          Go Home
        </button>
      </div>
    );
  }

  return (
    <div className="video-player-container">
      <button 
        onClick={() => navigate(-1)} 
        className="btn btn-secondary mb-4"
        data-testid="back-button"
      >
        <ArrowLeft className="w-5 h-5" />
        Back
      </button>

      <div className="video-embed">
        {/* Video player would go here */}
        <div className="flex items-center justify-center h-full bg-zinc-900">
          <p className="text-gray-400">Video player integration coming soon</p>
        </div>
      </div>

      <div className="video-info">
        <div className="flex items-start justify-between mb-4">
          <div>
            <h1 className="video-title">
              {video.title || `Video ${videoId}`}
            </h1>
            <div className="flex items-center gap-4 text-sm text-gray-400">
              <span>1.2M views</span>
              <span>•</span>
              <span>2 days ago</span>
            </div>
          </div>
        </div>

        <div className="flex gap-2 mb-6">
          <button 
            onClick={handleBookmark}
            className="btn btn-secondary"
            data-testid="bookmark-button"
          >
            <Bookmark className="w-5 h-5" />
            Save
          </button>
          <button className="btn btn-secondary">
            <Share2 className="w-5 h-5" />
            Share
          </button>
          <button className="btn btn-secondary">
            <Download className="w-5 h-5" />
            Download
          </button>
        </div>

        <div className="bg-zinc-900 rounded-lg p-6">
          <h3 className="text-lg font-semibold mb-3">Description</h3>
          <p className="video-description">
            {video.description || 'No description available'}
          </p>
        </div>
      </div>
    </div>
  );
}

export default VideoPlayer;
