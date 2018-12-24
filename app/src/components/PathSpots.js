import React, { PureComponent } from 'react';
import styled from 'styled-components';

class PathSpotsComponent extends PureComponent {
  render () {
    const { className, move, path, time } = this.props;

    return (
      <div className={className}>
        <PathOverview>
          <h2>{time}</h2>
          <h4>{move}를 이용합니다.</h4>
        </PathOverview>
        <SpotList>
          {path.map((name, index) => (
            <SpotItem key={`${name}-${index}`}>
              <Icon>
                <Line />
                <Circle />
              </Icon>
              <Information>
                <strong>{name}</strong>
              </Information>
            </SpotItem>
          ))}
        </SpotList>
      </div>
    );
  }
}

const PathOverview = styled.p`
  border-bottom: 1px solid #d3d3d3;
  padding: 0.5rem 1rem;
`;

const SpotList = styled.ul`
  padding: 0.5rem 0;
`;

const SpotItem = styled.li`
  display: flex;
`;

const Icon = styled.div`
  display: flex;
  flex: 1;
  justify-content: flex-end;
  position: relative;
`;

const Circle = styled.div`
  background-color: #3a94fb;
  border-radius: 50%;
  height: 3rem;
  margin: 0.5rem 0;
  width: 3rem;
  z-index: 1;
`;

const Line = styled.div`
  background-color: #3a94fb;
  height: 100%;
  position: absolute;
  right: 22px;
  width: 3px;
`;

const Information = styled.div`
  align-items: center;
  border-bottom: 1px solid #d3d3d3;
  display: flex;
  flex: 4;
  padding: 0.5rem;
`;

export const PathSpots = styled(PathSpotsComponent)`
  background-color: #fff;
  flex: 1;
`;
